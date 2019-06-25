package com.lcsaber.filter;

import com.lcsaber.shiron.JWTToken;
import com.lcsaber.util.JWTUtil;
import com.lcsaber.util.RequestUtil;
import com.lcsaber.util.ResponseBean;
import com.lcsaber.util.Sender;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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
        if(request.getRequestURI().toString().contains("/swagger")){
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
        if (authorization==null || authorization.equals("")){
            ctx.setSendZuulResponse(false);
            ResponseBean responseBean = new ResponseBean(401,"未登录",null);
            try{
                ctx.getResponse().getWriter().write(responseBean.toString());
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return false;
        }
        JWTToken token = new JWTToken(authorization);
        subject.login(token);
        RequestUtil requestUtil = new RequestUtil(request);
        String userId = JWTUtil.getUsername(authorization);
        requestUtil.setUserId(userId);
        sender.send(requestUtil.toString());
        //ctx.setRequestQueryParams();
        ctx.addZuulRequestHeader("userId",userId);
        return true;
    }
}
