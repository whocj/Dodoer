package com.summer.whm.service.chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.chats.ChatsFriend;
import com.summer.whm.entiry.chats.ChatsFriendMessage;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsFriendMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsFriendService extends BaseService {
    
    @Autowired
    private ChatsFriendMapper chatsFriendMapper;

    public List<ChatsFriend> queryByUsername(String username){
        return this.chatsFriendMapper.queryByUsername(username);
    }

    public ChatsFriend saveOrUpdate(ChatsFriend chatsFriend){
        if(chatsFriend.isNew()){
            this.insert(chatsFriend);
        }else{
            this.update(chatsFriend);
        }
        
        return chatsFriend;
    }
    
    @Override
    protected BaseMapper getMapper() {
        return chatsFriendMapper;
    }
}
