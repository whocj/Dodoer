package com.summer.whm.mapper.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.author.AuthorDetail;
import com.summer.whm.mapper.BaseMapper;

/**
 * 
 * 作者小说关系<br>
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AuthorDetailMapper extends BaseMapper {
    List<AuthorDetail> queryByAuthorId(@Param("authorId") Integer authorId);
    
    List<AuthorDetail> queryByAuthorIdAndStoryId(@Param("authorId") Integer authorId, @Param("storyId")  Integer storyId);
    
    void deleteByAuthorIdAndStoryId(@Param("authorId") Integer authorId, @Param("storyId")  Integer storyId);
}
