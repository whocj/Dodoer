package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class OnLineTotal extends BaseEntity{

    private Integer userId;
    private String username;
    private Integer onLineLatest;
    private Integer onLineDay;
    private Integer onLineWeek;
    private Integer onLineMonth;
    private Integer onLineYear;
    private Integer onLineTotal;

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

    public Integer getOnLineLatest() {
        return onLineLatest;
    }

    public void setOnLineLatest(Integer onLineLatest) {
        this.onLineLatest = onLineLatest;
    }

    public Integer getOnLineDay() {
        return onLineDay;
    }

    public void setOnLineDay(Integer onLineDay) {
        this.onLineDay = onLineDay;
    }

    public Integer getOnLineWeek() {
        return onLineWeek;
    }

    public void setOnLineWeek(Integer onLineWeek) {
        this.onLineWeek = onLineWeek;
    }

    public Integer getOnLineMonth() {
        return onLineMonth;
    }

    public void setOnLineMonth(Integer onLineMonth) {
        this.onLineMonth = onLineMonth;
    }

    public Integer getOnLineYear() {
        return onLineYear;
    }

    public void setOnLineYear(Integer onLineYear) {
        this.onLineYear = onLineYear;
    }

    public Integer getOnLineTotal() {
        return onLineTotal;
    }

    public void setOnLineTotal(Integer onLineTotal) {
        this.onLineTotal = onLineTotal;
    }
}
