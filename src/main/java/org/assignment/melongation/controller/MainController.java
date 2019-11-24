package org.assignment.melongation.controller;

import org.assignment.melongation.pojo.Answer;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.User;
import org.assignment.melongation.service.AnswerService;
import org.assignment.melongation.service.PaperService;
import org.assignment.melongation.service.UserService;
import org.assignment.melongation.utils.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


@Controller
public class MainController {


    @Autowired
    private PaperService paperService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/{paperId}")
    public String fillInPaper(@PathVariable("paperId") Integer paperId, Model model) {
        Paper paper = paperService.getCheckedPaper(paperId);
        model.addAttribute("paper", paper);
        return "fillInPaper";
    }


    /**
     * @param answers
     * @return
     */
    @PostMapping("/fillPaper")
    @ResponseBody
    public ResponseEntity<String> postFillInPaper(@RequestBody List<Answer> answers) {
        System.out.println(answers.toString());

        answerService.saveQuestion(answers);

        return ResponseEntity.ok(new String("感谢填写！！"));
    }


    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("msg", "hello melongation");
        return "index";
    }

    @Autowired
    UserService userService;

    //注册
    @GetMapping("/register")
    public String register() {
        return "addUser";
    }


    @PostMapping("/register")
    public String register(String username, String password, String email, Model model) throws UnsupportedEncodingException {
        if (!email.contains("@") || username.length() < 2 || username.length() > 10 || password.length() < 6 || password.length() > 20) {
            model.addAttribute("msg", "密码长度必须为6-20,用户名长度为2-10,邮箱地址必须合法!");
            return "addUser";
        }
        User user = userService.selectUserByUsername(username);
        if(user!=null){
            model.addAttribute("msg", "当前用户名已经存在！");
            return "addUser";
        }

        int answ = userService.register(username, password, email);
        if (answ == 1) {
            model.addAttribute("msg", "注册成功,请登录");

            return "redirect:/user/login";
        } else {
            model.addAttribute("msg", "注册失败,请重新注册");
            return "/register";
        }
    }


    @RequestMapping(value = {"/getCode"})
    public void loginValidateCode(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws Exception {
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        // 将四位数字的验证码保存到Session中。
        session.setAttribute("code", codeMap.get("code").toString());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", -1);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
