package com.summer.whm.entiry.chats;

import com.summer.whm.entiry.BaseEntity;

public class ChatsFriendMessage extends BaseEntity {

    private String messageId;
    
    private String username;

    private String nickname;
    
    private String acceptNickname;
    
    private String acceptname;

    private String content;

    // 0未读，1已读
    private String status;
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAcceptNickname() {
        return acceptNickname;
    }

    public void setAcceptNickname(String acceptNickname) {
        this.acceptNickname = acceptNickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAcceptname() {
        return acceptname;
    }

    public void setAcceptname(String acceptname) {
        this.acceptname = acceptname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
}
