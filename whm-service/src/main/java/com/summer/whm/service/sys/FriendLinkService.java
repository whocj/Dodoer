package com.summer.whm.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.sys.FriendLinkMapper;
import com.summer.whm.service.BaseService;

@Service
public class FriendLinkService extends BaseService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    public PageModel<FriendLink> list(int pageIndex, int pageSize) {
        PageModel<FriendLink> page = new PageModel<FriendLink>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Cacheable(value = "webCache", key = "#siteId + 'FriendLinkService.queryBySiteId'")
    public List<FriendLink> queryBySiteId(Integer siteId) {
        return friendLinkMapper.queryBySiteId(siteId);
    }

    @Cacheable(value = "webCache", key = "#creator + 'FriendLinkService.queryByCreator'")
    public List<FriendLink> queryByCreator(String creator) {
        return friendLinkMapper.queryByCreator(creator);
    }

    @Override
    protected BaseMapper getMapper() {
        return this.friendLinkMapper;
    }

}
