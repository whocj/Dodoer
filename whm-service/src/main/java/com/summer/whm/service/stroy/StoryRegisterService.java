package com.summer.whm.service.stroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.story.StoryRegister;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryRegisterMapper;
import com.summer.whm.service.BaseService;

@Service
public class StoryRegisterService extends BaseService {

    @Autowired
    private StoryRegisterMapper storyRegisterMapper;

    public Integer save(StoryRegister storyRegister) {
        if (storyRegister.isNew()) {
            storyRegisterMapper.insert(storyRegister);
        } else {
            storyRegisterMapper.update(storyRegister);
        }

        return storyRegister.getId();
    }

    @Override
    protected BaseMapper getMapper() {
        return storyRegisterMapper;
    }

}
