package com.summer.whm.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.blog.BlogOptions;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.blog.BlogOptionsMapper;
import com.summer.whm.service.BaseService;

@Service
public class BlogOptionsService extends BaseService{

    @Autowired
    private BlogOptionsMapper blogOptionsMapper;
    
    public BlogOptions queryByCreator(String creator){
        BlogOptions blogOptions = blogOptionsMapper.queryByCreator(creator);
        
        if(blogOptions == null){
            blogOptions = new BlogOptions();
            
            blogOptions.setCreator(creator);
            blogOptions.setDescription("我的地盘，我做主。");
            blogOptions.setKeywords("keyword");
            blogOptions.setSubTitle("天王盖地虎，请问下一句是什么？");
            blogOptions.setTitle(creator + "的博客！");
        }
        
        return blogOptions;
    }
    
    @Override
    protected BaseMapper getMapper() {
        return this.blogOptionsMapper;
    }

}
