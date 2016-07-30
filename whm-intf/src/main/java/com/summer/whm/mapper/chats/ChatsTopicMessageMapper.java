package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsTopicMessage;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsTopicMessageMapper extends BaseMapper {
    List<ChatsTopicMessage> queryByTopicId(@Param("topicId") Integer topicId);
}
