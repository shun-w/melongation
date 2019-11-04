package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.UserInfo;

import java.util.List;

public interface UserMapper {
    @Results(
            id = "userInfoMap",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "image", property = "image"),
                    @Result(column = "email", property = "email")
            }
    )
    @Select("select * from `user` where id=#{id}")
    public UserInfo findById(@Param("id") Integer id);



    @Select("select * from `user` ")
    @ResultMap("userInfoMap")
    public List<UserInfo> findAll();


}
