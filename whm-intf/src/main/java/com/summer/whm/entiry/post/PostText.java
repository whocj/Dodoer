package com.summer.whm.entiry.post;

import com.summer.whm.entiry.BaseEntity;

public class PostText extends BaseEntity{
    
    private Integer postId;
    
    private String postTitle;
    
    private String postContent;

    private String postContentText;
    
    public String getPostContentText() {
        return postContentText;
    }

    public void setPostContentText(String postContentText) {
        this.postContentText = postContentText;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "PostText [postId=" + postId + ", postTitle=" + postTitle + ", postContent=" + postContent + "]";
    }
    
    
}
