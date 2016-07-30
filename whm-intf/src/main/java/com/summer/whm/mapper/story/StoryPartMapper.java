package com.summer.whm.mapper.story;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.story.StoryPart;
import com.summer.whm.mapper.BaseMapper;

/**
 * 小说段落
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface StoryPartMapper extends BaseMapper {
    List<StoryPart> queryByStoryId(@Param("storyId") Integer storyId);
}
