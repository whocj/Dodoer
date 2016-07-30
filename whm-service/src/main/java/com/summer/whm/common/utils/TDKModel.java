package com.summer.whm.common.utils;

public class TDKModel {
    private String title;
    
    private String content;
    
    private String keywords;
    
    private String description;

    private String tagName;
    
    public TDKModel(String title, String content, String tagName) {
        super();
        this.title = title;
        this.content = content;
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    
}
