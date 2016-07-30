package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryPart;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryDetailMapper;
import com.summer.whm.mapper.story.StoryInfoMapper;
import com.summer.whm.mapper.story.StoryPartMapper;
import com.summer.whm.service.BaseService;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryInfoService extends BaseService {

    @Autowired
    private StoryInfoMapper storyInfoMapper;
    
    @Autowired
    private StoryPartMapper storyPartMapper;
    
    @Autowired
    private StoryDetailMapper storyDetailMapper;
    
    public StoryInfo queryById(Integer storyId){
        StoryInfo storyInfo = storyInfoMapper.loadById(storyId + "");
        if(storyInfo != null){
            List<StoryPart> storyPartList = storyPartMapper.queryByStoryId(storyId);
            if(storyPartList != null && storyPartList.size() > 0){
                for(StoryPart storyPart : storyPartList){
                    List<StoryDetail> storyDetailList = storyDetailMapper.queryByPartId(storyPart.getId());
                    storyPart.setStoryDetailList(storyDetailList);
                }
                storyInfo.setStoryPartList(storyPartList);
                storyInfo.setPart(true);
            }else{
                List<StoryDetail> storyDetailList = storyDetailMapper.queryByStoryId(storyId);
                storyInfo.setStoryDetailList(storyDetailList);
            }
        }
        
        return storyInfo;
    }
    
    
    
    @Override
    protected BaseMapper getMapper() {
        return storyInfoMapper;
    }

}
