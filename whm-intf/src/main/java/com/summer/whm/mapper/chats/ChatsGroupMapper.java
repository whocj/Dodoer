package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsGroup;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsGroupMapper extends BaseMapper {
     List<ChatsGroup> queryByUsername(@Param("username") String username);
}
