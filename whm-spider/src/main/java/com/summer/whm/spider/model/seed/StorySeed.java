package com.summer.whm.spider.model.seed;

public class StorySeed {
    
    private String title;

    private String categoryId;

    private String templateId;

    private String url;

    private String qtRule;
    
    public StorySeed() {
        super();
    }

    public StorySeed(String title, String categoryId, String templateId, String url, String qtRule) {
        super();
        this.title = title;
        this.categoryId = categoryId;
        this.templateId = templateId;
        this.url = url;
        this.qtRule = qtRule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQtRule() {
        return qtRule;
    }

    public void setQtRule(String qtRule) {
        this.qtRule = qtRule;
    }
    
    public String asTxt(String split){
        return title + split +  categoryId + split + templateId + split + url + split + qtRule;
    }
}
