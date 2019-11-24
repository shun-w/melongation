package org.assignment.melongation.service;

import org.assignment.melongation.pojo.Question;

import java.util.List;

/**
 * question服务
 */
public interface QuestionService {
    /**
     * 保存question
     *
     * @param questions
     */
    void saveQuestions(List<Question> questions);

    /**
     *
     * @param id
     * @return
     */
    List<Question> findQuestionByPaperId(int id);


}

