package org.assignment.melongation.pojo;


import org.apache.ibatis.annotations.Many;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class Question {
    private Integer id;
    private Integer paperId;

    @Max(3)
    @Min(1)
    private Integer type;//问题类型，1表示单选，2表示多选，3表示文本

    @Size(max = 2000)
    @NotEmpty
    private String content;//问题，采用json格式

    @NotEmpty
    private Boolean mustAnswer;//是否必须回答

    @NotEmpty
    private Integer orderNumber;//这个问题在问卷中的顺序（是第几个题目）

    private Paper paper;
    private List<Answer> answerList;

    public Question(Integer id, Integer paperId, Integer type, String content, Boolean mustAnswer, Integer orderNumber) {
        this.id = id;
        this.paperId = paperId;
        this.type = type;
        this.content = content;
        this.mustAnswer = mustAnswer;
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", paperId=" + paperId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", mustAnswer=" + mustAnswer +
                ", orderNumber=" + orderNumber +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getMustAnswer() {
        return mustAnswer;
    }

    public void setMustAnswer(Boolean mustAnswer) {
        this.mustAnswer = mustAnswer;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
