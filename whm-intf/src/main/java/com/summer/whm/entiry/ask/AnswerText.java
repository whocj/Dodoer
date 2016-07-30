package com.summer.whm.entiry.ask;

import com.summer.whm.entiry.BaseEntity;

public class AnswerText extends BaseEntity {
    
    private Integer answerId;

    private String answerTitle;

    private String answerContent;

    private String answerContentText;
    
    public String getAnswerContentText() {
        return answerContentText;
    }

    public void setAnswerContentText(String answerContentText) {
        this.answerContentText = answerContentText;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    @Override
    public String toString() {
        return "AnswerText [answerId=" + answerId + ", answerTitle=" + answerTitle + ", answerContent=" + answerContent
                + ", answerContentText=" + answerContentText + "]";
    }
    
}
