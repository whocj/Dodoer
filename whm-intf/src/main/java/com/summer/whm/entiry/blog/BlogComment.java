package com.summer.whm.entiry.blog;

import com.summer.whm.entiry.BaseEntity;

public class BlogComment extends BaseEntity {

    private Integer postId;
    /* 父评论ID */
    private String parent;
    
    private Integer creatorId;
    /* 评论者邮箱 */
    private String email;
    /* 评论者网址 */
    private String url;
    /* 评论者的userAgent */
    private String agent;
    /* 评论者IP */
    private String ip;
    /* 内容 */
    private String content;
    /* 评论状态 */
    private String status;

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

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
