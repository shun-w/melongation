package org.assignment.melongation.service.impl;


import org.assignment.melongation.mapper.UserMapper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectAllUser();
    }

    @Override
    public User login(String username, String password) {
        return userMapper.selectUserByNameAndPassword(username, password);
    }

    @Override
    public int  register(String username, String password, String  email) {
         if(userMapper.register(username, password, email) > 0) return 1;
         else return 0;
    }

    @Override
    public int  update(User user) {
        if(userMapper.updateUser(user) > 0) return 1;
       else return 0;
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public List<User> serchUsers(String keyWord) {
        return userMapper.searchAdminByKeyword(keyWord);
    }


}
