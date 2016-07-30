package com.summer.whm.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.mapper.BaseMapper;

public interface TagMapper extends BaseMapper {
    List<Tag> queryByIsSysAndSite(@Param("isSystem") String isSystem, @Param("siteId") Integer siteId);
    
    List<Tag> queryByCreatorAndSite(@Param("creator") String creator, @Param("siteId") Integer siteId);
}
