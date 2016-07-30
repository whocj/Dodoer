package com.summer.whm.mapper.story;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.mapper.BaseMapper;

/**
 * 
 * 小说明细
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface StoryDetailMapper extends BaseMapper {
    List<StoryDetail> queryByStoryId(@Param("storyId") Integer storyId);

    List<StoryDetail> queryByPartId(@Param("partId") Integer partId);
    
    void addReadCount(@Param("id") Integer id);
    
    void addReplyCount(@Param("id") Integer id);
}
