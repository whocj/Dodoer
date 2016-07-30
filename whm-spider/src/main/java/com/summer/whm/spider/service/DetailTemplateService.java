package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.CrawTemplateMapper;
import com.summer.whm.mapper.spider.DetailTemplateMapper;
import com.summer.whm.service.BaseService;

@Service
public class DetailTemplateService extends BaseService {

    @Autowired
    private DetailTemplateMapper detailTemplateMapper;

    @Autowired
    private CrawTemplateMapper crawTemplateMapper;

    public PageModel<DetailTemplate> list(int pageIndex, int pageSize) {
        PageModel<DetailTemplate> page = new PageModel<DetailTemplate>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public DetailTemplate queryById(String id) {
        DetailTemplate detailTemplate = getMapper().loadById(id);

        if (detailTemplate.getCrawTemplateId() != null) {
            CrawTemplate crawTemplate = crawTemplateMapper.loadById(detailTemplate.getCrawTemplateId() + "");
            detailTemplate.setCrawTemplate(crawTemplate);
        }

        return detailTemplate;
    }

    public DetailTemplate queryByCrawTemplateId(int crawTemplateId) {
       return detailTemplateMapper.queryByCrawTemplateId(crawTemplateId);
    }

    @Override
    protected BaseMapper getMapper() {
        return this.detailTemplateMapper;
    }

}
