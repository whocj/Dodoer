package com.summer.whm.mapper.author;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.author.Author;
import com.summer.whm.mapper.BaseMapper;

/**
 * 
 * 作者词条<br>
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface AuthorMapper extends BaseMapper {
    List<Author> queryByName(@Param("name") String name);
    
    List<Author> queryTopHot(@Param("categoryId") Integer categoryId, @Param("topN") Integer topN);
    
    List<Author> queryAll(@Param("categoryId") Integer categoryId);
    
    void addLike( @Param("id") Integer id);
    
    void addRead( @Param("id") Integer id);
    
    void addReply( @Param("id") Integer id);
}
