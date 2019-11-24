package org.assignment.melongation.service;

import org.assignment.melongation.pojo.Admin;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminService {

    /**
     * 查询所有管理员
     * @return 所有用户管理员列表
     */
    public List<Admin> findAll();

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    public Admin login(String username, String password);

    /**
     * 删除一个管理员账号
     * @param id
     */
    public void deleteAdmin(Integer id);

    /**
     * 增加一个管理员
     * @param admin
     */
    public void addAdmin(Admin admin);

    /**
     * 修改一个管理员
     * @param admin
     */
    public void editAdmin(Admin admin);

    /**
     * 关键字查找管理员
     * @param keyWord
     * @return
     */
    public List<Admin> serchAdmins(String keyWord);

    /**
     *获取管理员账号的总数量
     * @return
     */
    public int getCount();

    /**
     * 管理员账号分页
     * @param map
     * @return
     */
    public List<Admin> pageAdmins(Map map);

    /**
     * 存储图像src
     * @param map
     */
    public void uploadImage(Map map);



    /**
     * 删除一个管理员账号
     * @param id
     */
    public void deleteUser(Integer id);

    /**
     * 增加一个管理员
     * @param user
     */
    public void addUser(User user);

    /**
     * 修改一个管理员
     * @param user
     */
    public void editUser(User user);

    /**
     * 关键字查找管理员
     * @param keyWord
     * @return
     */
    public List<User> serchUsers(String keyWord);

    /**
     *获取管理员账号的总数量
     * @return
     */
    public int getCountOfUser();

    /**
     * 管理员账号分页
     * @param map
     * @return
     */
    public List<User> pageUsers(Map map);






}
