package com.summer.whm.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.LoginLog;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.LoginLogMapper;
import com.summer.whm.service.BaseService;

@Service
public class LoginLogService extends BaseService {

    @Autowired
    private  LoginLogMapper loginLogMapper;

    public PageModel<LoginLog> list(int pageIndex, int pageSize) {
        PageModel<LoginLog> page = new PageModel<LoginLog>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.loginLogMapper;
    }

}
