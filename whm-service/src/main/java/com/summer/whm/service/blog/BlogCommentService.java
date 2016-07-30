package com.summer.whm.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.entiry.blog.BlogComment;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.blog.BlogCommentMapper;
import com.summer.whm.mapper.blog.BlogPostMapper;
import com.summer.whm.service.BaseService;

@Service
public class BlogCommentService extends BaseService{

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private BlogPostMapper blogPostMapper;
    
    @Autowired
    private HtmlUtils htmlUtils;
    
    @Override
    protected BaseMapper getMapper() {
        return this.blogCommentMapper;
    }

    public void save(BlogComment blogComment){
        
        blogComment.setContent(htmlUtils.replace(blogComment.getContent()));
        
        this.insert(blogComment);
        blogPostMapper.updateAddReply(blogComment.getPostId());
    }
}
