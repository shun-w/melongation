package org.assignment.melongation.mapper;


import org.apache.ibatis.annotations.*;
import org.assignment.melongation.pojo.Paper;
import org.assignment.melongation.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
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
    @Insert("insert into `paper` values (null, #{userId}, #{title},  #{description}, #{createTime}, #{submitNumber}, #{isChecked)")
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
     * 根据id check通过一个paper
     *
     * @param id
     */
    @Update("update  paper set is_checked = 1 where id=#{id}")
    public void checkPaperById(@Param("id") Integer id);


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


    @Select("select * from paper where id=(select id from user where username = #{username})")
    public List<Paper> findUserPaper(@Param("username")String username);

    /**
     * 根据paper的Id查询Paper，并查询出关联的user， questions  __(have bug)
     * @param id
     * @return
     */
    @Results({
            @Result(property = "user", column = "user_id", javaType= User.class,
                    one = @One(select = "org.assignment.melongation.mapper.UserMapper.selectUserById")
            ),
            @Result(property = "questionList", column = "id",
                    many = @Many(select = "org.assignment.melongation.mapper.QuestionMapper.selectQuestionByPaperId"))

    }
    )
    @Select("select * from paper where id=#{id}")
    public Paper findPaperById(@Param("id")Integer id);



}
