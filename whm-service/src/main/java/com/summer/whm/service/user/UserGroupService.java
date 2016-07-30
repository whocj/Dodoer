package com.summer.whm.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.user.UserGroup;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.user.UserGroupMapper;
import com.summer.whm.service.BaseService;

@Service
public class UserGroupService extends BaseService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    public PageModel<UserGroup> list(int pageIndex, int pageSize) {
        PageModel<UserGroup> page = new PageModel<UserGroup>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return userGroupMapper;
    }

}
