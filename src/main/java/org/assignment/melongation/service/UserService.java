package org.assignment.melongation.service;

import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.pojo.User;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 查询所有用户
     *
     * @return 所有用户列表
     */
    public List<User> findAll();

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param email
     * @return
     */
    int register(String username, String password, String email);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int update(User user);

    /**
     *
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     *
     * @param keyWord
     * @return
     */
    List<User> serchUsers(String keyWord);
}
