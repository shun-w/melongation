package org.assignment.melongation.mapper;

import ch.qos.logback.core.pattern.color.ANSIConstants;
//import net.bytebuddy.asm.Advice;
import org.assignment.melongation.mapper.AnswerMapper;
import org.assignment.melongation.pojo.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.AbstractJdbcInsert;

import java.util.List;

@SpringBootTest
public class AnswerMapperTest {
    @Autowired
    private AnswerMapper answerMapper;

    @Test
    public void testSelectAnswerById() {
        Answer answer = answerMapper.selectAnswerById(2);
        System.out.println(answer.toString());
    }

    @Test
    public void testSelectAllAnswer() {
        List<Answer> answers = answerMapper.selectAllAnswer();
        for (Answer answer : answers) {
            System.out.println(answer.toString());
        }

    }

    @Test
    public void testSaveAnswer() {
        Answer answer = new Answer(null, 1, "wangs");
        Integer id = answerMapper.saveAnswer(answer);
        testSelectAllAnswer();
        System.out.println("id:" + id);
    }

    @Test
    public void testFindAllAnswer() {
        List<Answer> allAnswer = answerMapper.findAllAnswer();
        System.out.println("d");

    }

    @Test
    public void testFindAnswerById() {
        Answer answerById = answerMapper.findAnswerById(1);
        System.out.println("1");

    }
}
