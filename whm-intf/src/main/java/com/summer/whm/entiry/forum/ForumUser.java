package com.summer.whm.entiry.forum;

import com.summer.whm.entiry.BaseEntity;

public class ForumUser extends BaseEntity{
    private Integer forumId;
    
    private Integer userId;

    private String username;
    
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ForumUser [forumId=" + forumId + ", userId=" + userId + ", username=" + username + "]";
    }
    
}
