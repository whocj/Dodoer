package com.summer.whm.service.chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.chats.ChatsUserReta;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsUserRetaMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsUserRetaService extends BaseService {
    
    @Autowired
    private ChatsUserRetaMapper chatsUserRetaMapper;
    
    public   List<ChatsUserReta> queryByUsername(String username){
        return chatsUserRetaMapper.queryByUsername(username);
    }

    @Override
    protected BaseMapper getMapper() {
        return chatsUserRetaMapper;
    }
}
