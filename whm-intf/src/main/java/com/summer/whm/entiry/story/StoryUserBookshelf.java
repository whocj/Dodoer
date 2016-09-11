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
    private String author;
    private String  storyStatus;
    private String statusTxt;
    
    private String outline;
    private Integer lastDetailId;
    private String lastDetailTitle;
    private String tagName;
    private int likeCount;
    private int readCount;
    private int replyCount;
    
    public String getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(String storyStatus) {
        this.storyStatus = storyStatus;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatusTxt() {
        if(statusTxt == null){
            statusTxt = "3".equals(this.getStoryStatus()) ? "<font color='red'>(完结)</font>" : "";
        }
        return statusTxt;
    }

    public void setStatusTxt(String statusTxt) {
        this.statusTxt = statusTxt;
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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

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
