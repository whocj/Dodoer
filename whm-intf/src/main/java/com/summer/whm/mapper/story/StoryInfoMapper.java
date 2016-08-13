package com.summer.whm.mapper.story;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.mapper.BaseMapper;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface StoryInfoMapper extends BaseMapper {
    
    List<StoryInfo> queryTopNByHot(@Param("categoryId") Integer categoryId, @Param("topN") Integer topN);
    
    List<StoryInfo> queryTopNByOrderAndCategoryId(@Param("categoryId") Integer categoryId,
            @Param("orderField") String orderField, @Param("topN") Integer topN);

    List<StoryInfo> queryTopNByOrder(@Param("orderField") String orderField, @Param("topN") Integer topN);

    void addLike( @Param("id") Integer id);
    
    void addRead( @Param("id") Integer id);
    
    void addReply( @Param("id") Integer id);
}
