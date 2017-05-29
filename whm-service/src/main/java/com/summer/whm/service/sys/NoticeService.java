package com.summer.whm.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Notice;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.NoticeMapper;
import com.summer.whm.service.BaseService;

@Service
public class NoticeService extends BaseService {

    @Autowired
    private NoticeMapper noticeMapper;
    
    @Override
    protected BaseMapper getMapper() {
        return this.noticeMapper;
    }
    
    @Cacheable(value = "webCache", key = "#pageIndex + '@' + #pageSize + 'NoticeService.list'")
    public PageModel<Notice> list(int pageIndex, int pageSize) {
        PageModel<Notice> page = new PageModel<Notice>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public List<Notice> queryBySiteId(Integer siteId) {
        return noticeMapper.queryBySiteId(siteId);
    }
}
