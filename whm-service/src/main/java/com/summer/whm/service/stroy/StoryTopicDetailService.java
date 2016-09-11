package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryTopicDetail;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryTopicDetailMapper;
import com.summer.whm.service.BaseService;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryTopicDetailService extends BaseService {

    @Autowired
    private StoryTopicDetailMapper storyTopicDetailMapper;

    public List<StoryTopicDetail> queryByTopicId(Integer topicId, Integer topN) {
        return storyTopicDetailMapper.queryByTopicId(topicId, topN);
    }

    public void cleanByTopicId(Integer topicId) {
        storyTopicDetailMapper.cleanByTopicId(topicId);
    }

    @Override
    protected BaseMapper getMapper() {
        return storyTopicDetailMapper;
    }
}
