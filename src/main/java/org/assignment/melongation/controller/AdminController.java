package org.assignment.melongation.controller;


import com.github.pagehelper.PageInfo;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.service.PaperService;
import org.assignment.melongation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PaperService paperService;
    @Autowired
    QuestionService questionService;


    //    @ResponseBody

    /**
     * 返回paper的列表
     * @param model
     * @return
     */
    @GetMapping("/getAllPaper")
    public String getAllPaper(Model model, int pageNo) {


        PageInfo<Paper> papers =  paperService.findAllPaper(pageNo);
        model.addAttribute("papers", papers);
        return "/admin/papers";
    }

    /**
     * 查看某个问卷的页面, 以及附带其所有的问题
     * @param model
     * @return
     */
    @GetMapping("/getOnePaperAndQuestion")
    public String getOnePaperAndQuestion(Model model, @RequestParam int id) {

        Paper paper =  paperService.findPaperById(id);
        model.addAttribute("paper", paper);

        //List<Question> Questions =  questionService.findQuestionByPaperId(id);
       // model.addAttribute("questions", Questions);

        return "/admin/paper";
    }

    /**
     * 审核通过按钮
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/checkPaper")
    public String checkPaper(Model model, int id) {

        paperService.checkPaper(id);
        return "redirect:/admin/papers";   //审核通过，重定向刷新一下
    }




}
