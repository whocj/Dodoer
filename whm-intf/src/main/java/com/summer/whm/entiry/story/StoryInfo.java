package com.summer.whm.entiry.story;

import java.util.List;

import com.summer.whm.entiry.BaseEntity;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StoryInfo extends BaseEntity {

    private Integer categoryId;

    private String title;
    private String author;
    private String status;
    private String picPath;
    private String outline;
    private Integer lastDetailId;
    private String lastDetailTitle;
    private String tagName;
    private int likeCount;
    private int readCount;
    private int replyCount;
    private int sortIndex;
    private String remark;
    private String crawlUrl;
    private String creator;

    private String keywords;
    private String description;

    private List<StoryPart> storyPartList;

    private List<StoryDetail> storyDetailList;

    private boolean part = false;// 是否有段落

    public boolean isPart() {
        return part;
    }

    public void setPart(boolean part) {
        this.part = part;
    }

    public List<StoryPart> getStoryPartList() {
        return storyPartList;
    }

    public void setStoryPartList(List<StoryPart> storyPartList) {
        this.storyPartList = storyPartList;
    }

    public List<StoryDetail> getStoryDetailList() {
        return storyDetailList;
    }

    public void setStoryDetailList(List<StoryDetail> storyDetailList) {
        this.storyDetailList = storyDetailList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCrawlUrl() {
        return crawlUrl;
    }

    public void setCrawlUrl(String crawlUrl) {
        this.crawlUrl = crawlUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    @Override
    public String toString() {
        return "StoryInfo [categoryId=" + categoryId + ", title=" + title + ", author=" + author + ", status=" + status
                + ", picPath=" + picPath + ", outline=" + outline + ", lastDetailId=" + lastDetailId
                + ", lastDetailTitle=" + lastDetailTitle + ", tagName=" + tagName + ", likeCount=" + likeCount
                + ", readCount=" + readCount + ", replyCount=" + replyCount + ", sortIndex=" + sortIndex + ", remark="
                + remark + ", crawlUrl=" + crawlUrl + ", creator=" + creator + ", keywords=" + keywords
                + ", description=" + description + ", storyPartList=" + storyPartList + ", storyDetailList="
                + storyDetailList + ", part=" + part + "]";
    }

}
