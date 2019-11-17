package org.assignment.melongation.controller;





import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String getLoginForm() {
        return "user/login";
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param checkCode
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password, String checkCode, HttpSession session, Model model, HttpServletResponse resp) throws UnsupportedEncodingException {
        String sessionCode = session.getAttribute("code").toString();
        session.removeAttribute("code");

        if (!StringUtils.isEmpty(checkCode) && !StringUtils.isEmpty(checkCode) && (checkCode.toLowerCase()).equals(sessionCode.toLowerCase())) {
            User user = userService.login(username, password);
            if (user != null && user.getUsername() != null) {
                String username1 = URLEncoder.encode(username, "utf-8");
                Cookie cookie= new Cookie("username", username1);
                cookie.setMaxAge(60*60*3);
                resp.addCookie(cookie);
                return "redirect:/user";
            } else {
                model.addAttribute("msg", "用户名或密码错误,请重新登录");
                return "user/login";
            }
        } else {
            model.addAttribute("msg", "验证码错误,请重新输入");
            return "user/login";
        }

    }

    @GetMapping()
    public String main(){
        return "user/main";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("username")) {
                cookies[i].setValue("");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        return "redirect:/user/login";
    }

}
