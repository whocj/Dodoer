package com.summer.whm.entiry.story;

import com.summer.whm.entiry.BaseEntity;

//小说专题明细
public class StoryTopicDetail extends BaseEntity {

    private Integer topicId;

    private Integer storyId;

    private String title;

    private String author;

    private String status;

    private String statusTxt;
    private String categoryId;
    private String categoryName;
    private String picPath;
    
    private String outline;
    private Integer lastDetailId;
    private String lastDetailTitle;

    private Integer sortIndex;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusTxt() {
        return statusTxt;
    }

    public void setStatusTxt(String statusTxt) {
        this.statusTxt = statusTxt;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Integer getLastDetailId() {
        return lastDetailId;
    }

    public void setLastDetailId(Integer lastDetailId) {
        this.lastDetailId = lastDetailId;
    }

    public String getLastDetailTitle() {
        return lastDetailTitle;
    }

    public void setLastDetailTitle(String lastDetailTitle) {
        this.lastDetailTitle = lastDetailTitle;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

}
