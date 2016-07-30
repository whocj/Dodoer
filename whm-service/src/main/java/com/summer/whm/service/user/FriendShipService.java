package com.summer.whm.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.user.FriendShip;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.user.FriendShipMapper;
import com.summer.whm.service.BaseService;

@Service
public class FriendShipService extends BaseService {

    @Autowired
    private FriendShipMapper friendShipMapper;

    public PageModel<FriendShip> list(int pageIndex, int pageSize) {
        PageModel<FriendShip> page = new PageModel<FriendShip>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return friendShipMapper;
    }

}
