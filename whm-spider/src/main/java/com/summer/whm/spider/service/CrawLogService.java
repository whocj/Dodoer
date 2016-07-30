package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawLog;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.CrawLogMapper;
import com.summer.whm.service.BaseService;

@Service
public class CrawLogService extends BaseService {

    @Autowired
    private CrawLogMapper crawLogMapper;

    public PageModel<CrawLog> list(int pageIndex, int pageSize) {
        PageModel<CrawLog> page = new PageModel<CrawLog>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.crawLogMapper;
    }

}
