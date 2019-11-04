package org.assignment.melongation.controller;

import org.assignment.melongation.pojo.UserInfo;
import org.assignment.melongation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user")
    public ResponseEntity<List<UserInfo>> findAll(){
        List<UserInfo> users = userService.findAll();
        if(CollectionUtils.isEmpty(users)){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

}
