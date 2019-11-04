package org.assignment.melongation.controller;

import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.util.List;


@RestController("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (CollectionUtils.isEmpty(users)) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param checkCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password, String checkCode, HttpSession session) {
        String sessionCode = session.getAttribute("code").toString();
        session.removeAttribute("code");
        System.out.println("输入：" + checkCode.toLowerCase() + ",session:" + sessionCode.toLowerCase());
        if (!StringUtils.isEmpty(checkCode) && !StringUtils.isEmpty(checkCode) && (checkCode.toLowerCase()).equals(sessionCode.toLowerCase())) {
            return ResponseEntity.ok(new String("ok"));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
