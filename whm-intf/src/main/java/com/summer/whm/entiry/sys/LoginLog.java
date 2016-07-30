package com.summer.whm.entiry.sys;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class LoginLog extends BaseEntity {

    /**
     * 登录用户
     */
    private Integer userId;
    
    private String username;
    
    /**
     * 登录时间
     */
    private Date loginTime;
    
    /**
     * 退出时间
     */
    private Date logoutTime;
    
    /**
     * 登录ip
     */
    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "LoginLog [userId=" + userId + ", username=" + username + ", loginTime=" + loginTime + ", logoutTime="
                + logoutTime + ", ip=" + ip + "]";
    }
    
    
}
