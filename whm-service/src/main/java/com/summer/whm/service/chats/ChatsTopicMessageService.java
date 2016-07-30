package com.summer.whm.service.chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.chats.ChatsTopicMessage;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsTopicMessageMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsTopicMessageService extends BaseService {
    
    @Autowired
    private ChatsTopicMessageMapper chatsTopicMessageMapper;
    
    public List<ChatsTopicMessage> queryByTopicId(Integer topicId){
        return chatsTopicMessageMapper.queryByTopicId(topicId);
    }

    @Override
    protected BaseMapper getMapper() {
        return chatsTopicMessageMapper;
    }
}
