package org.assignment.melongation.service.impl;

import org.assignment.melongation.exception.MelongationException;
import org.assignment.melongation.mapper.AnswerMapper;
import org.assignment.melongation.mapper.QuestionMapper;
import org.assignment.melongation.pojo.Answer;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Answer> findAll() {
        return answerMapper.findAllAnswer();
    }

    @Override
    public void saveQuestion(List<Answer> answers) {
        for (Answer answer : answers) {
            String answer1 = answer.getAnswer();

            String[] split = answer1.split(",");
            String answer2 = "";
            for (int i = 0; i < split.length; i++) {
                answer2 += split[i];
            }
            answer.setAnswer(answer2);

            Question question = questionMapper.selectQuestionById(answer.getQuestionId());

            if (answer2.length() <= 0 && question.getMustAnswer() == true)
                throw new MelongationException("问卷提交失败，问卷填写不对,请仔细检查");
            if (answer.getAnswer().equals("") && question.getMustAnswer() == true)
                throw new MelongationException("问卷提交失败，问卷填写不对,请仔细检查");
            answerMapper.saveAnswer(answer);
        }
    }
}
