package org.assignment.melongation.service;


import org.assignment.melongation.pojo.Answer;

import java.util.List;
import org.assignment.melongation.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface AnswerService {




        int[] getABCDnumber(int questionId);








    /**
     * 查询所有提交的答案
     *
     * @return 所有答案
     */
    public List<Answer> findAll();

    void saveQuestion(List<Answer> answer);

    /**
     *依据问题id查找答案
     *
     * @return 对应问题id的答案
     */
    //public List<Answer> findByQuestionId(Integer questionId);

    /**
     * 依据id查找答案
     *
     * @param id
     * @return 对应id的答案
     */
    //public Answer findById(Integer id);

    /**
     * 查找所有答案
     *
     * @return 所有答案
     */
    //public List<Answer> selectAll();

    /**
     * 保存一个answer
     *
     * @param answer
     * @return 1或0，1表示保存成功
     */
    //public Integer saveAnswer(Answer answer);

}
