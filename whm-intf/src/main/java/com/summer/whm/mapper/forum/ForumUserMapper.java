package com.summer.whm.mapper.forum;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.forum.ForumUser;
import com.summer.whm.mapper.BaseMapper;

public interface ForumUserMapper extends BaseMapper{
    void cleanByForumId(@Param("forumId") Integer forumId);

    List<ForumUser> queryByForumId(@Param("forumId") Integer forumId);
}
