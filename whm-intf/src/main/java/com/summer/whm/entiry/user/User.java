package com.summer.whm.entiry.user;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class User extends BaseEntity {
    private String username;
    private String realname;
    private String nickname;
    private String password;
    private String email;
    /* 用户账号状态 */
    private String status;
    private String role;
    private String description;

    private String thirdAuthType;
    private String thirdAuthKey;
    private String thirdAuthToken;
    private Date thirdAuthTime;
    private String userLogo;
    private String userLogo50;
    private String userLogo100;
    
    //聊天室唯一标识
    private String chatkey;
    
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname == null ? username : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username == null ? nickname : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getThirdAuthType() {
        return thirdAuthType;
    }

    public void setThirdAuthType(String thirdAuthType) {
        this.thirdAuthType = thirdAuthType;
    }

    public String getThirdAuthKey() {
        return thirdAuthKey;
    }

    public void setThirdAuthKey(String thirdAuthKey) {
        this.thirdAuthKey = thirdAuthKey;
    }

    public Date getThirdAuthTime() {
        return thirdAuthTime;
    }

    public void setThirdAuthTime(Date thirdAuthTime) {
        this.thirdAuthTime = thirdAuthTime;
    }
    
    public String getThirdAuthToken() {
        return thirdAuthToken;
    }

    public void setThirdAuthToken(String thirdAuthToken) {
        this.thirdAuthToken = thirdAuthToken;
    }
    
    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", realname=" + realname + ", nickname=" + nickname + ", password="
                + password + ", email=" + email + ", status=" + status + ", role=" + role + ", description="
                + description + "]";
    }

    
}
