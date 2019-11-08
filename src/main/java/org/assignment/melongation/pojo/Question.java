package org.assignment.melongation.pojo;

import org.omg.CORBA.INTERNAL;

import java.util.List;

public class Question {
    private Integer id;
    private Integer type;//问题类型
    private Boolean mustAnswer;
    private Integer orderNumber;
    private List<Answer> answerList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

}
