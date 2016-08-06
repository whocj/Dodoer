package com.summer.whm.web.controller.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.ask.AnswerService;
import com.summer.whm.service.ask.QuestionService;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Configs;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

@Controller
public class IndexController extends BaseController {

    public static final Integer TOP_8 = 8;
    
    public static final Integer TOP_30 = 30;

    @Autowired
    private TagService tagService;
    
    @Autowired
    private TopicService topicService; 
    
    @Autowired
    private QuestionService questionService;

    @Autowired
    private BlogPostService blogPostService;
    
    @Autowired
    private FriendLinkService friendLinkService;
    
    @Autowired
    private AnswerService answerService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        String skin = request.getParameter("skin");
        if (StringUtils.isNotEmpty(skin)) {
            // request.setAttribute("SKIN", Constants.SKIN_MAP.get(skin));
            request.getSession().setAttribute("SKIN", Constants.SKIN_MAP.get(skin));
        }

        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        List<Question> latestList = questionService.queryLatestTopNum(TOP_30);
        
        if(Configs.SITE_BBS_ENABLE){
            List<Topic> topicList = topicService.queryTopTopic(TOP_8);
            model.put("topicList", topicList);
        }

        if(Configs.SITE_BLOG_ENABLE){
            List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(TOP_8);
            model.put("blogPostList", blogPostList);
        }
        
        List<FriendLink> friendLinkList = friendLinkService.queryBySiteId(Constants.SITE_ID_ASK);
        List<Answer> answerList = answerService.queryTopNum(TOP_8);
        
        model.put("friendLinkList", friendLinkList);
        model.put("answerList", answerList);

        
        model.put("tagList", tagList);
        model.put("latestList", latestList);
        
        return "index.ftl";
    }

}
