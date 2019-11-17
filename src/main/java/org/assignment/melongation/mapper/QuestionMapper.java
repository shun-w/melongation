package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.Question;

import java.util.List;

public interface QuestionMapper {

    /**
     * 根据Id查询一个Question
     *
     * @param id
     * @return
     */
    @Select(" select * from `question` where id = #{id} ")
    public Question selectQuestionById(@Param("id") Integer id);

    /**
     * 查询所有的Question
     *
     * @return
     */
    @Select(" select * from `question` ")
    public List<Question> selectAllQuestion();


    /**
     * 保存一个question
     *
     * @param question
     * @return
     */
    @Insert("insert into `question` values (null, #{paperId}, #{type}, #{content},#{mustAnswer},#{orderNumber})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    public Integer saveQuestion(Question question);

    /**
     * 根据paperId查询question
     *
     * @param paperId
     * @return
     */
    @Select("select * from question where paper_id = #{paperId}")
    public List<Question> selectQuestionByPaperId(@Param("paperId") Integer paperId);

    /**
     * 查询所有的question，并查出关联的paper，answer
     *
     * @return
     */
    @Results({
            @Result(property = "paper", column = "paper_id",
                    one = @One(select = "org.assignment.melongation.mapper.PaperMapper.selectPaperById"))
            , @Result(property = "answerList", column = "id",
            many = @Many(select = "org.assignment.melongation.mapper.AnswerMapper.selectAnswerByQuestionId"))
    })
    @Select("select * from question")
    public List<Question> findAllQuestion();


    /**
     * 根据Id查询question，并查出关联的paper，answer
     *
     * @return
     */
    @Results({
            @Result(property = "paper", column = "paper_id",
                    one = @One(select = "org.assignment.melongation.mapper.PaperMapper.selectPaperById"))
            , @Result(property = "answerList", column = "id",
            many = @Many(select = "org.assignment.melongation.mapper.AnswerMapper.selectAnswerByQuestionId"))
    })
    @Select("select * from question where id=#{id}")
    public List<Question> findQuestionById(@Param("id")Integer id);
}
