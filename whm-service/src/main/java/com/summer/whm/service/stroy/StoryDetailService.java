package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryDetailMapper;
import com.summer.whm.service.BaseService;

/**
 * 
 * 小说明细
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryDetailService extends BaseService {

    @Autowired
    private StoryDetailMapper storyDetailMapper;

    @Override
    protected BaseMapper getMapper() {
        return storyDetailMapper;
    }

    public List<StoryDetail> queryByStoryIdAndContentIsNull(Integer storyId){
        return storyDetailMapper.queryByStoryIdAndContentIsNull(storyId);
    }
    
    public Integer save(StoryDetail storyDetail) {
        if (storyDetail.isNew()) {
            storyDetailMapper.insert(storyDetail);
        } else {
            storyDetailMapper.update(storyDetail);
        }

        return storyDetail.getId();
    }
    
    public StoryDetail queryNextByStoryAndId(Integer storyId, Integer id) {
        return storyDetailMapper.queryNextByStoryAndId(storyId, id);
    }

    public StoryDetail queryPrevByStoryAndId(Integer storyId, Integer id) {
        return storyDetailMapper.queryPrevByStoryAndId(storyId, id);
    }

    public void addReadCount(Integer id) {
        storyDetailMapper.addReadCount(id);
    }

    public void addReplyCount(Integer id) {
        storyDetailMapper.addReplyCount(id);
    }

    public List<StoryDetail> queryByStoryId(Integer storyId) {
        return storyDetailMapper.queryByStoryId(storyId);
    }

    public int queryCountByStoryId(Integer storyId){
        return storyDetailMapper.queryCountByStoryId(storyId);
    }
    
    public List<StoryDetail> queryByPartId(Integer partId) {
        return storyDetailMapper.queryByPartId(partId);
    }

}
