package org.assignment.melongation.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.assignment.melongation.pojo.Answer;

public interface AnswerMapper {
    @Results
    @Select("select * from answer where id = #{id}")
    public Answer findAnswerById(@Param("id") Integer id);
}
