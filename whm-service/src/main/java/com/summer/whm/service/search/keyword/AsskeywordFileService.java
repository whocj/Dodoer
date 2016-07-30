package com.summer.whm.service.search.keyword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.summer.whm.mapper.ask.QuestionTextMapper;
import com.summer.whm.mapper.blog.BlogPostMapper;
import com.summer.whm.mapper.post.TopicMapper;

public class AsskeywordFileService {
    private Logger log = LoggerFactory.getLogger(AsskeywordFileService.class);
    
    private BlogPostMapper blogPostMapper;
    
    private TopicMapper topicMapper;
    
    private QuestionTextMapper questionTextMapper;
    
    
}
