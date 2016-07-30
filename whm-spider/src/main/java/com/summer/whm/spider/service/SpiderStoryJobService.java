package com.summer.whm.spider.service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.SpiderStoryJobMapper;
import com.summer.whm.service.BaseService;

public class SpiderStoryJobService extends BaseService {

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
