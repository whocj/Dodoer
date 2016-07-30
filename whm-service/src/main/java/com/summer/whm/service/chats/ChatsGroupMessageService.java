package com.summer.whm.service.chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.chats.ChatsGroupMessage;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsGroupMessageMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsGroupMessageService extends BaseService {
    
    @Autowired
    private ChatsGroupMessageMapper chatsGroupMessageMapper;
    
    public  List<ChatsGroupMessage> queryByGroupId(Integer groupId){
        return chatsGroupMessageMapper.queryByGroupId(groupId);
    }

    @Override
    protected BaseMapper getMapper() {
        return chatsGroupMessageMapper;
    }
    
    
}
