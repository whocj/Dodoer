package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsKeys;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsKeysMapper extends BaseMapper {
    List<ChatsKeys> queryByChatkey(@Param("chatkey") String chatkey);

    List<ChatsKeys> queryByObjIdAndType(@Param("objId") String objId, @Param("type") String type);
    
    void deleteByChatkey(@Param("chatkey") String chatkey);
    
    void deleteByObjIdAndType(@Param("objId") String objId, @Param("type") String type);
}
