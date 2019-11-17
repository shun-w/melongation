package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.Answer;

import java.util.List;

public interface AnswerMapper {
    /**
     * 根据Id查询一个Answer，不包含关联属性
     *
     * @param id
     * @return
     */
    @Select("select * from answer where id = #{id}")
    public Answer selectAnswerById(@Param("id") Integer id);


    /**
     * 查询所有answer，不包含关联属性
     *
     * @return
     */
    @Select("select * from answer")
    public List<Answer> selectAllAnswer();

    /**
     * 保存一个answer
     *
     * @param answer
     * @return
     */
    @Insert("insert into answer values (null, #{questionId},#{answer})")
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    public Integer saveAnswer(Answer answer);

    /**
     * 根据questionId查询answer
     *
     * @param questionId
     * @return
     */
    @Select("select * from answer where question_id = #{questionId}")
    public List<Answer> selectAnswerByQuestionId(@Param("questionId") Integer questionId);


    /**
     * 根据Id查询answer，并查询出关联的question
     *
     * @param id
     * @return
     */

    @Results({@Result(property = "question", column = "question_id",
            one = @One(select = "org.assignment.melongation.mapper.QuestionMapper.selectQuestionById"))})
    @Select("select * from answer where id=#{id}")
    public Answer findAnswerById(@Param("id") Integer id);


    /**
     * 根据Id查询answer，并查询出关联的question
     * @return
     */
    @Results({@Result(property = "question", column = "question_id",
            one = @One(select = "org.assignment.melongation.mapper.QuestionMapper.selectQuestionById"))})
    @Select("select * from answer")
    public List<Answer> findAllAnswer();
}
