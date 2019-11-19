package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.User;


import java.util.List;

/**
 * User持久层代码
 */
public interface UserMapper {

    /**
     * 根据id查询User，不查询关联属性
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User selectUserById(@Param("id") Integer id);

    /**
     * 查询所有的User，不查询关联属性
     *
     * @return
     */
    @Select("select * from user ")
    public List<User> selectAllUser();

    /**
     * 根据id删除一个user
     *
     * @param id
     */
    @Update("delete from `user` where id=#{id}")
    public void DeleteUSerById(@Param("id") Integer id);

    /**
     * 保存一个User
     *
     * @return
     */
    @Insert("insert into user values (null, #{username}, #{password}, #{image}, #{email}, #{isActive})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    public Integer saveUser(User user);

    /**
     * 更新User
     *
     * @param user
     */
    @Update("update `user` set username=#{username}, password=#{password}, image=#{image}, email=#{email}, is_active=#{isActive} where id = #{id}")
    public void updateUser(User user);


    /**
     * 根据用户名，密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Select(" select * from `user` where username=#{username} and password=#{password} and is_active=1 ")
    public User selectUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 查询所有的User，并查询出关联的Paper
     *
     * @return
     */
    @Results(
            @Result(property = "pageList", column = "id",
                    many = @Many(select = "org.assignment.melongation.mapper.PaperMapper.selectPageByUserId"))
    )
    @Select("select * from `user`")
    public List<User> findAllUser();

    /**
     * 根据id查询User，并查询出关联的Paper
     *
     * @param id
     * @return
     */
    @Results(
            @Result(property = "pageList", column = "id",
                    many = @Many(select = "org.assignment.melongation.mapper.PaperMapper.selectPageByUserId"))
    )
    @Select("select * from `user` where id = #{id}")
    public User findUserById(@Param("id") Integer id);


    /**
     * 根据username查找用户
     * @param username
     * @return
     */
    @Select("select * from `user` where username=#{username}")
    User selectUserByUsername(@Param("username") String username);
}
