package com.summer.whm.entiry.ask;

import java.util.Date;
import java.util.List;

import com.summer.whm.common.utils.TagUtils;
import com.summer.whm.entiry.BaseEntity;

public class Question extends BaseEntity {

    private Integer categoryId;
    private Integer userId;
    private String username;
    private String tagName;
    /**
     * 浏览次数
     */
    private Integer viewCount;
    /**
     * 回复次数
     */
    private Integer replyCount;
    /**
     * 外部链接
     */
    private String outerUrl;
    /**
     * 是否上传附件 default '0'
     */
    private String isAffix;
    /**
     * 编辑时间
     */
    private Date editTime;
    /**
     * 编辑者IP
     */
    private String editerIp;
    /**
     * 编辑次数
     */
    private Integer editCount;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否匿名
     */
    private String anonymous;

    private Integer goodCount;

    private String questionTitle;

    private String questionContent;

    private String questionContentText;

    private List<Answer> answerList;
    
    private String keywords;
    
    private String description;
    
    /**
     * 是否有最佳答案，0否，1是
     */
    private String hasGood;
    
    private String userLogo;
    private String userLogo50;
    private String userLogo100;
    
    private List<String> tagNameList;
    
    public List<String> getTagNameList() {
        if(tagNameList == null && this.tagName != null){
            tagNameList = TagUtils.getTagList(tagName);
        }
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

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

    public String getHasGood() {
        return hasGood;
    }

    public void setHasGood(String hasGood) {
        this.hasGood = hasGood;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getQuestionContentText() {
        return questionContentText;
    }

    public void setQuestionContentText(String questionContentText) {
        this.questionContentText = questionContentText;
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

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
        tagNameList = TagUtils.getTagList(tagName);
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public String getIsAffix() {
        return isAffix;
    }

    public void setIsAffix(String isAffix) {
        this.isAffix = isAffix;
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

    public Integer getEditCount() {
        return editCount;
    }

    public void setEditCount(Integer editCount) {
        this.editCount = editCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    @Override
    public String toString() {
        return "Question [categoryId=" + categoryId + ", userId=" + userId + ", username=" + username + ", tagName="
                + tagName + ", viewCount=" + viewCount + ", replyCount=" + replyCount + ", outerUrl=" + outerUrl
                + ", isAffix=" + isAffix + ", editTime=" + editTime + ", editerIp=" + editerIp + ", editCount="
                + editCount + ", status=" + status + ", anonymous=" + anonymous + ", goodCount=" + goodCount
                + ", questionTitle=" + questionTitle + ", questionContent=" + questionContent
                + ", questionContentText=" + questionContentText + ", answerList=" + answerList + "]";
    }

}
