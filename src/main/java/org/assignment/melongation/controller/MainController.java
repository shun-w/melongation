package org.assignment.melongation.controller;

import org.assignment.melongation.pojo.Answer;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.service.AnswerService;
import org.assignment.melongation.service.PaperService;
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
    public String fillInPaper(@PathVariable("paperId") Integer paperId, Model model){
        Paper paper = paperService.getCheckedPaper(paperId);
        model.addAttribute("paper",paper);
        return "fillInPaper";
    }

    @PostMapping("/{paperId}")
    @ResponseBody
    public ResponseEntity<String> postFillInPaper(@PathVariable("paperId") Integer paperId, Answer answer){
        System.out.println(answer.toString());

        return ResponseEntity.ok(new String("感谢填写！！"));
    }


    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("msg","hello melongation");
        return "index";
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
