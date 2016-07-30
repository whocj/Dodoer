package com.summer.whm.service.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.post.PostTextMapper;
import com.summer.whm.service.BaseService;

@Service
public class PostTextService extends BaseService {

    @Autowired
    private PostTextMapper postTextMapper;

    @Override
    protected BaseMapper getMapper() {
        return postTextMapper;
    }

}
