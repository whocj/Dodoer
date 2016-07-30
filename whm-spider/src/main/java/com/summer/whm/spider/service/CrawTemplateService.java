package com.summer.whm.spider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.spider.CrawTemplateMapper;
import com.summer.whm.mapper.spider.DetailTemplateMapper;
import com.summer.whm.mapper.spider.ListTemplateMapper;
import com.summer.whm.service.BaseService;

@Service
public class CrawTemplateService extends BaseService {

    public static final String STATUS_DEFAULT = "Default";

    public static final String STATUS_WAIT = "Wait";
    
    public static final String STATUS_RUN = "Run";

    public static final String STATUS_STOP = "Stop";

    public static final String STATUS_ERROR = "Error";

    @Autowired
    private CrawTemplateMapper crawTemplateMapper;
    
    @Autowired
    private DetailTemplateMapper detailTemplateMapper;
    
    @Autowired
    private ListTemplateMapper listTemplateMapper;

    public PageModel<CrawTemplate> list(int pageIndex, int pageSize) {
        PageModel<CrawTemplate> page = new PageModel<CrawTemplate>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public CrawTemplate queryById(Integer id){
        CrawTemplate crawTemplate = this.loadById(id + "");
        DetailTemplate detailTemplate = detailTemplateMapper.queryByCrawTemplateId(crawTemplate.getId());
        crawTemplate.setDetailTemplate(detailTemplate);
        
        ListTemplate listTemplate = listTemplateMapper.queryByCrawTemplateId(crawTemplate.getId());
        crawTemplate.setListTemplate(listTemplate);
        
        return crawTemplate;
    }
    
    @Override
    protected BaseMapper getMapper() {
        return this.crawTemplateMapper;
    }

}
