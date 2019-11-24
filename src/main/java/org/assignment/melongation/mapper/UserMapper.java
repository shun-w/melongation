package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * User持久层代码
 */
@Repository
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
    @Update("update user set username=#{username}, password=#{password},  email=#{email} where id = #{id}")
    public int updateUser(User user);

    /**
     * 注册
     * @param username
     * @param password
     * @param email
     * @return
     */
    @Insert("insert into user values(null, #{username},#{password},null, #{email},null)")
    public int register(String username, String password, String  email);


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
    @Select("select * from user where username=#{username}")
    User selectUserByUsername(@Param("username") String username);

    /**
     * 用户模糊查询
     * @param keyWord
     * @return
     */
    @Select("select * from user where username like '%${keyWord}%'")
    public List<User> searchUserByKeyword(@Param("keyWord") String keyWord);

    /**
     * 用户账号查询总条数
     */
    @Select(value = "select count(*) from user")
    int getCount();

    /**
     * 用户
     * 分页查询
     */
    @Select(value = "select * from user limit #{pageNo},#{pageSize}")
    List<User> pageList(Map map);
}
