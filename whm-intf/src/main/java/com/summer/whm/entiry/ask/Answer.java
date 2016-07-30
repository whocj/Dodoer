package com.summer.whm.entiry.ask;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class Answer extends BaseEntity {

    private Integer questionId;
    private Integer categoryId;
    private Integer userId;
    private String username;
    private String askName;
    private String askEmail;
    private String askWebSite;

    /**
     * 外部链接
     */
    private String outerUrl;
    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑者IP
     */
    private String editerIp;
    /**
     * 是否匿名
     */
    private String anonymous;
    /**
     * 是否是最好的
     */
    private String isGood;

    private Integer goodCount;

    private String answerTitle;

    private String answerContent;

    private String answerContentText;

    private String userLogo;
    private String userLogo50;
    private String userLogo100;
    
    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public String getUserLogo50() {
        return userLogo50;
    }

    public void setUserLogo50(String userLogo50) {
        this.userLogo50 = userLogo50;
    }

    public String getUserLogo100() {
        return userLogo100;
    }

    public void setUserLogo100(String userLogo100) {
        this.userLogo100 = userLogo100;
    }

    public String getAnswerContentText() {
        return answerContentText;
    }

    public void setAnswerContentText(String answerContentText) {
        this.answerContentText = answerContentText;
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

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAskName() {
        return askName;
    }

    public void setAskName(String askName) {
        this.askName = askName;
    }

    public String getAskEmail() {
        return askEmail;
    }

    public void setAskEmail(String askEmail) {
        this.askEmail = askEmail;
    }

    public String getAskWebSite() {
        return askWebSite;
    }

    public void setAskWebSite(String askWebSite) {
        this.askWebSite = askWebSite;
    }

    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditerIp() {
        return editerIp;
    }

    public void setEditerIp(String editerIp) {
        this.editerIp = editerIp;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getIsGood() {
        return isGood;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood;
    }

    @Override
    public String toString() {
        return "Answer [questionId=" + questionId + ", categoryId=" + categoryId + ", userId=" + userId + ", username="
                + username + ", askName=" + askName + ", askEmail=" + askEmail + ", askWebSite=" + askWebSite
                + ", outerUrl=" + outerUrl + ", editTime=" + editTime + ", editerIp=" + editerIp + ", anonymous="
                + anonymous + ", isGood=" + isGood + ", goodCount=" + goodCount + ", answerTitle=" + answerTitle
                + ", answerContent=" + answerContent + ", answerContentText=" + answerContentText + "]";
    }

}
