package com.summer.whm.mapper.post;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.mapper.BaseMapper;

public interface TopicMapper extends BaseMapper{

    List<Topic> queryTopTopic(@Param("num") Integer num);
    
    List<Topic> queryTopReply(@Param("num") Integer num);
    
    List<Topic> queryTopHotTopic(@Param("num") Integer num);
    
    List<String> queryTitle(PageModel<String> model);
    
    List<Topic> listAll(PageModel<Topic> model);
    
    List<Integer> queryIdByCount(@Param("count") Integer count);
}
