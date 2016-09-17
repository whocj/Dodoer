package com.summer.whm.entiry.story;

import com.summer.whm.entiry.BaseEntity;

public class StoryRegister extends BaseEntity {
    private Integer categoryId;
    private String categoryName;
    private Integer storyId;
    private String title;
    private String author;
    private String status;
    private String statusTxt;
    private String remark;

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusTxt() {
        if (status != null) {
            if ("0".equals(status)) {
                statusTxt = "<font color=\"#395996\">(已申请)</font>";
            } else if ("1".equals(status)) {
                statusTxt = "<font color=\"#00ff45\">(收录中)</font>";
            } else if ("2".equals(status)) {
                statusTxt = "<font color=\"red\">(已收录)</font>";
            } else if ("3".equals(status)) {
                statusTxt = "(版本限制，请阅读正版)";
            } else {
                statusTxt = "<font color=\"#00ff45\">(处理中)</font>";
            }
        } else {
            statusTxt = "<font color=\"#395996\">(已申请)</font>";
        }

        return statusTxt;
    }

    public void setStatusTxt(String statusTxt) {
        this.statusTxt = statusTxt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
