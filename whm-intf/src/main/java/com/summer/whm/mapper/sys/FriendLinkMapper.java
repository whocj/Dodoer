package com.summer.whm.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.mapper.BaseMapper;

public interface FriendLinkMapper extends BaseMapper {
    List<FriendLink> queryBySiteId(@Param("siteId") Integer siteId);
    
    List<FriendLink> queryByCreator(@Param("creator") String creator);
}
