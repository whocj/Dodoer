package com.summer.whm.service.post;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.common.utils.JsoupUtils;
import com.summer.whm.common.utils.LocalIP;
import com.summer.whm.common.utils.TDKModel;
import com.summer.whm.common.utils.TDKTools;
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.entiry.post.Post;
import com.summer.whm.entiry.post.PostText;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.entiry.user.User;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.forum.ForumMapper;
import com.summer.whm.mapper.post.PostMapper;
import com.summer.whm.mapper.post.PostTextMapper;
import com.summer.whm.mapper.post.TopicMapper;
import com.summer.whm.mapper.user.UserMapper;
import com.summer.whm.service.BaseService;
import com.summer.whm.service.search.SearchPostService;
import com.summer.whm.service.search.model.PostType;

@Service
public class TopicService extends BaseService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostTextMapper postTextMapper;

    @Autowired
    private SearchPostService searchPostService;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private ForumMapper forumMapper;

    @Autowired
    private HtmlUtils htmlUtils;
    
    public PageModel<Topic> queryAll(PageModel<Topic> page) {
        page.setContent(topicMapper.listAll(page));
        return page;
    }
    
    public PageModel<Topic> query(PageModel<Topic> page) {
        super.list(page);
        return page;
    }

//    @Cacheable(value = "webCache", key = "#id + '@' + #pageIndex + '@' + #pageSize + 'TopicService.queryById'")
    public Topic queryById(Integer id, Integer pageIndex, int pageSize) {
        Topic topic = topicMapper.loadById(id + "");
        if(topic == null){
            return null;
        }
        User user =  userMapper.loadById(topic.getCreaterId() + "");
        topic.setUser(user);
        PageModel<Post> page = new PageModel<Post>(pageIndex, pageSize);
        page.insertQuery("topicId", id);

        List<Post> postList = postMapper.queryByTopicId(page);
        page.setContent(postList);
        topic.setPostPage(page);
        
        Topic tempTopic = new Topic();
        tempTopic.setId(id);
        topicMapper.update(tempTopic);
        
        return topic;
    }

    public Integer newTopic(Topic topic) {
        int topicId = 0;
        int postId = 0;
        
        topic.setTitle(htmlUtils.replace(topic.getTitle()));
        topic.setContent(htmlUtils.replace(topic.getContent()));
        topic.setTagName(htmlUtils.replace(topic.getTagName()));
        
        topic.setStatus(GlobalConfigHolder.DEFAULT_POST_STATUS);
        topic.setPrimeLevel(0);
        
        TDKModel model = TDKTools.getTDK(new TDKModel(topic.getTitle(), topic.getContent(), ""));
        topic.setKeywords(model.getKeywords());
        topic.setDescription(model.getDescription());
        
        topicMapper.insert(topic);
        topicId = topic.getId();
        Post post = new Post();

        post.setEditerIp(LocalIP.getLocalIP());
        post.setPosterIp(LocalIP.getLocalIP());
        post.setTopicId(topicId);
        post.setCreateTime(new Date());
        post.setCreaterId(topic.getCreaterId());
        post.setStatus(GlobalConfigHolder.DEFAULT_POST_STATUS);
        postMapper.insert(post);
        postId = post.getId();

        Topic tempTopic = new Topic();
        tempTopic.setId(topicId);
        tempTopic.setFirstPostId(postId);
        tempTopic.setLastTime(new Date());
        tempTopic.setLastPostId(postId);
        topicMapper.update(tempTopic);

        PostText postText = new PostText();
        postText.setCreateTime(new Date());
        postText.setCreator(topic.getCreator());
        postText.setPostId(postId);
        postText.setPostTitle(topic.getTitle());
        postText.setPostContent(topic.getContent());
        postTextMapper.insert(postText);
        
        Forum forum = forumMapper.loadById(topic.getForumId() + "");
        Forum tempForum = new Forum();
        tempForum.setPostId(topicId);
        tempForum.setReplyerId(topic.getCreaterId());
        tempForum.setLastTime(new Date());
        tempForum.setTopicTotal("1");
        tempForum.setPostTotal("1");
        tempForum.setPostToday("1");
        tempForum.setId(forum.getId());
        
        forumMapper.update(tempForum);
        
        String tempExcerpt = JsoupUtils.plainText(topic.getContent());
        if(tempExcerpt == null){
            tempExcerpt = topic.getContent();
        }
        
        searchPostService.insert(new SearchPost(topic.getId() + "", PostType.POST_TYPE_TOPIC, 
                topic.getTitle(), tempExcerpt, topic.getUsername(), forum.getTitle() + "," + forum.getTitle()));

        return topicId;
    }
    
    public Integer save(Topic topic){
        if(topic.isNew()){
            newTopic(topic);
        }else{
            topicMapper.update(topic);
        }
        
        return topic.getId();
    }

    @Override
    protected BaseMapper getMapper() {
        return topicMapper;
    }

    @Cacheable(value = "webCache", key = "#num + 'TopicService.queryTopTopic'")
    public List<Topic> queryTopTopic(Integer num) {
        return topicMapper.queryTopTopic(num);
    }

    @Cacheable(value = "webCache", key = "#num + 'TopicService.queryTopReply'")
    public List<Topic> queryTopReply(Integer num) {
        return topicMapper.queryTopReply(num);
    }

    @Cacheable(value = "webCache", key = "#num + 'TopicService.queryTopHotTopic'")
    public List<Topic> queryTopHotTopic(Integer num) {
        return topicMapper.queryTopHotTopic(num);
    }
}
