package com.summer.whm.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Authentication;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.AuthenticationMapper;
import com.summer.whm.service.BaseService;

@Service
public class AuthenticationService extends BaseService {

    @Autowired
    private AuthenticationMapper authenticationMapper;

    public PageModel<Authentication> list(int pageIndex, int pageSize) {
        PageModel<Authentication> page = new PageModel<Authentication>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.authenticationMapper;
    }

}
