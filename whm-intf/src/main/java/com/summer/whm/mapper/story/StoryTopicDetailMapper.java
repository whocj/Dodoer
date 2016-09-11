package com.summer.whm.mapper.story;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.story.StoryTopicDetail;
import com.summer.whm.mapper.BaseMapper;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface StoryTopicDetailMapper extends BaseMapper {
    List<StoryTopicDetail> queryByTopicId(@Param("topicId") Integer topicId, @Param("topN") Integer topN);

    void cleanByTopicId(@Param("topicId") Integer topicId);
}
