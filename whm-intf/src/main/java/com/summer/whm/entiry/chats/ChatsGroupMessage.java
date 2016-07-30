package com.summer.whm.entiry.chats;

import com.summer.whm.entiry.BaseEntity;

public class ChatsGroupMessage extends BaseEntity {
    
    private int groupId;

    private String username;
    
    private String nickname;

    private String content;
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
