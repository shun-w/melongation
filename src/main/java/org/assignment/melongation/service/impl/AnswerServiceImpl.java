package org.assignment.melongation.service.impl;

import org.assignment.melongation.mapper.AnswerMapper;
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
    private AnswerMapper answerMapper;

    @Override
    public List<Answer> findAll(){
        return answerMapper.findAllAnswer();
    }
}
