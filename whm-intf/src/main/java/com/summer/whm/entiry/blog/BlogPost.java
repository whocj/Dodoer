package com.summer.whm.entiry.blog;

import java.util.List;

import com.summer.whm.common.utils.TagUtils;
import com.summer.whm.entiry.BaseEntity;

public class BlogPost extends BaseEntity {

    private String title;

    /* 摘录,当type为页面该项为null */
    private String excerpt;

    private String content;
    
    private String contentText;

    private String type;
    
    private String tagName;
    
    private List<String> tagNameList;

    private Integer categoryId;
    /* 文章状态 */
    private String pstatus;
    /* 
     * 评论状态 
     * 0草稿
     * 1发布
     * 99垃圾箱
     * */
    private String cstatus;
    /* 评论数 */
    private Integer ccount;
    /* 阅读数 */
    private Integer rcount;

    private Integer creatorId;

    /* 最近一周评论数 */
    private Integer weekCCount;

    /* 最近一周阅读数 */
    private Integer weekRCount;

    //评论数据
    private List<BlogComment> blogCommentList; 

    private String keywords;
    
    private String description;
    
    private String userLogo;
    private String userLogo50;
    private String userLogo100;
    
    
    public List<String> getTagNameList() {
        if(tagNameList == null && this.tagName != null){
            tagNameList = TagUtils.getTagList(tagName);
        }
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public String getUserLogo50() {
        return userLogo50;
    }

    public void setUserLogo50(String userLogo50) {
        this.userLogo50 = userLogo50;
    }

    public String getUserLogo100() {
        return userLogo100;
    }

    public void setUserLogo100(String userLogo100) {
        this.userLogo100 = userLogo100;
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
    
    public List<BlogComment> getBlogCommentList() {
        return blogCommentList;
    }

    public void setBlogCommentList(List<BlogComment> blogCommentList) {
        this.blogCommentList = blogCommentList;
    }

    public Integer getWeekCCount() {
        return weekCCount;
    }

    public void setWeekCCount(Integer weekCCount) {
        this.weekCCount = weekCCount;
    }

    public Integer getWeekRCount() {
        return weekRCount;
    }

    public void setWeekRCount(Integer weekRCount) {
        this.weekRCount = weekRCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public Integer getCcount() {
        return ccount;
    }

    public void setCcount(Integer ccount) {
        this.ccount = ccount;
    }

    public Integer getRcount() {
        return rcount;
    }

    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
        tagNameList = TagUtils.getTagList(tagName);
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    
}
