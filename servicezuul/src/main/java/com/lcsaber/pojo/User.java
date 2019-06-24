package com.lcsaber.pojo;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 14:43
 */
public class User {
    private String userName;
    private String password;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
