package com.summer.whm.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Site;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.SiteMapper;
import com.summer.whm.service.BaseService;

@Service
public class SiteService extends BaseService {

    @Autowired
    private SiteMapper siteMapper;

    public PageModel<Site> list(int pageIndex, int pageSize) {
        PageModel<Site> page = new PageModel<Site>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public List<Site> queryAll(){
        PageModel<Site> page = new PageModel<Site>(1, 10000);
        super.list(page);
        return page.getContent();
    }

    @Override
    protected BaseMapper getMapper() {
        return this.siteMapper;
    }

}
