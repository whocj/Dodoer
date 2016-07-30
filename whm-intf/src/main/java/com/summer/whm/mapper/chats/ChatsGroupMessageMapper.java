package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsGroupMessage;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsGroupMessageMapper extends BaseMapper {
    List<ChatsGroupMessage> queryByGroupId(@Param("groupId") Integer groupId);
}
