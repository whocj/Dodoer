package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsFriendMessage;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsFriendMessageMapper extends BaseMapper {
    List<ChatsFriendMessage> queryByMessageId(@Param("messageId") String messageId);
}
