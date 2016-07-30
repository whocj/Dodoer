package com.summer.whm.entiry.spider;

import com.summer.whm.entiry.BaseEntity;

public class ListTemplate extends BaseEntity{
    
    private Integer crawTemplateId;
    
    private Integer forumId;
    
    private String url;

    private String username;
    
    private Integer userId;
    
    private Integer topicType;

    private String listXPath;

    private String totalPageXPath;

    private String titleXPath;

    private String nextXPath;

    private String prevXPath;

    private String detailXPath;
    
    private CrawTemplate crawTemplate;
    
    private String crawTemplateName;
    
    public String getCrawTemplateName() {
        return crawTemplateName;
    }

    public void setCrawTemplateName(String crawTemplateName) {
        this.crawTemplateName = crawTemplateName;
    }
    
    public CrawTemplate getCrawTemplate() {
        return crawTemplate;
    }

    public void setCrawTemplate(CrawTemplate crawTemplate) {
        this.crawTemplate = crawTemplate;
    }

    
    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Integer getCrawTemplateId() {
        return crawTemplateId;
    }

    public void setCrawTemplateId(Integer crawTemplateId) {
        this.crawTemplateId = crawTemplateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getTopicType() {
        return topicType;
    }

    public void setTopicType(Integer topicType) {
        this.topicType = topicType;
    }

    public String getListXPath() {
        return listXPath;
    }

    public void setListXPath(String listXPath) {
        this.listXPath = listXPath;
    }

    public String getTotalPageXPath() {
        return totalPageXPath;
    }

    public void setTotalPageXPath(String totalPageXPath) {
        this.totalPageXPath = totalPageXPath;
    }

    public String getTitleXPath() {
        return titleXPath;
    }

    public void setTitleXPath(String titleXPath) {
        this.titleXPath = titleXPath;
    }

    public String getNextXPath() {
        return nextXPath;
    }

    public void setNextXPath(String nextXPath) {
        this.nextXPath = nextXPath;
    }

    public String getPrevXPath() {
        return prevXPath;
    }

    public void setPrevXPath(String prevXPath) {
        this.prevXPath = prevXPath;
    }

    public String getDetailXPath() {
        return detailXPath;
    }

    public void setDetailXPath(String detailXPath) {
        this.detailXPath = detailXPath;
    }
}
