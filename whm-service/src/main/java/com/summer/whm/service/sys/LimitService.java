package com.summer.whm.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Limit;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.LimitMapper;
import com.summer.whm.service.BaseService;

@Service
public class LimitService extends BaseService {

    @Autowired
    private LimitMapper limitMapper;

    public PageModel<Limit> list(int pageIndex, int pageSize) {
        PageModel<Limit> page = new PageModel<Limit>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.limitMapper;
    }

}
