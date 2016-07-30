package com.summer.whm.service.forum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.forum.ForumGroupOpt;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.forum.ForumGroupOptMapper;
import com.summer.whm.service.BaseService;

@Service
public class ForumGroupOptService extends BaseService {

    // 回复
    public static final String FORUM_GROUP_REPLY = "Reply";

    // 发贴
    public static final String FORUM_GROUP_TOPIC = "Topic";

    // 查看
    public static final String FORUM_GROUP_VIEW = "View";

    @Autowired
    private ForumGroupOptMapper forumGroupOptMapper;

    @Override
    protected BaseMapper getMapper() {
        return this.forumGroupOptMapper;
    }

    public void cleanByForumId(Integer forumId) {
        forumGroupOptMapper.cleanByForumId(forumId);
    }

    public Map<String, List<ForumGroupOpt>> queryByForumId(Integer forumId) {
        Map<String, List<ForumGroupOpt>> map = new HashMap<String, List<ForumGroupOpt>>();

        List<ForumGroupOpt> replyList = queryByForumIdAndType(forumId, FORUM_GROUP_REPLY);
        map.put(FORUM_GROUP_REPLY, replyList);
        List<ForumGroupOpt> topicList = queryByForumIdAndType(forumId, FORUM_GROUP_TOPIC);
        map.put(FORUM_GROUP_TOPIC, topicList);
        List<ForumGroupOpt> viewList = queryByForumIdAndType(forumId, FORUM_GROUP_VIEW);
        map.put(FORUM_GROUP_VIEW, viewList);
        
        return map;
    }

    public List<ForumGroupOpt> queryByForumIdAndType(Integer forumId, String type) {
        return forumGroupOptMapper.queryByForumIdAndType(forumId, type);
    }

}
