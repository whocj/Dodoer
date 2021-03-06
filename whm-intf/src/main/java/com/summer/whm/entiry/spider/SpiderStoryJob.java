package com.summer.whm.entiry.spider;

import com.summer.whm.entiry.BaseEntity;

public class SpiderStoryJob extends BaseEntity {

    private Integer templateId;
    private Integer categoryId;
    private String categoryName;
    private Integer storyId;
    private Integer userId;

    private String username;
    private String name;
    private String url;
    private String title;
    private String status;
    private String spiderStatus;
    private String qtRule;
    
    public String getSpiderStatus() {
        return spiderStatus;
    }

    public void setSpiderStatus(String spiderStatus) {
        this.spiderStatus = spiderStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQtRule() {
        return qtRule;
    }

    public void setQtRule(String qtRule) {
        this.qtRule = qtRule;
    }

}
