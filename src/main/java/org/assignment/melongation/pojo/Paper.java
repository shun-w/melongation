package org.assignment.melongation.pojo;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

public class Paper {
    private Integer id;
    private Integer userId;//外键

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Past
    private Date createTime;
    @NotEmpty
    private Integer submitNumber;//当前已经提交填写的数量
    @NotEmpty
    private boolean isChecked;


    private User user;
    private List<Question> questionList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", submitNumber=" + submitNumber +
                ", isChecked=" + isChecked +
                '}';
    }

    public Paper(Integer id, Integer userId, String title, String description, Date createTime, Integer submitNumber, boolean isChecked) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createTime = createTime;
        this.submitNumber = submitNumber;
        this.isChecked = isChecked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSubmitNumber() {
        return submitNumber;
    }

    public void setSubmitNumber(Integer submitNumber) {
        this.submitNumber = submitNumber;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }
}
