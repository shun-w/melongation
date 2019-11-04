package org.assignment.melongation.service;


import org.assignment.melongation.mapper.UserMapper;
import org.assignment.melongation.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<UserInfo> findAll() {
        List<UserInfo> users = userMapper.findAll();
        return users;

    }
}
