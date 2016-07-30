package com.summer.whm.entiry.spider;

import com.summer.whm.entiry.BaseEntity;

public class CrawTemplate extends BaseEntity {
    
    private String name;
    
    private String description;

    private String type;
    
    private Integer forumId;
    
    private String status;
    
    private ListTemplate listTemplate;
    
    private DetailTemplate detailTemplate;
    
    //过滤词
    private String filterWord;
    
    public void init(){
        
    }
    
    public ListTemplate getListTemplate() {
        return listTemplate;
    }

    public void setListTemplate(ListTemplate listTemplate) {
        this.listTemplate = listTemplate;
    }

    public DetailTemplate getDetailTemplate() {
        return detailTemplate;
    }

    public void setDetailTemplate(DetailTemplate detailTemplate) {
        this.detailTemplate = detailTemplate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilterWord() {
        return filterWord;
    }

    public void setFilterWord(String filterWord) {
        this.filterWord = filterWord;
    }
    
    
}
