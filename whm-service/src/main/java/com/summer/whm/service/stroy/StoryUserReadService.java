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

    @Override
    protected BaseMapper getMapper() {
        return this.storyUserReadMapper;
    }
}
