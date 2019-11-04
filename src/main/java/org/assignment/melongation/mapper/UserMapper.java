package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.User;


import java.util.List;

public interface UserMapper{
    @Results(
            id = "userMap",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "image", property = "image"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "is_active",property = "isActive")
            }
    )
    @Select("select * from `user` where id=#{id}")
    public User findById(@Param("id") Integer id);

    @Select(" select * from `user` where is_active=1 ")
    @ResultMap("userMap")
    public List<User> findAll();

    @Select(" select * from `user` where username=#{username} and password=#{password} and is_active=1 ")
    @ResultMap("userMap")
    public User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
