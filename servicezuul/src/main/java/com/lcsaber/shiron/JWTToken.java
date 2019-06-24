package com.lcsaber.shiron;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 14:32
 */
public class JWTToken implements AuthenticationToken {
    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
