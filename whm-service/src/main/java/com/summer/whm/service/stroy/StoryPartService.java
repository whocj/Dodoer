package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryPart;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryPartMapper;
import com.summer.whm.service.BaseService;

/**
 * 小说段落
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryPartService extends BaseService {
    public List<StoryPart> queryByStoryId(Integer storyId) {
        return storyPartMapper.queryByStoryId(storyId);
    }

    @Autowired
    private StoryPartMapper storyPartMapper;

    @Override
    protected BaseMapper getMapper() {
        return storyPartMapper;
    }
}
