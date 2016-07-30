package com.summer.whm.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.UserOnLineTotal;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.UserOnLineTotalMapper;
import com.summer.whm.service.BaseService;

@Service
public class UserOnLineTotalService extends BaseService {

    @Autowired
    private UserOnLineTotalMapper userOnLineTotalMapper;

    public PageModel<UserOnLineTotal> list(int pageIndex, int pageSize) {
        PageModel<UserOnLineTotal> page = new PageModel<UserOnLineTotal>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.userOnLineTotalMapper;
    }

}
