package com.summer.whm.entiry.chats;

import com.summer.whm.entiry.BaseEntity;

public class ChatsKeys extends BaseEntity {
    private String chatkey;
    
    private String type;

    private String objId;

    public String getChatkey() {
        return chatkey;
    }

    public void setChatkey(String chatkey) {
        this.chatkey = chatkey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }
}
