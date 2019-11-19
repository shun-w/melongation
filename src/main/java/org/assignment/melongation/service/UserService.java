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
     * @return 所有用户列表
     */
    public List<User> findAll();

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);

    User selectUserByUsername(String username);


}
