package com.summer.whm.spider.service;

import java.util.List;

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

    public SpiderStoryJob queryByUrl(String url) {
        return spiderStoryJobMapper.queryByUrl(url);
    }
    
    public List<SpiderStoryJob> queryByTempateIdAndStatus(Integer templateId, String status) {
        return spiderStoryJobMapper.queryByTempateIdAndStatus(templateId, status);
    }
    
    public SpiderStoryJob queryByTempateIdAndStatusTop1(Integer templateId, String status) {
        List<SpiderStoryJob> list = spiderStoryJobMapper.queryByTempateIdAndStatusTop1(templateId, status);
        if(list != null){
            return list.get(0);
        }
        
        return null;
    }

    
    
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
