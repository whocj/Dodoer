package com.summer.whm.entiry.ask;

import com.summer.whm.entiry.BaseEntity;

public class QuestionText extends BaseEntity {
    private Integer questionId;

    private String questionTitle;

    private String questionContent;

    private String questionContentText;
    
    public String getQuestionContentText() {
        return questionContentText;
    }

    public void setQuestionContentText(String questionContentText) {
        this.questionContentText = questionContentText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Override
    public String toString() {
        return "QuestionText [questionId=" + questionId + ", questionTitle=" + questionTitle + ", questionContent="
                + questionContent + ", questionContentText=" + questionContentText + "]";
    }
    
}
