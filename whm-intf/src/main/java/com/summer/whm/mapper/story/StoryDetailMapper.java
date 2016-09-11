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
    
    List<StoryDetail> queryById(@Param("id") Integer id, @Param("table") String table);
    
    public int insert(@Param("storyDetail") StoryDetail storyDetail,  @Param("table") String table);
    
    public int update(@Param("storyDetail") StoryDetail storyDetail,  @Param("table") String table);
    
    List<StoryDetail> queryByStoryId(@Param("storyId") Integer storyId, @Param("table") String table);

    int queryCountByStoryId(@Param("storyId") Integer storyId, @Param("table") String table);

    List<StoryDetail> queryByPartId(@Param("partId") Integer partId, @Param("table") String table);

    void addReadCount(@Param("id") Integer id, @Param("table") String table);

    void addReplyCount(@Param("id") Integer id, @Param("table") String table);

    StoryDetail queryNextByStoryAndId(@Param("storyId") Integer storyId, @Param("id") Integer id,
            @Param("table") String table);

    StoryDetail queryPrevByStoryAndId(@Param("storyId") Integer storyId, @Param("id") Integer id,
            @Param("table") String table);

    List<StoryDetail> queryByStoryIdAndContentIsNull(@Param("storyId") Integer storyId, @Param("table") String table);
}
