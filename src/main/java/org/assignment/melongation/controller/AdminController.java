package org.assignment.melongation.controller;


import com.github.pagehelper.PageInfo;
import org.assignment.melongation.pojo.Admin;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.AdminService;
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
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PaperService paperService;
    @Autowired
    QuestionService questionService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    //    @ResponseBody

    /**
     * 返回paper的列表
     *
     * @param model
     * @return
     */
    @GetMapping("/getAllPaper")
    public String getAllPaper(Model model, int pageNo) {
        PageInfo<Paper> papers = paperService.findAllPaper(pageNo);
        model.addAttribute("papers", papers);
        return "/admin/papers";
    }

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

        //List<Question> Questions =  questionService.findQuestionByPaperId(id);
        // model.addAttribute("questions", Questions);

        return "/admin/paper";
    }

    /**
     * 审核通过按钮
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/checkPaper")
    public String checkPaper(Model model, int id) {
        int pageNo = 1;
        paperService.checkPaper(id);
        return "redirect:/admin/getAllPaper?pageNo=" + Integer.toString(pageNo);   //审核通过，重定向刷新一下
    }

    /**
     *管理员登录页面
     * @return
     */
    @GetMapping("/login")
    public String getLoginForm() {
        return "admin/login";
    }

    /**
     *管理员登录
     * @param username
     * @param password
     * @param checkCode
     * @param session
     * @param model
     * @param resp
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/login")
    public String login(String username, String password, String checkCode, HttpSession session, Model model, HttpServletResponse resp) throws UnsupportedEncodingException {
        String sessionCode = session.getAttribute("code").toString();
        session.removeAttribute("code");

        if (!StringUtils.isEmpty(checkCode) && !StringUtils.isEmpty(checkCode) && (checkCode.toLowerCase()).equals(sessionCode.toLowerCase())) {
            Admin admin = adminService.login(username, password);

            System.out.println(admin.toString());
            if (admin!= null && admin.getUsername() != null) {
                String username1 = URLEncoder.encode(username, "utf-8");
                Cookie cookie = new Cookie("adminname", username1);
                cookie.setMaxAge(60 * 60 * 3);
                resp.addCookie(cookie);
                session.setAttribute("admin",admin);
                return "redirect:/admin";
            } else {
                model.addAttribute("msg", "用户名或密码错误,请重新登录");
                return "admin/login";
            }
        } else {
            model.addAttribute("msg", "验证码错误,请重新输入");
            return "admin/login";
        }

    }

    /**
     * 跳转管理员主界面
     * @return
     */

    @GetMapping()
    public String main() {
        return "admin/main";
    }

    /**
     * 管理员注销
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("username")) {
                cookies[i].setValue("");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        session.removeAttribute("admin");
        return "redirect:/admin/login";
    }

    /**
     * 增加管理员账号
     * @param admin
     * @return
     */
    @PostMapping("/addAdmin")
    @ResponseBody
    public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return ResponseEntity.ok().build();
    }

    /**
     * 管理员账号管理主界面
     * @param model
     * @return
     */
    //@GetMapping("/adminMain")
    //public String adminMain(Model model){
    //   List<Admin> admins=adminService.findAll();
//
    //      model.addAttribute("admins",admins);
    //    return "admin/adminMain";
    // }

    /**
     *
     *  删除管理员账号
     * @param id

     * @return
     */
    @GetMapping("/delete")
    public String deleteAdmin(@RequestParam Integer id){
        adminService.deleteAdmin(id);

        return "redirect:/admin/adminMain";
    }

    /**
     * 修改管理员账号
     * @param admin
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public ResponseEntity<Void> edit(@RequestBody Admin admin){
        adminService.editAdmin(admin);

        return ResponseEntity.ok().build();
    }

    /**
     * 查找管理员账号
     * @param keyWord
     * @param model
     * @return
     */
    @GetMapping("/search")

    public String search(@RequestParam(value = "keyWord") String keyWord,Model model){
        List<Admin> admins=adminService.serchAdmins(keyWord);
        if(admins.isEmpty()){
            model.addAttribute("msg",1);
        }else {
            model.addAttribute("admins", admins);
            model.addAttribute("msg",2);
        }
        return "admin/adminMain";
    }


    /**
     * 跳转问卷管理界面
     * @return
     */
    @GetMapping("/paperMain")
    public String paperMain(){
        return "admin/paperMain";
    }

    /**
     * 展示管理员账号列表
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping("/adminMain")
    public String page(String pageNumber,Model model){
        String spPage=pageNumber;
        //设置每页条数
        int pageSize=5;
        //页数
        int pageNo=0;
        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=adminService.getCount();
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);

        List<Admin> admins=adminService.pageAdmins(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("admins",admins);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);
        return "admin/adminMain";
    }




    /**
     * 展示用户列表
     * @param pageNumber
     * @param model
     * @return
     */
    @GetMapping("/userMain")
    public String userPage(String pageNumber,Model model){
        String spPage=pageNumber;
        //设置每页条数
        int pageSize=5;
        //页数
        int pageNo=0;
        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=adminService.getCountOfUser();
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);
        List<User> users=adminService.pageUsers(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("users",users);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);
        return "admin/userMain";
    }


    /**
     * 增加用户账号
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        adminService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer id){
        adminService.deleteUser(id);

        return "redirect:/admin/userMain";
    }

    /**
     * 修改用户账号
     * @param user
     * @return
     */
    @PostMapping("/editUser")
    @ResponseBody
    public ResponseEntity<Void> editUser(@RequestBody User user){
        adminService.editUser(user);
        return ResponseEntity.ok().build();
    }

    /**
     * 查找用户账号
     * @param keyWord
     * @param model
     * @return
     */
    @GetMapping("/searchUser")
    public String searchUser(@RequestParam(value = "keyWord") String keyWord,Model model){
        List<User> users=adminService.serchUsers(keyWord);
        if(users.isEmpty()){
            model.addAttribute("msg",1);
        }else {
            model.addAttribute("users", users);
            model.addAttribute("msg",2);
        }
        return "admin/userMain";
    }



}
