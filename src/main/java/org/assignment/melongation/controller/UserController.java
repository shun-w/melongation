package org.assignment.melongation.controller;

import com.github.pagehelper.PageInfo;

import org.assignment.melongation.mapper.AnswerMapper;
import org.assignment.melongation.pojo.*;

import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.pojo.User;

import org.assignment.melongation.service.PaperService;
import org.assignment.melongation.service.QuestionService;
import org.assignment.melongation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PaperService paperService;

    @Autowired
    AnswerMapper answerMapper;

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

//        if (!StringUtils.isEmpty(checkCode) && !StringUtils.isEmpty(checkCode) && (checkCode.toLowerCase()).equals(sessionCode.toLowerCase())) {
            if (true){  //test
            User user = userService.login(username, password);
            if (user != null && user.getUsername() != null) {
                String username1 = URLEncoder.encode(username, "utf-8");
                Cookie cookie = new Cookie("username", username1);
                cookie.setMaxAge(60 * 60 * 3);
                resp.addCookie(cookie);
                session.setAttribute("user",user);
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
    public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("username")) {
                cookies[i].setValue("");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        session.removeAttribute("user");
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
     * 修改个人信息
     *
     * @return
     */
    @GetMapping("/update")
    public String update(Model model, HttpServletRequest request,HttpServletResponse resp) {
        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("username")) {
                String username =  cookies[i].getValue();
                 User user =  userService.selectUserByUsername(username);
                model.addAttribute("user",user);
                int id = user.getId();

                Cookie cookie = new Cookie("userid", Integer.toString(id));
                cookie.setMaxAge(60 * 60 * 3);
                resp.addCookie(cookie);

            }
        }

        return "user/updateUser";
    }


    @PostMapping("/update")
    public String updatePost(String username, String password, String email , Model model, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        int id = 0;
        User user;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("userid")) {
                String userid = cookies[i].getValue();
                id = Integer.parseInt(userid);
            }
        }


        user = new User(id, username, password, email);
        int answ = userService.update(user);
                if (answ == 1) {
                    model.addAttribute("msg", "修改成功");
                    for (int i = 0; i < cookies.length; i++) {  //更新cookies name的值
                        if (cookies[i].getName().equals("username")) {
                            cookies[i].setMaxAge(0);
                        }
                    }
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(60 * 60 * 3);
                    response.addCookie(cookie);

                    return "redirect:/user/update";
                } else {
                    model.addAttribute("msg", "修改个人信息失败,请重新修改个人信息");
                    }
                    return "redirect:/user/update";
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

    /**
     * 返回paper的列表
     *
     * @param model
     * @return
     */
    @GetMapping("/getUserPaper")
    public String getUserPaper(Model model, Integer pageNo,HttpServletRequest request) throws UnsupportedEncodingException {
        if (pageNo==null) pageNo = new Integer(1);
        String username = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie c:cookies){
            if(c.getName().equals("username")) username = URLDecoder.decode(c.getValue(), "utf-8");
        }
        if(username==null) return "user/login";
        PageInfo<Paper> papers = paperService.findUserPaper(pageNo,username);
        System.out.println();
        model.addAttribute("papers", papers);
        return "/user/papers";}


//    /**
//     * 跳转到我的问卷
//     * @return
//     */
//    @GetMapping("/myPapers")
//    public String myPaper(Model model,@RequestParam int pageNo,HttpSession session){
//        User user= (User) session.getAttribute("user");
//        int id=user.getId();
//        PageInfo<Paper> papers = paperService.findAllPaperByUser(pageNo,id);
//        model.addAttribute("papers", papers);
//        return "user/myPapers";
//
//    }

    /**
     * 查看某个问卷的页面, 以及附带其所有的问题
     *
     * @param model
     * @return
     */

    @GetMapping("/getOnePaperAndQuestion")
    public String getOnePaperAndQuestion(Model model, @RequestParam int id) {
        Paper paper = paperService.findPaperById(id);
        model.addAttribute("paper", paper);
        return "/user/paper";
    }

    @GetMapping("/selectDetail")
    public String SelectDetail(Model model,@RequestParam(value = "tid") String tid, @RequestParam(value = "tTitle") String tTitle){
        //务必在get请求发起之前替换字符串，将{替换为%7b,将}替换为%7d,否则请求会报错
        String details="";
        String allSelections="";
        List<AnswerDistribution> answerDistributionList= answerMapper.findAnswerByIdGrouped(Integer.parseInt(tid));
        for (AnswerDistribution answerDistribution : answerDistributionList) {
           details+= answerDistribution.toString();
           allSelections += answerDistribution.getJsonAnswer();
        }
        //务必在get请求发起之前替换字符串，将{替换为%7b,将}替换为%7d,否则请求会报错
        model.addAttribute("tid",tid);
        model.addAttribute("tTitle",tTitle);
        model.addAttribute("details",details);
        model.addAttribute("allSelections",allSelections);

        return "user/selectDetail";
    }

    @GetMapping("/paperAnalyze")
    public String PaperAnalyze(Model model,@RequestParam(value = "tid") String tid,@RequestParam(value = "tTitle") String tTitle){
        List<AnswerDistribution> answerDistributionList = answerMapper.findAnswerByIdGrouped(Integer.parseInt(tid));
        for(AnswerDistribution answerDistribution:answerDistributionList){
            System.out.println(answerDistribution.getAnswer());
        }

        model.addAttribute("tid",tid);
        model.addAttribute("tTitle",tTitle);
        model.addAttribute("answerList",answerDistributionList);
        return "user/paperAnalyze";
    }


//    @GetMapping("/myOnePaper")
//    public String getOnePaperAndQuestion(Model model, @RequestParam int id) {
//        Paper paper = paperService.findPaperById(id);
//        model.addAttribute("paper", paper);
//        return "/user/myOnePaper";
//    }

}
