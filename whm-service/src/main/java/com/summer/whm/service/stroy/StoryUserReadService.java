package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryUserRead;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryUserReadMapper;
import com.summer.whm.service.BaseService;

/**
 * 我的阅读记录
 */
@Service
public class StoryUserReadService extends BaseService {

    @Autowired
    private StoryUserReadMapper storyUserReadMapper;

    public List<StoryUserRead> queryByUsername(String username) {
        return storyUserReadMapper.queryByUsername(username);
    }

    public StoryUserRead queryByUserIdAndStoryId(Integer userId, Integer storyId) {
        StoryUserRead temp = storyUserReadMapper.queryByUserIdAndStoryId(userId, storyId);
        return temp;
    }

    public Integer save(StoryUserRead storyUserRead) {
        if (storyUserRead.isNew()) {
            StoryUserRead temp = storyUserReadMapper.queryByUserIdAndStoryId(storyUserRead.getUserId(),
                    storyUserRead.getStoryId());
            if (temp != null) {
                storyUserRead.setId(temp.getId());
                storyUserReadMapper.update(storyUserRead);
            }
            storyUserReadMapper.insert(storyUserRead);
        } else {
            storyUserReadMapper.update(storyUserRead);
        }

        return storyUserRead.getId();
    }

    @Override
    protected BaseMapper getMapper() {
        return this.storyUserReadMapper;
    }
}
