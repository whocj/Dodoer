package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.SpiderStoryTemplateMapper;
import com.summer.whm.service.BaseService;

@Service
public class SpiderStoryTemplateService extends BaseService {

    @Autowired
    private SpiderStoryTemplateMapper spiderStoryTemplateMapper;

    @Override
    protected BaseMapper getMapper() {
        return spiderStoryTemplateMapper;
    }

    public PageModel<SpiderStoryTemplate> list(int pageIndex, int pageSize) {
        PageModel<SpiderStoryTemplate> page = new PageModel<SpiderStoryTemplate>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

}
