package com.lcsaber.mapper;

import com.lcsaber.pojo.UserInformInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: LiChao
 * @Date: 2019/6/22 20:53
 */
@Mapper
public interface InformMapper {

    @Insert("insert into user_inform (id,inform_level,inform_description," +
            "inform_content,user_id,inform_time)" +
            " values(default,#{informLevel},#{informDescription},#{informContent},#{userId},#{informTime})")
    int insInform(UserInformInfo userInformInfo);

    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "inform_level",property = "informLevel"),
            @Result(column = "inform_description",property = "informDescription"),
            @Result(column = "inform_content",property = "informContent"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "inform_time",property = "informTime")
    })
    @Select("select * from user_inform order by inform_time desc limit #{0},#{1}")
    List<UserInformInfo> selUserInform(Integer pageStart,Integer pageSize);

    @Select("select count(*) from user_inform")
    int selAllCount();

    @Delete("delete from user_inform where id = #{0}")
    int delInform(Long id);
}
