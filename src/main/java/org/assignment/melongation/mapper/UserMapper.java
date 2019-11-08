package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.User;


import java.util.List;

/**
 * User持久层代码
 */
public interface UserMapper{

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
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


    /**
     * 查询所有用户
     * @return
     */
    @Select(" select * from `user` where is_active=1 ")
    @ResultMap("userMap")
    public List<User> findAll();


    /**
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Select(" select * from `user` where username=#{username} and password=#{password} and is_active=1 ")
    @ResultMap("userMap")
    public User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
