package com.summer.whm.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.TagMapper;
import com.summer.whm.service.BaseService;

@Service
public class TagService extends BaseService {

    @Autowired
    private TagMapper tagMapper;

    public PageModel<Tag> list(int pageIndex, int pageSize) {
        PageModel<Tag> page = new PageModel<Tag>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Cacheable(value = "webCache", key = "#isSystem + '@' + #siteId + 'TagService.queryByIsSysAndSite'")
    public List<Tag> queryByIsSysAndSite(String isSystem, Integer siteId){
        return tagMapper.queryByIsSysAndSite(isSystem, siteId);
    }
    
    @Cacheable(value = "webCache", key = "#creator + '@' + #siteId + 'TagService.queryByCreatorAndSite'")
    public List<Tag> queryByCreatorAndSite(String creator, Integer siteId){
        return tagMapper.queryByCreatorAndSite(creator, siteId);
    }
    
    @Override
    protected BaseMapper getMapper() {
        return this.tagMapper;
    }

}
