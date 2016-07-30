package com.summer.whm.service.chats;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.chats.ChatsKeys;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.chats.ChatsKeysMapper;
import com.summer.whm.service.BaseService;

@Service
public class ChatsKeysService extends BaseService {

    @Autowired
    private ChatsKeysMapper chatsKeysMapper;

    public ChatsKeys queryByChatkey(String chatkey) {
        List<ChatsKeys> list = chatsKeysMapper.queryByChatkey(chatkey);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public ChatsKeys queryByObjIdAndType(String objId, String type) {
        List<ChatsKeys> list = chatsKeysMapper.queryByObjIdAndType(objId, type);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public void deleteByChatkey(String chatkey) {
        chatsKeysMapper.deleteByChatkey(chatkey);
    }

    public void deleteByObjIdAndType(String objId, String type) {
        chatsKeysMapper.deleteByObjIdAndType(objId, type);
    }

    @Override
    protected BaseMapper getMapper() {
        return chatsKeysMapper;
    }
}
