package com.summer.whm.entiry.spider;

import com.summer.whm.entiry.BaseEntity;

public class SpiderStoryTemplate extends BaseEntity{

    private String name;
    private String userId;
    private String username;
    // 小说标题
    private String titleXPath;

    // 作者
    private String authorXPath;
    // 概要
    private String outlineXPath;
    // 图片
    private String picPathXPath;
    // 明细地址
    private String detailXPath;
    // 明细标题
    private String detailTitleXPath;
    // 明细内容
    private String detailContentXPath;
    // 下一页
    private String nextXPath;
    // 明细下一页
    private String detailNextXPath;
    // 执行模块
    private String execModel;
    // 过虑词
    private String filterWord;
    // 过滤行
    private String filterItem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitleXPath() {
        return titleXPath;
    }

    public void setTitleXPath(String titleXPath) {
        this.titleXPath = titleXPath;
    }

    public String getAuthorXPath() {
        return authorXPath;
    }

    public void setAuthorXPath(String authorXPath) {
        this.authorXPath = authorXPath;
    }

    public String getOutlineXPath() {
        return outlineXPath;
    }

    public void setOutlineXPath(String outlineXPath) {
        this.outlineXPath = outlineXPath;
    }

    public String getPicPathXPath() {
        return picPathXPath;
    }

    public void setPicPathXPath(String picPathXPath) {
        this.picPathXPath = picPathXPath;
    }

    public String getDetailXPath() {
        return detailXPath;
    }

    public void setDetailXPath(String detailXPath) {
        this.detailXPath = detailXPath;
    }

    public String getDetailTitleXPath() {
        return detailTitleXPath;
    }

    public void setDetailTitleXPath(String detailTitleXPath) {
        this.detailTitleXPath = detailTitleXPath;
    }

    public String getDetailContentXPath() {
        return detailContentXPath;
    }

    public void setDetailContentXPath(String detailContentXPath) {
        this.detailContentXPath = detailContentXPath;
    }

    public String getNextXPath() {
        return nextXPath;
    }

    public void setNextXPath(String nextXPath) {
        this.nextXPath = nextXPath;
    }

    public String getDetailNextXPath() {
        return detailNextXPath;
    }

    public void setDetailNextXPath(String detailNextXPath) {
        this.detailNextXPath = detailNextXPath;
    }

    public String getExecModel() {
        return execModel;
    }

    public void setExecModel(String execModel) {
        this.execModel = execModel;
    }

    public String getFilterWord() {
        return filterWord;
    }

    public void setFilterWord(String filterWord) {
        this.filterWord = filterWord;
    }

    public String getFilterItem() {
        return filterItem;
    }

    public void setFilterItem(String filterItem) {
        this.filterItem = filterItem;
    }
}
