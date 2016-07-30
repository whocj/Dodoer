package com.summer.whm.entiry.chats;

import com.summer.whm.entiry.BaseEntity;

public class ChatsTopic extends BaseEntity {
    
    private int parentId;
    
    private String title;
    
    private String content;

    private String chatkey;
    
    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }
    
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
   
}
