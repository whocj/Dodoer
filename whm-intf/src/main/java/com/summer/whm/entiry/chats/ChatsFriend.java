package com.summer.whm.entiry.chats;

import com.summer.whm.entiry.BaseEntity;

public class ChatsFriend extends BaseEntity {
    
    private String username;
    
    private String friendname;
    
    private String friendNickname;
    
    private String remarkName;

    private String chatkey;
    
    public ChatsFriend() {
        super();
    }

    public ChatsFriend(String username, String friendname) {
        super();
        this.username = username;
        this.friendname = friendname;
    }

    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }
    
    public String getFriendNickname() {
        return friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }
}
