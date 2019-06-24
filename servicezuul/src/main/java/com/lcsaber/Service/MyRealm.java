package com.lcsaber.Service;

import com.lcsaber.mapper.UserMapper;
import com.lcsaber.pojo.User;
import com.lcsaber.shiron.JWTToken;
import com.lcsaber.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 15:00
 */
@Service
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String)authenticationToken.getCredentials();
        String userName = JWTUtil.getUsername(token);
        if (userName==null){
            throw new AuthenticationException("token invalid");
        }
        User user = userMapper.getUserById(Long.parseLong(userName));

        if (user==null){
            throw new AuthenticationException("User didn't existed");
        }
        if (!JWTUtil.verify(token,userName,user.getPassword())){
            throw new AuthenticationException("userName or password error");
        }
        return new SimpleAuthenticationInfo(token,token,"my_realm");
    }
}
