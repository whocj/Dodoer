package com.summer.whm.service.post;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.common.utils.LocalIP;
import com.summer.whm.entiry.post.Post;
import com.summer.whm.entiry.post.PostText;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.post.PostMapper;
import com.summer.whm.mapper.post.PostTextMapper;
import com.summer.whm.mapper.post.TopicMapper;
import com.summer.whm.service.BaseService;

@Service
public class PostService extends BaseService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private PostTextMapper postTextMapper;
    
    @Autowired
    private HtmlUtils htmlUtils;
    
    public PageModel<Post> queryByTopicId(PageModel<Post> model) {
        List<Post> postList = postMapper.queryByTopicId(model);
        model.setContent(postList);
        return model;
    }

    //清缓存
//    @CacheEvict(value = "webCache", key = "#post.topicId + '@1@' + " + WebConstants.PAGE_SIZE + "+ 'TopicService.queryById'")
    public void newPost(Post post) {
        int postId = 0;
        
        post.setTitle(htmlUtils.replace(post.getTitle()));
        post.setContent(htmlUtils.replace(post.getContent()));
        
        post.setEditerIp(LocalIP.getLocalIP());
        post.setPosterIp(LocalIP.getLocalIP());
        post.setCreateTime(new Date());
        post.setStatus(GlobalConfigHolder.DEFAULT_POST_STATUS);
        postMapper.insert(post);
        postId = post.getId();
        
        Topic topic = new Topic();
        topic.setId(post.getTopicId());
        topic.setLastTime(new Date());
        topic.setLastPostId(postId);
        topic.setReplyCount(1);//递增
        topicMapper.update(topic);

        PostText postText = new PostText();
        postText.setCreateTime(new Date());
        postText.setCreator(post.getCreator());
        postText.setPostId(postId);
        postText.setPostContent(post.getContent());
        postTextMapper.insert(postText);
    }

    @Autowired
    private PostMapper postMapper;

    @Override
    protected BaseMapper getMapper() {
        return postMapper;
    }

}
