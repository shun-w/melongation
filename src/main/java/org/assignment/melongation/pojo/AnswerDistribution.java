package org.assignment.melongation.pojo;

/**
 * AnswerDistribution
 */
public class AnswerDistribution {
    private Integer num;//数量

    private String answer;//回答的内容，此处是选项


    public AnswerDistribution(Integer num, String answer) {
        this.num = num;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "{value:"+ num + ",name:'" + answer + "'},";
    }
    public String getJsonAnswer(){
        return "'" + answer + "',";
    }

    public String getAnswer(){
        return answer;
    }

}
