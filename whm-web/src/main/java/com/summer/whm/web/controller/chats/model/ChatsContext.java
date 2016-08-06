package com.summer.whm.web.controller.chats.model;

import com.summer.whm.web.controller.chats.ChatsFriendSocket;

public class ChatsContext {
    private String username;

    private String nickname;

    private String chatkey;

    private ChatsFriendSocket friendSocket;

    public ChatsContext() {
        super();
    }

    public ChatsContext(String username, String nickname, String chatkey, ChatsFriendSocket friendSocket) {
        super();
        this.username = username;
        this.nickname = nickname;
        this.chatkey = chatkey;
        this.friendSocket = friendSocket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }

    public ChatsFriendSocket getFriendSocket() {
        return friendSocket;
    }

    public void setFriendSocket(ChatsFriendSocket friendSocket) {
        this.friendSocket = friendSocket;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((friendSocket == null) ? 0 : friendSocket.hashCode());
        result = prime * result + ((chatkey == null) ? 0 : chatkey.hashCode());
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChatsContext other = (ChatsContext) obj;
        if (friendSocket == null) {
            if (other.friendSocket != null)
                return false;
        } else if (!friendSocket.equals(other.friendSocket))
            return false;
        if (chatkey == null) {
            if (other.chatkey != null)
                return false;
        } else if (!chatkey.equals(other.chatkey))
            return false;
        if (nickname == null) {
            if (other.nickname != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ChatsContext [username=" + username + ", nickname=" + nickname + ", chatkey=" + chatkey + ", friendSocket="
                + friendSocket + "]";
    }
    
}
