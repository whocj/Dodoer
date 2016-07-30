package com.summer.whm.entiry.chats;

import com.summer.whm.entiry.BaseEntity;

public class ChatsUserReta extends BaseEntity {
    
    public static final String TOPIC_TYPE = "Topic";
    
    public static final String GROUP_TYPE = "Group";
    
    private int retaId;
    
    private String retaName;
    
    private String groupName;
    
    private String topicName;
    
    private String username;
    
    //0没有未读消息 ，>0有多少条消息未读
    private String messageStauts;
    
    //type:['Group','Topic']
    private String type;
    
    private String chatkey;
    
    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getRetaName() {
        if(retaName == null || "".equals(retaName)){
            if(TOPIC_TYPE.equals(type)){
                retaName = this.getTopicName();
            }else if(GROUP_TYPE.equals(type)){
                retaName = this.getGroupName();
            }
        }
        
        return retaName;
    }

    public void setRetaName(String retaName) {
        this.retaName = retaName;
    }

    public int getRetaId() {
        return retaId;
    }

    public void setRetaId(int retaId) {
        this.retaId = retaId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessageStauts() {
        return messageStauts;
    }

    public void setMessageStauts(String messageStauts) {
        this.messageStauts = messageStauts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
