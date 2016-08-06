package com.summer.whm.web.controller.bbs.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/bbs")
public class BBSIndexController extends BaseController {

    public static final Integer TOP_8 = 8;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private TopicService topicService; 

    @Autowired
    private FriendLinkService friendLinkService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        String skin = request.getParameter("skin");
        if (StringUtils.isNotEmpty(skin)) {
            request.getSession().setAttribute("SKIN", GlobalConfigHolder.SKIN_MAP.get(skin));
        }

        List<Category> categoryList = categoryService.queryBySite(GlobalConfigHolder.SITE_ID_BBS);
        List<FriendLink> friendLinkList =  friendLinkService.queryBySiteId(GlobalConfigHolder.SITE_ID_BBS);
        List<Topic> hotTopicList = topicService.queryTopHotTopic(TOP_8);
        List<Topic> replyTopicList = topicService.queryTopReply(TOP_8);
        List<Topic> topicList = topicService.queryTopTopic(TOP_8);
        
        model.put("hotTopicList", hotTopicList);
        model.put("replyTopicList", replyTopicList);
        model.put("topicList", topicList);
        model.put("categoryList", categoryList);
        
        model.put("friendLinkList", friendLinkList);
        model.put(GlobalConfigHolder.CURRENT_MENU_CODE, GlobalConfigHolder.MENU_BBS_CODE);
        return "bbs/index.ftl";
    }

}
