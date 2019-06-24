package com.lcsaber.Service;

import com.lcsaber.pojo.User;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 14:57
 */
public interface UserService {
    User selUser(String userName);

    User selUserByEmail(String email);

    User selUserByPhone(String phone);
}
