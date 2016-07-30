package com.summer.whm.mapper.blog;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.blog.BlogOptions;
import com.summer.whm.mapper.BaseMapper;

public interface BlogOptionsMapper extends BaseMapper {

    BlogOptions queryByCreatorId(@Param("creatorId") Integer creatorId);
    
    BlogOptions queryByCreator(@Param("creator") String creator);
}
