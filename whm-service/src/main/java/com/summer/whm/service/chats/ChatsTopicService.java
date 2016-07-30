package com.summer.whm.service.chats;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.utils.ChatsKeysUtils;
import com.summer.whm.entiry.chats.ChatsKeys;
import com.summer.whm.entiry.chats.ChatsTopic;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsKeysMapper;
import com.summer.whm.mapper.chats.ChatsTopicMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsTopicService extends BaseService {

    @Autowired
    private ChatsTopicMapper chatsTopicMapper;

    @Autowired
    private ChatsKeysMapper chatsKeysMapper;

    public ChatsTopic saveOrUpdate(ChatsTopic chatsTopic){
        if(chatsTopic.isNew()){
            this.insert(chatsTopic);
            ChatsKeys chatsKeys = new ChatsKeys();
            chatsKeys.setChatkey(ChatsKeysUtils.createKey(chatsTopic.getId(), ChatsKeysUtils.CHATS_TYPE_TOPIC));
            chatsKeys.setCreateTime(new Date());
            chatsKeys.setCreator(chatsTopic.getCreator());
            chatsKeys.setLastUpdate(new Date());
            chatsKeys.setObjId(chatsTopic.getId() + "");
            chatsKeys.setType(ChatsKeysUtils.CHATS_TYPE_TOPIC);

            chatsKeysMapper.insert(chatsKeys);
        }else{
            this.update(chatsTopic);
        }

        return chatsTopic;
    }
    
    @Override
    protected BaseMapper getMapper() {
        return chatsTopicMapper;
    }

}
