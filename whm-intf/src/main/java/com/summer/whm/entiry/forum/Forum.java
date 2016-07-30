package com.summer.whm.entiry.forum;

import java.util.Date;
import java.util.List;

import com.summer.whm.entiry.BaseEntity;

public class Forum extends BaseEntity {
    private Integer categoryId;
    private Integer siteId;

    /**
     * 最后回帖
     */
    private Integer postId;

    /**
     * '最后回帖会员
     */
    private Integer replyerId;

    private String path;

    private String title;

    private String description;

    /**
     * meta-keywords
     */
    private String keywords;

    /**
     * 版规
     */
    private String forumRule;
    /**
     * 排列顺序
     */
    private String priority;

    /**
     * 主题总数
     */
    private String topicTotal;

    /**
     * 帖子总数
     */
    private String postTotal;

    /**
     * 今日新帖
     */
    private String postToday;

    /**
     * 外部链接
     */
    private String outerUrl;

    /**
     * 发贴加分
     */
    private int pointTopic;

    /**
     * 回帖加分
     */
    private int pointReply;

    /**
     * 精华加分
     */
    private int pointPrime;

    /**
     * 最后发贴时间
     */
    private Date lastTime;

    /**
     * 锁定主题（天）
     */
    private int topicLockLimit;

    /**
     * 版主
     */
    private String moderators;

    /**
     * 访问会员组
     */
    private String groupViews;

    /**
     * 发帖会员组
     */
    private String groupTopics;

    /**
     * 回复会员组
     */
    private String groupReplies;

    /**
     * 积分是否启用
     */
    private int pointAvailable;

    /**
     * 威望是否启用
     */
    private int prestigeAvailable;

    /**
     * 发帖加威望
     */
    private int prestigeTopic;

    /**
     * 回帖加威望
     */
    private int prestigeReply;

    /**
     * 精华1加威望
     */
    private int prestigePrime1;

    /**
     * 精华2加威望
     */
    private int prestigePrime2;

    /**
     * 精华3加威望
     */
    private int prestigePrime3;

    /**
     * 解除精华扣除威望
     */
    private Integer prestigePrime0;

    private List<ForumUser> forumUserList;

    private List<ForumGroupOpt> groupViewList;

    private List<ForumGroupOpt> groupTopicList;

    private List<ForumGroupOpt> groupReplyList;

    private String username;

    private String postTitle;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public List<ForumUser> getForumUserList() {
        return forumUserList;
    }

    public void setForumUserList(List<ForumUser> forumUserList) {
        this.forumUserList = forumUserList;
    }

    public List<ForumGroupOpt> getGroupViewList() {
        return groupViewList;
    }

    public void setGroupViewList(List<ForumGroupOpt> groupViewList) {
        this.groupViewList = groupViewList;
    }

    public List<ForumGroupOpt> getGroupTopicList() {
        return groupTopicList;
    }

    public void setGroupTopicList(List<ForumGroupOpt> groupTopicList) {
        this.groupTopicList = groupTopicList;
    }

    public List<ForumGroupOpt> getGroupReplyList() {
        return groupReplyList;
    }

    public void setGroupReplyList(List<ForumGroupOpt> groupReplyList) {
        this.groupReplyList = groupReplyList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getReplyerId() {
        return replyerId;
    }

    public void setReplyerId(Integer replyerId) {
        this.replyerId = replyerId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getForumRule() {
        return forumRule;
    }

    public void setForumRule(String forumRule) {
        this.forumRule = forumRule;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTopicTotal() {
        return topicTotal;
    }

    public void setTopicTotal(String topicTotal) {
        this.topicTotal = topicTotal;
    }

    public String getPostTotal() {
        return postTotal;
    }

    public void setPostTotal(String postTotal) {
        this.postTotal = postTotal;
    }

    public String getPostToday() {
        return postToday;
    }

    public void setPostToday(String postToday) {
        this.postToday = postToday;
    }

    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getModerators() {
        return moderators;
    }

    public void setModerators(String moderators) {
        this.moderators = moderators;
    }

    public String getGroupViews() {
        return groupViews;
    }

    public void setGroupViews(String groupViews) {
        this.groupViews = groupViews;
    }

    public String getGroupTopics() {
        return groupTopics;
    }

    public void setGroupTopics(String groupTopics) {
        this.groupTopics = groupTopics;
    }

    public String getGroupReplies() {
        return groupReplies;
    }

    public void setGroupReplies(String groupReplies) {
        this.groupReplies = groupReplies;
    }

    public Integer getPrestigeTopic() {
        return prestigeTopic;
    }

    public void setPrestigeTopic(Integer prestigeTopic) {
        this.prestigeTopic = prestigeTopic;
    }

    public Integer getPrestigeReply() {
        return prestigeReply;
    }

    public void setPrestigeReply(Integer prestigeReply) {
        this.prestigeReply = prestigeReply;
    }

    public Integer getPrestigePrime1() {
        return prestigePrime1;
    }

    public void setPrestigePrime1(Integer prestigePrime1) {
        this.prestigePrime1 = prestigePrime1;
    }

    public Integer getPrestigePrime2() {
        return prestigePrime2;
    }

    public void setPrestigePrime2(Integer prestigePrime2) {
        this.prestigePrime2 = prestigePrime2;
    }

    public Integer getPrestigePrime3() {
        return prestigePrime3;
    }

    public void setPrestigePrime3(Integer prestigePrime3) {
        this.prestigePrime3 = prestigePrime3;
    }

    public Integer getPrestigePrime0() {
        return prestigePrime0;
    }

    public void setPrestigePrime0(Integer prestigePrime0) {
        this.prestigePrime0 = prestigePrime0;
    }

    public int getPointTopic() {
        return pointTopic;
    }

    public void setPointTopic(int pointTopic) {
        this.pointTopic = pointTopic;
    }

    public int getPointReply() {
        return pointReply;
    }

    public void setPointReply(int pointReply) {
        this.pointReply = pointReply;
    }

    public int getPointPrime() {
        return pointPrime;
    }

    public void setPointPrime(int pointPrime) {
        this.pointPrime = pointPrime;
    }

    public int getTopicLockLimit() {
        return topicLockLimit;
    }

    public void setTopicLockLimit(int topicLockLimit) {
        this.topicLockLimit = topicLockLimit;
    }

    public int getPointAvailable() {
        return pointAvailable;
    }

    public void setPointAvailable(int pointAvailable) {
        this.pointAvailable = pointAvailable;
    }

    public int getPrestigeAvailable() {
        return prestigeAvailable;
    }

    public void setPrestigeAvailable(int prestigeAvailable) {
        this.prestigeAvailable = prestigeAvailable;
    }

    public void setPrestigeTopic(int prestigeTopic) {
        this.prestigeTopic = prestigeTopic;
    }

    public void setPrestigeReply(int prestigeReply) {
        this.prestigeReply = prestigeReply;
    }

    public void setPrestigePrime1(int prestigePrime1) {
        this.prestigePrime1 = prestigePrime1;
    }

    public void setPrestigePrime2(int prestigePrime2) {
        this.prestigePrime2 = prestigePrime2;
    }

    public void setPrestigePrime3(int prestigePrime3) {
        this.prestigePrime3 = prestigePrime3;
    }

    @Override
    public String toString() {
        return "Forum [categoryId=" + categoryId + ", siteId=" + siteId + ", postId=" + postId + ", replyerId="
                + replyerId + ", path=" + path + ", title=" + title + ", description=" + description + ", keywords="
                + keywords + ", forumRule=" + forumRule + ", priority=" + priority + ", topicTotal=" + topicTotal
                + ", postTotal=" + postTotal + ", postToday=" + postToday + ", outerUrl=" + outerUrl + ", pointTopic="
                + pointTopic + ", pointReply=" + pointReply + ", pointPrime=" + pointPrime + ", lastTime=" + lastTime
                + ", topicLockLimit=" + topicLockLimit + ", moderators=" + moderators + ", groupViews=" + groupViews
                + ", groupTopics=" + groupTopics + ", groupReplies=" + groupReplies + ", pointAvailable="
                + pointAvailable + ", prestigeAvailable=" + prestigeAvailable + ", prestigeTopic=" + prestigeTopic
                + ", prestigeReply=" + prestigeReply + ", prestigePrime1=" + prestigePrime1 + ", prestigePrime2="
                + prestigePrime2 + ", prestigePrime3=" + prestigePrime3 + ", prestigePrime0=" + prestigePrime0 + "]";
    }

}
