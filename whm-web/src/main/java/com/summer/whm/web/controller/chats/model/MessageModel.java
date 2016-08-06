package com.summer.whm.web.controller.chats.model;

public class MessageModel {
    /*
     * "{"+ "chatkey:" + chatkey + ",acceptName:" + friendName + ",type:" + type + ",username:" + username + ",content:"
     * + msg + "}";
     */
    //接受人key
    private String chatkey;

    //接受人
    private String acceptName;

    //消息类型
    private String type;

    //发送人
    private String username;
    //发送人key
    private String userChatkey;

    //消息内容
    private String content;
    
    public String getUserChatkey() {
        return userChatkey;
    }

    public void setUserChatkey(String userChatkey) {
        this.userChatkey = userChatkey;
    }

    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "MessageModel [chatkey=" + chatkey + ", acceptName=" + acceptName + ", type=" + type + ", username="
                + username + ", userChatkey=" + userChatkey + ", content=" + content + "]";
    }
    
}
