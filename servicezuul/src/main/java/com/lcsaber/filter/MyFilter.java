package com.lcsaber.filter;

import com.lcsaber.shiron.JWTToken;
import com.lcsaber.util.*;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

/**
 * @Author: LiChao
 * @Date: 2019/6/11 19:33
 */
@Component
public class MyFilter extends ZuulFilter {

    @Resource
    private SecurityManager securityManager;

    @Autowired
    private Sender sender;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getRequestURI().toString().contains("/login")){
            return false;
        }

//        if(request.getRequestURI().toString().contains("swagger")){
//            return false;
//        }
//        if (request.getRequestURI().toString().endsWith(".js") || request.getRequestURI().toString().endsWith(".css")){
//            return false;
//        }
        if (request.getRequestURI().toString().contains("api-docs")){
            return false;
        }
        if (request.getRequestURI().startsWith("/search")){
            return false;
        }

        if (request.getRequestURI().matches("/article/articles/\\d+/\\d+")){
            return false;
        }

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Subject subject = SecurityUtils.getSubject();
        if (subject!=null && subject.isAuthenticated()){
            return true;
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String authorization = request.getHeader("Authorization");
        HttpServletResponse response = ctx.getResponse();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if (authorization==null || authorization.equals("")){
            ctx.setSendZuulResponse(false);
            ResponseBean responseBean = new ResponseBean(CodeData.API_NOT_PER,false,
                    "没有该接口的访问权限",null);
            try{
                ctx.getResponse().getWriter().write(responseBean.toString());
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return false;
        }
        JWTToken token = new JWTToken(authorization);
        try{
            subject.login(token);
        }catch (Exception e){
        }
        if (!subject.isAuthenticated())
        {
            ctx.setSendZuulResponse(false);
            ResponseBean responseBean = new ResponseBean(CodeData.ACCOUNT_ERROR,false,"验证失败",null);
            try{
                ctx.getResponse().getWriter().write(responseBean.toString());
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return false;
        }
        RequestUtil requestUtil = new RequestUtil(request);
        String userId = JWTUtil.getUsername(authorization);
        requestUtil.setUserId(userId);
        sender.send(requestUtil.toString());
        //ctx.setRequestQueryParams();
        ctx.addZuulRequestHeader("userId",userId);
//        System.out.println(userId);

        return true;
    }
}
