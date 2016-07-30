package com.summer.whm.service.forum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.forum.ForumUser;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.forum.ForumUserMapper;
import com.summer.whm.service.BaseService;

@Service
public class ForumUserService extends BaseService {

    @Autowired
    private ForumUserMapper forumUserMapper;

    @Override
    protected BaseMapper getMapper() {
        return this.forumUserMapper;
    }

    public void cleanByForumId(Integer forumId) {
        forumUserMapper.cleanByForumId(forumId);
    }

    public List<ForumUser> queryByForumId(Integer forumId) {
        return forumUserMapper.queryByForumId(forumId);
    }
}
