package com.summer.whm.entiry.user;

import com.summer.whm.entiry.BaseEntity;

public class FriendShip extends BaseEntity {

    private Integer userId;
    
    private String username;
    
    private String nickname;
    
    private Integer friendId;
    
    private String friendUsername;

    /**
     * 好友状态(0:申请中;1:接受;2:拒绝)
     */
    private String status;
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    @Override
    public String toString() {
        return "FriendShip [userId=" + userId + ", nickname=" + nickname + ", friendId=" + friendId + ", status="
                + status + "]";
    }

    
}
