package com.summer.whm.entiry.story;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.BaseEntity;

//小说专题
public class StoryTopic extends BaseEntity {

    private Integer categoryId;

    private String categoryName;

    //0初始化，1下线，2上线
    private String status;
    
    private String title;
    private String keywords;
    private String description;
    private String picPath;
    private Integer sortIndex;
    private Integer count;

    private PageModel<StoryInfo> storyInfoPage;
    
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public PageModel<StoryInfo> getStoryInfoPage() {
        return storyInfoPage;
    }

    public void setStoryInfoPage(PageModel<StoryInfo> storyInfoPage) {
        this.storyInfoPage = storyInfoPage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
