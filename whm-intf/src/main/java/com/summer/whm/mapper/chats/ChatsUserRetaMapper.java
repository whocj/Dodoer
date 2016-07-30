package com.summer.whm.mapper.chats;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.chats.ChatsUserReta;
import com.summer.whm.mapper.BaseMapper;

public interface ChatsUserRetaMapper extends BaseMapper {
    List<ChatsUserReta> queryByUsername(@Param("username") String username);
}
