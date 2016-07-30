package com.summer.whm.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.UserOnLine;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.UserOnLineMapper;
import com.summer.whm.service.BaseService;

@Service
public class UserOnLineService extends BaseService {

    @Autowired
    private UserOnLineMapper userOnLineMapper;

    public PageModel<UserOnLine> list(int pageIndex, int pageSize) {
        PageModel<UserOnLine> page = new PageModel<UserOnLine>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.userOnLineMapper;
    }

}
