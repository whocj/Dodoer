package com.summer.whm.mapper.blog;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.mapper.BaseMapper;

public interface BlogPostMapper extends BaseMapper {
    List<BlogPost> queryHotBlogPost(PageModel<BlogPost> page);
    
    List<BlogPost> queryLatestTopNum(@Param("num") Integer num);
    
    List<BlogPost> queryByCreator(PageModel<BlogPost> page);
    
    void updateAddReply(@Param("id") Integer id);
    
    void updateAddView(@Param("id") Integer id);
    
    List<String> queryTitle(PageModel<String> model);
    
    List<Integer> queryIdByCount(@Param("count") Integer count);
    
    BlogPost queryNextByCreatorAndId(@Param("creator") String creator, @Param("id") Integer id);
    
    BlogPost queryPrevByCreatorAndId(@Param("creator") String creator, @Param("id") Integer id);
}
