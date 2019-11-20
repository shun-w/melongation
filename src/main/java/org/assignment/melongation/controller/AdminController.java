package org.assignment.melongation.controller;


import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.service.PaperService;
import org.assignment.melongation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/getAllPaper")
    public String getAllPaper(Model model) {


        List<Paper> papers =  paperService.findAllPaper();
        model.addAttribute("papers", papers);

        return "papers.jsp";
    }

    /**
     * 查看某个问卷的页面
     * @param model
     * @return
     */
    @GetMapping("/getOnePaperAndQuestion")
    public String getOnePaperAndQuestion(Model model, int id) {


        Paper paper =  paperService.findPaperById(id);
        model.addAttribute("paper", paper);

        List<Question> Questions =  questionService.findQuestionByPaperId(id);
        model.addAttribute("questions", Questions);



        return "onePaper.jsp";
    }








}
