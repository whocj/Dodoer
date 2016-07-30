package com.summer.whm.mapper.forum;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.forum.ForumGroupOpt;
import com.summer.whm.mapper.BaseMapper;

public interface ForumGroupOptMapper extends BaseMapper {
    void cleanByForumId(@Param("forumId") Integer forumId);

    List<ForumGroupOpt> queryByForumId(@Param("forumId") Integer forumId);

    List<ForumGroupOpt> queryByForumIdAndType(@Param("forumId") Integer forumId, @Param("type") String type);
}
