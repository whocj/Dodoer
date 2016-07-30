package com.summer.whm.mapper.story;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.story.StoryUserRead;
import com.summer.whm.mapper.BaseMapper;

/**
 * 我的阅读记录
 */
public interface StoryUserReadMapper extends BaseMapper {
    List<StoryUserRead> queryByUsername(@Param("username") String username);
}
