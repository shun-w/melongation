package org.assignment.melongation.service.impl;

import org.assignment.melongation.mapper.AdminMapper;
import org.assignment.melongation.mapper.PaperMapper;
import org.assignment.melongation.mapper.UserMapper;
import org.assignment.melongation.pojo.Admin;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAllAdmin();
    }

    @Override
    public Admin login(String username, String password) {
        return adminMapper.selectAdminByUsernameAndPassword(username,password);
    }

    @Override
    public void deleteAdmin(Integer id) {
        adminMapper.deleteAdminById(id);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminMapper.saveAdmin(admin);
    }

    @Override
    public void editAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    @Override
    public List<Admin> serchAdmins(String keyWord) {
        return adminMapper.searchAdminByKeyword(keyWord);
    }

    @Override
    public int getCount() {
        return adminMapper.getCount();
    }

    @Override
    public List<Admin> pageAdmins(Map map) {
        return adminMapper.pageList(map);
    }

//    @Override
//    public void uploadImage(Map map) {
//        adminMapper.uploadImage(map);
//    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.DeleteUSerById(id);
    }

    @Override
    public void addUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void editUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> serchUsers(String keyWord) {
        return userMapper.searchUserByKeyword(keyWord);
    }

    @Override
    public int getCountOfUser() {
        return userMapper.getCount();
    }

    @Override
    public List<User> pageUsers(Map map) {
        return userMapper.pageList(map);
    }

    @Override
    public void isActive(int isActive, int userId) {
        userMapper.setIsActive(isActive,userId);

    }

}
