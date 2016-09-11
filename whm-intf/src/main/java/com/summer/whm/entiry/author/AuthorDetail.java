package com.summer.whm.entiry.author;

import com.summer.whm.entiry.BaseEntity;

/**
 * 
 * 作者小说关系<br>
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AuthorDetail extends BaseEntity {

    private Integer authorId;

    private Integer storyId;
    
    private String picPath;
    private Integer categoryId;
    private String categoryName;
    private String storyStatus;
    private String statusTxt;
    private Integer lastDetailId;
    private String lastDetailTitle;
    private int likeCount;
    private int readCount;
    private int replyCount;
    
    private String title;
    private String outline;
    private String createDate;// 创作时间

    
    
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    public String getStoryStatus() {
        return storyStatus;
    }

    public void setStoryStatus(String storyStatus) {
        this.storyStatus = storyStatus;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
