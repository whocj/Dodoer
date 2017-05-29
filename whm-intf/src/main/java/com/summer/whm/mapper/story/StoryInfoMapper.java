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
    
    List<StoryInfo> queryByTitleAndAuthor(@Param("title") String title, @Param("author") String author);
    
    List<StoryInfo> queryTopNByHot(@Param("categoryId") Integer categoryId, @Param("topN") Integer topN);

    List<StoryInfo> queryStoryInfoOrderCreateTimeTop(@Param("categoryId") Integer categoryId,  @Param("topN") Integer topN);
    
    List<StoryInfo> queryStoryInfoOrderlastUpdateTop(@Param("categoryId") Integer categoryId,  @Param("topN") Integer topN);
    
    List<StoryInfo> queryStoryInfoOrderSortIndexTop(@Param("categoryId") Integer categoryId,  @Param("topN") Integer topN);
    
    List<StoryInfo> queryTopReply(@Param("categoryId") Integer categoryId,  @Param("topN") Integer topN);
    
    List<StoryInfo> queryTopHot(@Param("categoryId") Integer categoryId,  @Param("topN") Integer topN);
    
    List<StoryInfo> queryTopLike(@Param("categoryId") Integer categoryId,  @Param("topN") Integer topN);

    List<StoryInfo> queryLatestTopN(@Param("topN") Integer topN);
    
    List<Integer> queryIdByCount(@Param("count") Integer count);
    
    List<String> queryAllAuthorByGTId(@Param("id") Integer id);
    
    List<StoryInfo> queryByAuthorName(@Param("author") String author);
    
    void addLike( @Param("id") Integer id);
    
    void addRead( @Param("id") Integer id);
    
    void addReply( @Param("id") Integer id);
}
