package com.lcsaber.controller;

import com.lcsaber.Service.UserService;
import com.lcsaber.pojo.User;
import com.lcsaber.util.JWTUtil;
import com.lcsaber.util.RequestUtil;
import com.lcsaber.util.ResponseBean;
import com.lcsaber.util.Sender;
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

    @PostMapping("/login")
    public ResponseBean login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.selUser(username);
        RequestUtil requestUtil = new RequestUtil(request);
        requestUtil.setUserId(""+user.getId());
        sender.send(requestUtil.toString());
        if (user.getPassword().equals(password)){
            return new ResponseBean(200,"Login success",JWTUtil.sign(""+user.getId(),password));
        }
        else{
            return new ResponseBean(401,"Unauthorized",null);
        }
    }

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
            return new ResponseBean(200,"Login success",JWTUtil.sign(""+user.getId(),password));
        }
        else{
            return new ResponseBean(401,"Unauthorized",null);
        }
    }

    @PostMapping("/login/phone")
    public ResponseBean login3(HttpServletRequest request){
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        User user = userService.selUserByPhone(phone);
        RequestUtil requestUtil = new RequestUtil(request);
        requestUtil.setUserId(""+user.getId());
        sender.send(requestUtil.toString());
        if (user.getPassword().equals(password)){
            return new ResponseBean(200,"Login success",JWTUtil.sign(""+user.getId(),password));
        }
        else{
            return new ResponseBean(401,"Unauthorized",null);
        }
    }

}
