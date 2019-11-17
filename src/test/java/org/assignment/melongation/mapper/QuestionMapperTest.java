package org.assignment.melongation.mapper;


import org.assignment.melongation.mapper.QuestionMapper;
import org.assignment.melongation.pojo.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QuestionMapperTest {
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void testSelectQuestionById() {
        Question question = questionMapper.selectQuestionById(1);
        System.out.println(question.toString());
    }


    @Test
    public void testSelectAllQUestion() {
        List<Question> questions = questionMapper.selectAllQuestion();
        for (Question question : questions) {
            System.out.println(question.toString());
        }

    }

    @Test
    public void testSaveQuestion() {
        Question question = new Question(null, 1, 2, "wangs", true, 2);
        questionMapper.saveQuestion(question);
        testSelectAllQUestion();
    }

    @Test
    public void testFindAllQuestion() {
        List<Question> allQuestion = questionMapper.findAllQuestion();

        System.out.println("s");
    }

    @Test
    public void testFindQuestionById() {
        List<Question> question = questionMapper.findQuestionById(1);

        System.out.println("x");
    }

}
