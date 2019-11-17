package org.assignment.melongation.pojo;

import javax.validation.constraints.NotEmpty;

public class Answer {
    private Integer id;
    private Integer questionId;//属于哪位问题

    private String answer;//回答的内容


    private Question question;

    public Answer(Integer id, Integer questionId, String answer) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                '}';
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
