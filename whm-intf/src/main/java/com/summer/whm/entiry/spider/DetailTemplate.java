package com.summer.whm.entiry.spider;

import java.util.Arrays;
import java.util.List;

import com.summer.whm.entiry.BaseEntity;

public class DetailTemplate extends BaseEntity {
    
    private Integer crawTemplateId;
    
    private Integer forumId;
    
    private String url;

    private String username;

    private Integer userId;

    private Integer topicType;

    private String titleXPath;

    private String nextXPath;

    private String prevXPath;

    private String contentXPath;

    private String contentNextXPath;

    private String contentPrevXPath;

    private String commentsXPath;

    private CrawTemplate crawTemplate;
    
    private String crawTemplateName;
    
    private String contentExcludeXPath;
    
    private String titleExcludeXPath;
    
    private List<String> contentExcludeXPathList;
    
    private List<String> titleExcludeXPathList;
    
    public List<String> getContentExcludeXPathList() {
        if(contentExcludeXPath != null && !"".equals(contentExcludeXPath)){
            String[] strs = contentExcludeXPath.split(",");
            contentExcludeXPathList = Arrays.asList(strs);
        }
        return contentExcludeXPathList;
    }

    public void setContentExcludeXPathList(List<String> contentExcludeXPathList) {
        this.contentExcludeXPathList = contentExcludeXPathList;
    }

    public List<String> getTitleExcludeXPathList() {
        if(titleExcludeXPath != null && !"".equals(titleExcludeXPath)){
            String[] strs = titleExcludeXPath.split(",");
            titleExcludeXPathList = Arrays.asList(strs);
        }
        return titleExcludeXPathList;
    }

    public void setTitleExcludeXPathList(List<String> titleExcludeXPathList) {
        this.titleExcludeXPathList = titleExcludeXPathList;
    }

    public String getContentExcludeXPath() {
        return contentExcludeXPath;
    }

    public void setContentExcludeXPath(String contentExcludeXPath) {
        this.contentExcludeXPath = contentExcludeXPath;
    }

    public String getTitleExcludeXPath() {
        return titleExcludeXPath;
    }

    public void setTitleExcludeXPath(String titleExcludeXPath) {
        this.titleExcludeXPath = titleExcludeXPath;
    }

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

    public String getContentXPath() {
        return contentXPath;
    }

    public void setContentXPath(String contentXPath) {
        this.contentXPath = contentXPath;
    }

    public String getContentNextXPath() {
        return contentNextXPath;
    }

    public void setContentNextXPath(String contentNextXPath) {
        this.contentNextXPath = contentNextXPath;
    }

    public String getContentPrevXPath() {
        return contentPrevXPath;
    }

    public void setContentPrevXPath(String contentPrevXPath) {
        this.contentPrevXPath = contentPrevXPath;
    }

    public String getCommentsXPath() {
        return commentsXPath;
    }

    public void setCommentsXPath(String commentsXPath) {
        this.commentsXPath = commentsXPath;
    }
}
