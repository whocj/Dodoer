package com.summer.whm.service.chats;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.utils.ChatsKeysUtils;
import com.summer.whm.entiry.chats.ChatsGroup;
import com.summer.whm.entiry.chats.ChatsKeys;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsGroupMapper;
import com.summer.whm.mapper.chats.ChatsKeysMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsGroupService extends BaseService {
    
    @Autowired
    private ChatsGroupMapper chatsGroupMapper;

    @Autowired
    private ChatsKeysMapper chatsKeysMapper;
    
    public ChatsGroup saveOrUpdate(ChatsGroup chatsGroup){
        if(chatsGroup.isNew()){
            this.insert(chatsGroup);
            ChatsKeys chatsKeys = new ChatsKeys();
            chatsKeys.setChatkey(ChatsKeysUtils.createKey(chatsGroup.getId(), ChatsKeysUtils.CHATS_TYPE_GROUP));
            chatsKeys.setCreateTime(new Date());
            chatsKeys.setCreator(chatsGroup.getCreator());
            chatsKeys.setLastUpdate(new Date());
            chatsKeys.setObjId(chatsGroup.getId() + "");
            chatsKeys.setType(ChatsKeysUtils.CHATS_TYPE_GROUP);
            
            chatsKeysMapper.insert(chatsKeys);
        }else{
            this.update(chatsGroup);
        }

        return chatsGroup;
    }
    
    @Override
    protected BaseMapper getMapper() {
        return chatsGroupMapper;
    }
}
