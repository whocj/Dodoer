package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawInfo;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.CrawInfoMapper;
import com.summer.whm.service.BaseService;

@Service
public class CrawInfoService extends BaseService {

    @Autowired
    private CrawInfoMapper crawInfoMapper;

    public PageModel<CrawInfo> list(int pageIndex, int pageSize) {
        PageModel<CrawInfo> page = new PageModel<CrawInfo>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public CrawInfo queryByUrl(String url) {
        return crawInfoMapper.queryByUrl(url);
    }

    @Override
    protected BaseMapper getMapper() {
        return this.crawInfoMapper;
    }

}
