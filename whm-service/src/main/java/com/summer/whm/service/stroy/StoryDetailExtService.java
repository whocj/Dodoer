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
public class StoryDetailExtService extends BaseService {

    public static final String DEFAULT_TABLE = "story_detail";
    
    //小说明细分表hash值
    public static final int HASH_VAL = 5000;
    
    @Autowired
    private StoryDetailMapper storyDetailMapper;

    @Override
    protected BaseMapper getMapper() {
        return storyDetailMapper;
    }

    public String getTable(Integer storyId){
        if(storyId != null){
            int val = storyId / HASH_VAL;
            if(val == 0){
                return DEFAULT_TABLE;
            }else{
                return DEFAULT_TABLE + "_" + val;
            }
        }

        return DEFAULT_TABLE;
    }
    
    public List<StoryDetail> queryByStoryIdAndContentIsNull(Integer storyId){
        return storyDetailMapper.queryByStoryIdAndContentIsNull(storyId, getTable(storyId));
    }
    
    public Integer save(StoryDetail storyDetail) {
        if (storyDetail.isNew()) {
            storyDetailMapper.insert(storyDetail, getTable(storyDetail.getStoryId()));
        } else {
            storyDetailMapper.update(storyDetail, getTable(storyDetail.getStoryId()));
        }

        return storyDetail.getId();
    }
    
    public StoryDetail queryNextByStoryAndId(Integer storyId, Integer id) {
        return storyDetailMapper.queryNextByStoryAndId(storyId, id, getTable(storyId));
    }

    public StoryDetail queryPrevByStoryAndId(Integer storyId, Integer id) {
        return storyDetailMapper.queryPrevByStoryAndId(storyId, id, getTable(storyId));
    }

    public void addReadCount(Integer id, Integer storyId) {
        storyDetailMapper.addReadCount(id, getTable(storyId));
    }

    public void addReplyCount(Integer id, Integer storyId) {
        storyDetailMapper.addReplyCount(id, getTable(storyId));
    }

    public List<StoryDetail> queryByStoryId(Integer storyId) {
        return storyDetailMapper.queryByStoryId(storyId, getTable(storyId));
    }

    public int queryCountByStoryId(Integer storyId){
        return storyDetailMapper.queryCountByStoryId(storyId, getTable(storyId));
    }
    
    public List<StoryDetail> queryByPartId(Integer partId, Integer storyId) {
        return storyDetailMapper.queryByPartId(partId, getTable(storyId));
    }

}
