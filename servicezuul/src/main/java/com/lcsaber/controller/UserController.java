package com.lcsaber.controller;

import com.lcsaber.Service.UserService;
import com.lcsaber.pojo.User;
import com.lcsaber.util.JWTUtil;
import com.lcsaber.util.RequestUtil;
import com.lcsaber.util.ResponseBean;
import com.lcsaber.util.Sender;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 15:56
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sender sender;

    @ApiOperation(value = "按用户名登录",notes = "按用户名登录")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String")
    })
    @PostMapping("/login")
    public ResponseBean login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.selUser(username);
        RequestUtil requestUtil = new RequestUtil(request);
        requestUtil.setUserId(""+user.getId());
        sender.send(requestUtil.toString());
        if (user.getPassword().equals(password)){
            return new ResponseBean(1000,true,"登录成功",JWTUtil.sign(""+user.getId(),password));
        }
        else{
            return new ResponseBean(2001,false,"账户不存在或被禁用",null);
        }
    }

    @ApiOperation(value = "按邮箱登录",notes = "按邮箱登录")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "email",value = "邮箱",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String")
    })
    @PostMapping("/login/email")
    public ResponseBean login2(HttpServletRequest request)
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userService.selUserByEmail(email);
        RequestUtil requestUtil = new RequestUtil(request);
        requestUtil.setUserId(""+user.getId());
        sender.send(requestUtil.toString());
        if (user.getPassword().equals(password)){
            return new ResponseBean(1000,true,"登录成功",JWTUtil.sign(""+user.getId(),password));
        }
        else{
            return new ResponseBean(2001,false,"账户不存在或被禁用",null);
        }
    }

    @ApiOperation(value = "按手机登录",notes = "按手机登录")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone",value = "手机号",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String")
    })
    @PostMapping("/login/phone")
    public ResponseBean login3(HttpServletRequest request){
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        User user = userService.selUserByPhone(phone);
        RequestUtil requestUtil = new RequestUtil(request);
        requestUtil.setUserId(""+user.getId());
        sender.send(requestUtil.toString());
        if (user.getPassword().equals(password)){
            return new ResponseBean(1000,true,"登录成功",JWTUtil.sign(""+user.getId(),password));
        }
        else{
            return new ResponseBean(2001,false,"账户不存在或被禁用",null);
        }
    }

}
