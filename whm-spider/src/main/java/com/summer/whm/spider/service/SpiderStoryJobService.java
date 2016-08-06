package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.SpiderStoryJobMapper;
import com.summer.whm.service.BaseService;

@Service
public class SpiderStoryJobService extends BaseService {

    @Autowired
    private SpiderStoryJobMapper spiderStoryJobMapper;

    @Override
    protected BaseMapper getMapper() {
        return spiderStoryJobMapper;
    }
 
    public PageModel<SpiderStoryJob> list(int pageIndex, int pageSize) {
        PageModel<SpiderStoryJob> page = new PageModel<SpiderStoryJob>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

}
