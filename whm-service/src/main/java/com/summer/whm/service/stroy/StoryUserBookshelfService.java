package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryUserRead;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryUserReadMapper;
import com.summer.whm.service.BaseService;

/**
 * 
 * 我的书架
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryUserBookshelfService extends BaseService {

    @Autowired
    private StoryUserReadMapper storyUserReadMapper;

    public List<StoryUserRead> queryByUsername(String username) {
        return storyUserReadMapper.queryByUsername(username);
    }

    @Override
    protected BaseMapper getMapper() {
        return storyUserReadMapper;
    }
}
