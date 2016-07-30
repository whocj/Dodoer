package com.summer.whm.mapper.forum;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.mapper.BaseMapper;

public interface ForumMapper extends BaseMapper {
    List<Forum> queryByCategoryId(@Param("categoryId") Integer categoryId);
}
