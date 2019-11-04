package org.assignment.melongation.service;


import org.assignment.melongation.mapper.UserMapper;
import org.assignment.melongation.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public List<UserInfo> findAll() {
        List<UserInfo> users = userMapper.findAll();
        return users;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
