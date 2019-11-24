package org.assignment.melongation.service.impl;

import org.assignment.melongation.mapper.AnswerMapper;
import org.assignment.melongation.mapper.QuestionMapper;
import org.assignment.melongation.pojo.Answer;
import org.assignment.melongation.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    QuestionMapper questionMapper;

    /**
     * 返回单选题目的答题数据
     * @param questionId
     * @return
     */
    @Override
    public int[] getABCDnumber(int questionId) {

        int [] num_ABCD_single= {0, 0, 0, 0};

        int type = questionMapper.selectQuestionById(questionId).getType(); //判断多选还是单选

        List<Answer> answers = answerMapper.selectAnswerByQuestionId(questionId);

        for (Answer answer: answers){

            if (answer.getAnswer().equals("A"))  num_ABCD_single[0]+=1;
            if (answer.getAnswer().equals("B"))  num_ABCD_single[1]+=1;
            if (answer.getAnswer().equals("C"))  num_ABCD_single[2]+=1;
            if (answer.getAnswer().equals("D"))  num_ABCD_single[3]+=1;

        }

        return num_ABCD_single;
    }


}
