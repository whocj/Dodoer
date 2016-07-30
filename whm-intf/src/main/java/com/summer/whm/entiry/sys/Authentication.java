package com.summer.whm.entiry.sys;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class Authentication extends BaseEntity {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Authentication [userId=" + userId + ", username=" + username + ", email=" + email + ", loginTime="
                + loginTime + ", loginIp=" + loginIp + ", updateTime=" + updateTime + "]";
    }

}
