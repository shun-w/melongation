package org.assignment.melongation.controller;


import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.PaperService;
import org.assignment.melongation.service.QuestionService;
import org.assignment.melongation.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    PaperService paperService;

    @Autowired
    QuestionService questionService;

    /**
     * 获取用户登录界面
     *
     * @return
     */
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
                Cookie cookie = new Cookie("username", username1);
                cookie.setMaxAge(60 * 60 * 3);
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
    public String main() {
        return "user/main";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
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


    /**
     * 获取添加问卷界面
     *
     * @return
     */
    @GetMapping("/addPaper")
    public String getAddPaper() {
        return "user/addPaper";
    }

    /**
     * 添加一张问卷
     *
     * @param paper
     * @param model
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/addPaper")
    public String postAddPaper(Paper paper, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(paper.getTitle()) || StringUtils.isEmpty(paper.getDescription())) {
            model.addAttribute("msg", "标题或者描述不能为空");
            return "user/addPaper";
        } else {
            String username = "";
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = URLDecoder.decode(cookie.getValue(), "utf-8");
                }
            }

            User user = userService.selectUserByUsername(username);

            paper.setCreateTime(new Date());
            paper.setSubmitNumber(0);
            paper.setUserId(user.getId());
            paperService.addPaper(paper);
//            System.out.println("问卷:"+paper.toString());
            System.out.println("问卷Id：" + paper.getId());
            model.addAttribute("paperId", paper.getId());
            return "user/addQuestion";
        }
    }


    /**
     * 提交问卷的问题
     * @param questions
     * @return
     */
    @PostMapping("/addQuestion")
    @ResponseBody
    public ResponseEntity<Void> addQuestion(@RequestBody List<Question> questions) {
        questionService.saveQuestions(questions);
        for (Question question : questions)
            System.out.println(question.toString());
        return ResponseEntity.ok().build();
    }
}
