package com.lcsaber.mapper;

import com.lcsaber.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 14:53
 */
@Mapper
public interface UserMapper {

    @Select("select id,password from user where username=#{0}")
    User getUser(String userName);

    @Select("select id,password from user where email=#{0}")
    User getUserByMail(String email);

    @Select("select id,password from user where phone=#{0}")
    User getUserByPhone(String phone);

    @Select("select username,id,password from user where id=#{0}")
    User getUserById(Long id);
}
