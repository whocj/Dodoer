package com.summer.whm.entiry.post;

import java.util.Date;
import java.util.List;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.TagUtils;
import com.summer.whm.entiry.BaseEntity;
import com.summer.whm.entiry.user.User;

public class Topic extends BaseEntity{
    
    /**
     * 板块
     */
    private Integer  forumId;
    
    /**
     * 最后帖
     */
    private Integer lastPostId;
    
    /**
     * 主题帖
     */
    private Integer firstPostId;
    
    /**
     * 站点
     */
    private Integer  siteId;
    
    /**
     * 发帖会员
     */
    private Integer createrId;
    
    /**
     * 最后回帖会员
     */
    private Integer replyerId;
    
    /**
     * 标题
     */
    private String title;
    
    private String tagName;
    
    private List<String> tagNameList;
    
    /**
     * 创建时间
     */
    private Date lastTime = new Date();
    
    /**
     * 用于排序
     */
    private Date sortTime;
    
    /**
     * 浏览次数
     */
    private Integer viewCount = 0;
    
    /**
     * 回复次数
     */
    private Integer replyCount = 0;
    
    /**
     * 固定级别
     */
    private Integer topLevel = 0;
    
    /**
     * 精华级别
     */
    private Integer primeLevel = 0;
    
    /**
     * 状态
     */
    private Integer status;
    
    /**
     * 外部链接
     */
    private String outerUrl;
    
    /**
     * 粗体
     */
    private Integer styleBold;
    
    /**
     * 斜体
     */
    private Integer styleItalic;
    
    /**
     * 颜色
     */
    private String styleColor;
    
    /**
     * 样式有效时间
     */
    private Integer styleTime;
    
    /**
     * 是否上传附件
     */
    private Integer isAffix;
    
    /**
     * 主题分类id
     */
    private Integer typeId;
    
    /**
     * 主题是否允许回复
     */
    private Integer allayReply;
    
    /**
     * 主题是否已经被抢走沙发
     */
    private Integer hasSofaed;
    
    /**
     * 帖子类型(0:普通帖;1:投票贴)
     */
    private Integer category;
    
    /**
     * 总票数
     */
    private Integer totalCount;
    
    private String username;
    
    private PageModel<Post> postPage;
    
    private String content;
    
    private List<Post> postList;
    
    private String keywords;
    
    private String description;
    
    private User user;
    
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
        tagNameList = TagUtils.getTagList(tagName);
    }

    public List<String> getTagNameList() {
        if(tagNameList == null && this.tagName != null){
            tagNameList = TagUtils.getTagList(tagName);
        }
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PageModel<Post> getPostPage() {
        return postPage;
    }

    public void setPostPage(PageModel<Post> postPage) {
        this.postPage = postPage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Integer getLastPostId() {
        return lastPostId;
    }

    public void setLastPostId(Integer lastPostId) {
        this.lastPostId = lastPostId;
    }

    public Integer getFirstPostId() {
        return firstPostId;
    }

    public void setFirstPostId(Integer firstPostId) {
        this.firstPostId = firstPostId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getSortTime() {
        return sortTime;
    }

    public void setSortTime(Date sortTime) {
        this.sortTime = sortTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(Integer topLevel) {
        this.topLevel = topLevel;
    }

    public Integer getPrimeLevel() {
        return primeLevel;
    }

    public void setPrimeLevel(Integer primeLevel) {
        this.primeLevel = primeLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public Integer getStyleBold() {
        return styleBold;
    }

    public void setStyleBold(Integer styleBold) {
        this.styleBold = styleBold;
    }

    public Integer getStyleItalic() {
        return styleItalic;
    }

    public void setStyleItalic(Integer styleItalic) {
        this.styleItalic = styleItalic;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public Integer getStyleTime() {
        return styleTime;
    }

    public void setStyleTime(Integer styleTime) {
        this.styleTime = styleTime;
    }

    public Integer getIsAffix() {
        return isAffix;
    }

    public void setIsAffix(Integer isAffix) {
        this.isAffix = isAffix;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAllayReply() {
        return allayReply;
    }

    public void setAllayReply(Integer allayReply) {
        this.allayReply = allayReply;
    }

    public Integer getHasSofaed() {
        return hasSofaed;
    }

    public void setHasSofaed(Integer hasSofaed) {
        this.hasSofaed = hasSofaed;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Topic [forumId=" + forumId + ", lastPostId=" + lastPostId + ", firstPostId=" + firstPostId
                + ", siteId=" + siteId + ", createrId=" + createrId + ", replyerId=" + replyerId + ", title=" + title
                + ", lastTime=" + lastTime + ", sortTime=" + sortTime + ", viewCount=" + viewCount + ", replyCount="
                + replyCount + ", topLevel=" + topLevel + ", primeLevel=" + primeLevel + ", status=" + status
                + ", outerUrl=" + outerUrl + ", styleBold=" + styleBold + ", styleItalic=" + styleItalic
                + ", styleColor=" + styleColor + ", styleTime=" + styleTime + ", isAffix=" + isAffix + ", typeId="
                + typeId + ", allayReply=" + allayReply + ", hasSofaed=" + hasSofaed + ", category=" + category
                + ", totalCount=" + totalCount + "]";
    }

    
}
