package com.summer.whm.entiry.post;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

/**
 * 文章/页面,注：post的creator为userid
 */
public class Post extends BaseEntity {

    /**
     * 主题
     */
    private Integer topicId;

    /**
     * 站点
     */
    private Integer siteId;
    private Integer configId;

    /**
     * 编辑器会员
     */
    private Integer editerId;

    /**
     * 发贴会员
     */
    private Integer createrId;

    /**
     * 发贴IP
     */
    private String posterIp;

    /**
     * 编辑时间
     */
    private Date editTime = new Date();

    /**
     * 编辑者IP
     */
    private String editerIp;

    /**
     * 编辑次数
     */
    private Integer editCount = 0;

    /**
     * 第几楼
     */
    private Integer indexCount;

    /**
     * 状态
     */
    private Integer status;
    private Integer isAffix;

    /**
     * 是否有隐藏内容
     */
    private Integer isHidden = 0;

    /**
     * 帖子分类id
     */
    private Integer typeId;

    /**
     * 是否匿名
     */
    private Integer anonymous = 0;

    private String title;
    
    private String content;
    
    private String username;
    
    private String nickname;
    
    private String userLogo;
    private String userLogo50;
    private String userLogo100;
    
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getEditerId() {
        return editerId;
    }

    public void setEditerId(Integer editerId) {
        this.editerId = editerId;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getPosterIp() {
        return posterIp;
    }

    public void setPosterIp(String posterIp) {
        this.posterIp = posterIp;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditerIp() {
        return editerIp;
    }

    public void setEditerIp(String editerIp) {
        this.editerIp = editerIp;
    }

    public Integer getEditCount() {
        return editCount;
    }

    public void setEditCount(Integer editCount) {
        this.editCount = editCount;
    }

    public Integer getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAffix() {
        return isAffix;
    }

    public void setIsAffix(Integer isAffix) {
        this.isAffix = isAffix;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

    @Override
    public String toString() {
        return "Post [topicId=" + topicId + ", siteId=" + siteId + ", configId=" + configId + ", editerId=" + editerId
                + ", createrId=" + createrId + ", posterIp=" + posterIp + ", editTime=" + editTime + ", editerIp="
                + editerIp + ", editCount=" + editCount + ", indexCount=" + indexCount + ", status=" + status
                + ", isAffix=" + isAffix + ", isHidden=" + isHidden + ", typeId=" + typeId + ", anonymous=" + anonymous
                + "]";
    }

}
