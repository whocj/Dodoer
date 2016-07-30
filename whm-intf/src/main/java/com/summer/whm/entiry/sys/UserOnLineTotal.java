package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class UserOnLineTotal extends BaseEntity {

    private Integer userId;

    private String username;

    /**
     * 最后登录时长
     */
    private Double onLineLatest;

    /**
     * 今日在线时长
     */
    private Double onLineDay;

    /**
     * 本周在线
     */
    private Double onLineWeek;

    /**
     * 本月在线
     */
    private Double onLineMonth;

    /**
     * 本年在线
     */
    private Double onLineYear;

    /**
     * 总在线时长
     */
    private Double onLineTotal;
    
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

    public Double getOnLineLatest() {
        return onLineLatest;
    }

    public void setOnLineLatest(Double onLineLatest) {
        this.onLineLatest = onLineLatest;
    }

    public Double getOnLineDay() {
        return onLineDay;
    }

    public void setOnLineDay(Double onLineDay) {
        this.onLineDay = onLineDay;
    }

    public Double getOnLineWeek() {
        return onLineWeek;
    }

    public void setOnLineWeek(Double onLineWeek) {
        this.onLineWeek = onLineWeek;
    }

    public Double getOnLineMonth() {
        return onLineMonth;
    }

    public void setOnLineMonth(Double onLineMonth) {
        this.onLineMonth = onLineMonth;
    }

    public Double getOnLineYear() {
        return onLineYear;
    }

    public void setOnLineYear(Double onLineYear) {
        this.onLineYear = onLineYear;
    }

    public Double getOnLineTotal() {
        return onLineTotal;
    }

    public void setOnLineTotal(Double onLineTotal) {
        this.onLineTotal = onLineTotal;
    }

    @Override
    public String toString() {
        return "UserOnLineTotal [userId=" + userId + ", username=" + username + ", onLineLatest=" + onLineLatest
                + ", onLineDay=" + onLineDay + ", onLineWeek=" + onLineWeek + ", onLineMonth=" + onLineMonth
                + ", onLineYear=" + onLineYear + ", onLineTotal=" + onLineTotal + "]";
    }
    
    
}
