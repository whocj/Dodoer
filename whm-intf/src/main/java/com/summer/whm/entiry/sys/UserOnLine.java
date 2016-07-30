package com.summer.whm.entiry.sys;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class UserOnLine extends BaseEntity {

    private Integer userId;

    private String username;

    private Date loginTime;

    private String loginIP;

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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    @Override
    public String toString() {
        return "UserOnLine [userId=" + userId + ", username=" + username + ", loginTime=" + loginTime + ", loginIP="
                + loginIP + "]";
    }

    
}
