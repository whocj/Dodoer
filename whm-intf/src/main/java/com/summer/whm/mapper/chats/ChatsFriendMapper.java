package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsFriend;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsFriendMapper extends BaseMapper {
    List<ChatsFriend> queryByUsername(@Param("username") String username);
}
