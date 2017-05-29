package com.summer.whm.web.controller.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.Constants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryTopic;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Notice;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryTopicService;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.NoticeService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.controller.BaseController;

@Controller
public class IndexController extends BaseController {

    public static final Integer TOP_10 = 10;

    public static final Integer TOP_20 = 20;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private StoryInfoService storyInfoService;
    
    @Autowired
    private NoticeService noticeService;
    
    @Autowired
    private StoryTopicService storyTopicService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_STORY);
        List<FriendLink> friendLinkList = friendLinkService.queryBySiteId(Constants.SITE_ID_STORY);
        
        //热门推荐的
        List<StoryInfo> hotList = storyInfoService.queryTopNByHot(null, 4);
        model.put("hotList", hotList);
        
        
        PageModel<Notice> noticePage = noticeService.list(1, 4);
        noticePage.insertQuery("siteId", Constants.SITE_ID_STORY);
        model.put("noticeList", noticePage.getContent());
        
        //最新更新的
        List<StoryInfo> lastUpdateStoryList = storyInfoService.queryStoryInfoOrderlastUpdateTop(null, TOP_20);
        
        //最新上架的
        List<StoryInfo> newStoryList = storyInfoService.queryStoryInfoOrderCreateTimeTop(null, TOP_20);
        
        //阅读最多的
        List<StoryInfo> readStoryList = storyInfoService.queryTopHot(null, TOP_10);
        //用户最喜欢
        List<StoryInfo> likeStoryList =  storyInfoService.queryTopLike(null, TOP_10);
        //评论最多
        List<StoryInfo> replyStoryList =  storyInfoService.queryTopReply(null, TOP_10);
        
        List<StoryTopic> storyTopicList = storyTopicService.queryByStatus("2", 12);
        
        model.put("storyTopicList", storyTopicList);
        
        model.put("replyStoryList", replyStoryList);
        model.put("readStoryList", readStoryList);
        model.put("likeStoryList", likeStoryList);
        
        model.put("lastUpdateStoryList", lastUpdateStoryList);
        model.put("newStoryList", newStoryList);

        model.put("friendLinkList", friendLinkList);
        model.put("tagList", tagList);

        return this.getForward(request, response, "index.ftl");
    }

}
