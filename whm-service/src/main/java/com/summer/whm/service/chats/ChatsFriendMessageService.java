package com.summer.whm.service.chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.chats.ChatsFriendMessage;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsFriendMessageMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsFriendMessageService extends BaseService {

    @Autowired
    private ChatsFriendMessageMapper chatsFriendMessageMapper;

    public String getMessageId(String username, String username2) {
        String messageId = null;
        if (username.compareTo(username2) < 0) {
            messageId = username + "@" + username2;
        } else {
            messageId = username2 + "@" + username;
        }
        
        return messageId;
    }

    public ChatsFriendMessage saveOrUpdate(ChatsFriendMessage chatsFriendMessage){
        if(chatsFriendMessage.isNew()){
            this.insert(chatsFriendMessage);
        }else{
            this.update(chatsFriendMessage);
        }
        return chatsFriendMessage;
    }
    
    public List<ChatsFriendMessage> queryByUsername(String username, String acceptname){
        String messageId = getMessageId(username, acceptname);
        return chatsFriendMessageMapper.queryByMessageId(messageId);
    }

    @Override
    protected BaseMapper getMapper() {
        return chatsFriendMessageMapper;
    }
}
