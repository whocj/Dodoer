package com.summer.whm.mapper.blog;

import java.util.List;

import com.summer.whm.entiry.blog.BlogComment;
import com.summer.whm.mapper.BaseMapper;

public interface BlogCommentMapper extends BaseMapper {
    List<BlogComment> queryBlogCommentByPostId(Integer postId);
}
