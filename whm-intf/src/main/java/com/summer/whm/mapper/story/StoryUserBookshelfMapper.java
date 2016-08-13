package com.summer.whm.mapper.story;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.story.StoryUserBookshelf;
import com.summer.whm.mapper.BaseMapper;

/**
 * 
 * 我的书架
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface StoryUserBookshelfMapper extends BaseMapper {
    List<StoryUserBookshelf> queryByUsername(@Param("username") String username);

    StoryUserBookshelf queryByUserIdAndStoryId(@Param("userId") Integer userId, @Param("storyId") Integer storyId);
}
