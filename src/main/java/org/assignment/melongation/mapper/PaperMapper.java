package org.assignment.melongation.mapper;


import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.Paper;

import java.util.List;

public interface PaperMapper {

    /**
     * 根据id查询paper,不查询关联属性
     *
     * @param id
     * @return
     */
    @Select(" select * from paper where id= #{id} ")
    public Paper selectPaperById(@Param("id") Integer id);

    /**
     * 查询所有的paper，不包含关联属性
     *
     * @return
     */
    @Select("select * from paper")
    public List<Paper> selectAllPaper();

    /**
     * 保存一个Paper
     *
     * @param paper
     * @return
     */
    @Insert("insert into `paper` values (null, #{userId}, #{title},  #{description}, #{createTime}, #{submitNumber}, #{isChecked})")
    @Options(keyProperty = "id", keyColumn = "id", useGeneratedKeys = true)
    public Integer savePaper(Paper paper);

    /**
     * 根据id删除一个paper
     *
     * @param id
     */
    @Update("delete from paper where id=#{id}")
    public void deletePaperById(@Param("id") Integer id);


    /**
     * 根据用户id查询paper
     *
     * @param userId
     * @return
     */
    @Select(" select * from paper where user_id=#{userId} ")
    public List<Paper> selectPageByUserId(@Param("userId") Integer userId);


    /**
     * 查询所有的Paper，并查询出关联的user， question
     *
     * @return
     */
    @Results({
            @Result(property = "user", column = "user_id",
                    one = @One(select = "org.assignment.melongation.mapper.UserMapper.selectUserById")
            ),
            @Result(property = "questionList", column = "id",
            many = @Many(select = "org.assignment.melongation.mapper.QuestionMapper.selectQuestionByPaperId"))

    }
    )
    @Select("select * from paper")
    public List<Paper> findAllPaper();

    /**
     * 根据Id查询Paper，并查询出关联的user， question
     * @param id
     * @return
     */
    @Results({
            @Result(property = "user", column = "user_id",
                    one = @One(select = "org.assignment.melongation.UserMapper.selectUserById")
            ),
            @Result(property = "questionList", column = "id",
                    many = @Many(select = "org.assignment.melongation.mapper.QuestionMapper.selectQuestionByPaperId"))

    }
    )
    @Select("select * from paper where id=#{id}")
    public List<Paper> findPaperById(@Param("id")Integer id);

}
