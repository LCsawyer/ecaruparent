package com.lcsaber.Service.impl;

import com.lcsaber.Service.UserService;
import com.lcsaber.mapper.UserMapper;
import com.lcsaber.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 14:58
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User selUser(String userName) {
        return userMapper.getUser(userName);
    }

    @Override
    public User selUserByEmail(String email) {
        return userMapper.getUserByMail(email);
    }

    @Override
    public User selUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }
}
