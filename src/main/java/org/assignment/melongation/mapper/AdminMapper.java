package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.Admin;

import java.util.List;

/**
 * Admin持久层代码
 */
public interface AdminMapper {
    /**
     * 根据id查询Admin
     *
     * @param id
     * @return
     */
    @Select(" select * from admin where id = #{id} ")
    public Admin selectAdminById(@Param("id") Integer id);


    /**
     * 查询所有的Admin，返回List
     *
     * @return
     */
    @Select("select * from admin")
    public List<Admin> selectAllAdmin();

    /**
     * 插入一个admin，返回主键
     * @param admin
     * @return
     */
    @Insert("insert into `admin` values (null, #{username}, #{password},#{image}, #{email})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    public Integer saveAdmin(Admin admin);

    /**
     * 根据id删除一个admin
     * @param id
     */
    @Update("delete from `admin` where id=#{id}")
    public void deleteAdminById(@Param("id")Integer id);

    /**
     *更新Admin
     * @param admin
     */
    @Update("update `admin` set username = #{username}, password = #{password}, image= #{image}, email=#{email} where id=#{id}")
    public void updateAdmin(Admin admin);

    /**
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     */
    @Select("select * from admin where username=#{username} and password=#{password}")
    public void selectAdminByUsernameAndPassword(String username, String password);
}
