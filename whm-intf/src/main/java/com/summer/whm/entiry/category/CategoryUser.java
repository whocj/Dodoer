package com.summer.whm.entiry.category;

import com.summer.whm.entiry.BaseEntity;

public class CategoryUser extends BaseEntity {
    private Integer categoryId;
    private Integer userId;
    private String username;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

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

    @Override
    public String toString() {
        return "CategoryUser [categoryId=" + categoryId + ", userId=" + userId + ", username=" + username + "]";
    }
    
}
