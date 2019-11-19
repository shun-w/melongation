package org.assignment.melongation.service.impl;

import org.assignment.melongation.mapper.QuestionMapper;
import org.assignment.melongation.pojo.Question;
import org.assignment.melongation.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 保存question
     * @param questions
     */
    @Override
    public void saveQuestions(List<Question> questions) {
        for(Question question: questions){
            if(question.getMustAnswer()==null){
                question.setMustAnswer(true);
            }
            questionMapper.saveQuestion(question);

        }
    }
}
