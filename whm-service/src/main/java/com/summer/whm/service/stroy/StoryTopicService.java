package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.story.StoryTopic;
import com.summer.whm.entiry.story.StoryTopicDetail;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryTopicMapper;
import com.summer.whm.service.BaseService;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryTopicService extends BaseService {

    @Autowired
    private StoryTopicMapper storyTopicMapper;

    @Autowired
    private StoryTopicDetailService storyTopicDetailService;

    public PageModel<StoryTopic> list(int pageIndex, int pageSize) {
        PageModel<StoryTopic> page = new PageModel<StoryTopic>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public StoryTopic queryById(Integer id) {
        StoryTopic storyTopic = storyTopicMapper.loadById(id + "");
        if (storyTopic != null) {
            List<StoryTopicDetail> detailList = storyTopicDetailService.queryByTopicId(id, 100);
            storyTopic.setTopicDetailList(detailList);
        }
        return storyTopic;
    }

    @Cacheable(value = "webCache", key = "#status + '@' + #topN + 'StoryTopicService.queryByStatus'")
    public List<StoryTopic> queryByStatus(String status, Integer topN) {
        List<StoryTopic> storyTopicList = storyTopicMapper.queryByStatus(status);
        if (storyTopicList != null && storyTopicList.size() > 0) {
            for (StoryTopic storyTopic : storyTopicList) {
                List<StoryTopicDetail>  topicDetailList = storyTopicDetailService.queryByTopicId(storyTopic.getId(), topN);
                storyTopic.setTopicDetailList(topicDetailList);
            }
        }

        return storyTopicList;
    }

    public Integer saveOrUpdate(StoryTopic storyTopic){
        if(storyTopic != null){
            if(storyTopic.isNew()){
                this.insert(storyTopic);
            }else{
                this.update(storyTopic);
            }
            return storyTopic.getId();
        }
        return null;
    }
    
    @Override
    protected BaseMapper getMapper() {
        return storyTopicMapper;
    }
}
