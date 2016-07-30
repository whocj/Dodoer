package com.summer.whm.entiry.story;

import com.summer.whm.entiry.BaseEntity;

/**
 * 我的阅读记录
 */
public class StoryUserRead extends BaseEntity {

    private Integer storyId;

    private String title;

    private Integer userId;
    private String username;
    private Integer readDetailId;
    private String readDetailTitle;

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

}
