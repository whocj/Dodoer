package com.summer.whm.entiry.story;

import com.summer.whm.entiry.BaseEntity;

/**
 * 
 * 我的书架
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StoryUserBookshelf extends BaseEntity {

    private Integer storyId;

    private String categoryName;
    private String title;
    private String picPath;
    private Integer userId;
    private String username;
    private Integer readDetailId;
    private String readDetailTitle;
    private String status;
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    public Integer getReadDetailId() {
        return readDetailId;
    }

    public void setReadDetailId(Integer readDetailId) {
        this.readDetailId = readDetailId;
    }

    public String getReadDetailTitle() {
        return readDetailTitle;
    }

    public void setReadDetailTitle(String readDetailTitle) {
        this.readDetailTitle = readDetailTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
