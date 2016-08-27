package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryTopic;
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
            PageModel<StoryInfo> page = new PageModel<StoryInfo>(1, WebConstants.PAGE_SIZE);
            page.insertQuery("topic", id);
            storyTopicDetailService.list(page);
            storyTopic.setStoryInfoPage(page);
        }
        return storyTopic;
    }

    public List<StoryTopic> queryByStatus(String status) {
        List<StoryTopic> storyTopicList = storyTopicMapper.queryByStatus(status);
        if (storyTopicList != null && storyTopicList.size() > 0) {
            for (StoryTopic storyTopic : storyTopicList) {
                PageModel<StoryInfo> page = new PageModel<StoryInfo>(1, WebConstants.PAGE_SIZE);
                page.insertQuery("topic", storyTopic.getId());
                storyTopicDetailService.list(page);
                storyTopic.setStoryInfoPage(page);
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
