package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.ListTemplateMapper;
import com.summer.whm.service.BaseService;

@Service
public class ListTemplateService extends BaseService {

    @Autowired
    private ListTemplateMapper listTemplateMapper;

    @Autowired
    private CrawTemplateService crawTemplateService;

    public PageModel<ListTemplate> list(int pageIndex, int pageSize) {
        PageModel<ListTemplate> page = new PageModel<ListTemplate>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public ListTemplate queryById(String id) {
        ListTemplate listTemplate = getMapper().loadById(id);

        if (listTemplate.getCrawTemplateId() != null) {
            CrawTemplate crawTemplate = crawTemplateService.loadById(listTemplate.getCrawTemplateId() + "");
            listTemplate.setCrawTemplate(crawTemplate);
        }

        return listTemplate;
    }

    public ListTemplate queryByCrawTemplateId(int crawTemplateId) {
        return listTemplateMapper.queryByCrawTemplateId(crawTemplateId);
    }

    @Override
    protected BaseMapper getMapper() {
        return this.listTemplateMapper;
    }

}
