package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryUserBookshelf;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryUserBookshelfMapper;
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
    private StoryUserBookshelfMapper storyUserBookshelfMapper;

    public List<StoryUserBookshelf> queryByUserId(Integer username) {
        return storyUserBookshelfMapper.queryByUserId(username);
    }
    
    public  StoryUserBookshelf queryByUserIdAndStoryId(Integer userId, Integer storyId){
        return storyUserBookshelfMapper.queryByUserIdAndStoryId(userId, storyId);
    }

    public Integer save(StoryUserBookshelf storyUserBookshelf) {
        if (storyUserBookshelf.isNew()) {
            StoryUserBookshelf temp = storyUserBookshelfMapper.queryByUserIdAndStoryId(storyUserBookshelf.getUserId(),
                    storyUserBookshelf.getStoryId());
            if (temp != null) {
                storyUserBookshelf.setId(temp.getId());
                storyUserBookshelfMapper.update(storyUserBookshelf);
            } else {
                storyUserBookshelfMapper.insert(storyUserBookshelf);
            }
        } else {
            storyUserBookshelfMapper.update(storyUserBookshelf);
        }

        return storyUserBookshelf.getId();
    }

    @Override
    protected BaseMapper getMapper() {
        return storyUserBookshelfMapper;
    }
}
