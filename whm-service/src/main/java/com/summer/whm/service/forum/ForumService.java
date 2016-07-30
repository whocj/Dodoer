package com.summer.whm.service.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.forum.ForumMapper;
import com.summer.whm.service.BaseService;

@Service
public class ForumService extends BaseService {

    @Autowired
    private ForumMapper forumMapper;

    public PageModel<Forum> list(int pageIndex, int pageSize) {
        PageModel<Forum> page = new PageModel<Forum>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.forumMapper;
    }

}
