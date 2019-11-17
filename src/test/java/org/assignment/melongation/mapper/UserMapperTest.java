package org.assignment.melongation.mapper;

import org.assignment.melongation.mapper.UserMapper;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    /**
     * mysql中，数据库tinyint与java中boolean对应
     * 数据库中字段用下划线命名，java的pojo中用驼峰规则命名，可以自动转换
     */
    @Autowired
    UserMapper userMapper;
    @Test
    public void testSelectUserById(){
        User user = userMapper.selectUserById(1);
        System.out.println(user.toString());
    }


    @Test
    public void testFindAllUser(){
        List<User> allUser = userMapper.findAllUser();
        System.out.println("ss");
    }

    @Test
    public void testFindUserById(){
        User user = userMapper.findUserById(1);
        System.out.println("x");

    }
}
