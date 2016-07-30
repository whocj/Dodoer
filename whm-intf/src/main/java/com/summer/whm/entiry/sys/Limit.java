package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class Limit extends BaseEntity{

    /**
     * 限制ip
     */
    private String ip;
    
    /**
     * 限制用户ID
     */
    private Integer userId;
    
    private String username;
    
    private String remark;
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
