package com.summer.whm.web.controller.bbs.topic;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.entiry.post.Post;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.forum.ForumService;
import com.summer.whm.service.post.PostService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/bbs/topic")
public class TopicController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(TopicController.class);
    public static final Integer TOP_5 = 5;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ForumService forumService;

    @Autowired
    private PostService postService;

    // 回复
    public final static int TYPE_REPLY = 2;
    // 引用
    public final static int TYPE_QUOTE = 1;

    @RequestMapping("/listIndex/{forumId}/{primeLevel}/{currentPage}")
    public String listIndex(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("currentPage") int currentPage, @PathVariable("primeLevel") int primeLevel,
            @PathVariable("forumId") int forumId, ModelMap model) {
        if (primeLevel == 0) {
            return list(request, response, currentPage, forumId, model);
        } else {
            return jhlist(request, response, currentPage, forumId, model);
        }
    }

    @RequestMapping("/list/{forumId}/{currentPage}")
    public String list(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("currentPage") int currentPage, @PathVariable("forumId") int forumId, ModelMap model) {
        int primeLevel = 0;
        if (currentPage < 1) {
            currentPage = 1;
        }

        PageModel<Topic> page = new PageModel<Topic>(currentPage, WebConstants.PAGE_SIZE);
        page.insertQuery("forumId", forumId);
        page.insertQuery("primeLevel", primeLevel);

        page = topicService.query(page);
        List<Category> categoryList = categoryService.queryBySite(GlobalConfigHolder.SITE_ID_BBS);
        Forum forum = forumService.loadById(forumId + "");

        model.put("listUrl", "/bbs/topic/list/" + forumId + "/");
        model.put("forum", forum);
        model.put("categoryList", categoryList);
        model.put("page", page);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "bbs/topic/list/list_index.ftl";
    }

    @RequestMapping("/jh/list/{forumId}/{currentPage}")
    public String jhlist(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("currentPage") int currentPage, @PathVariable("forumId") int forumId, ModelMap model) {
        int primeLevel = 1;
        if (currentPage < 1) {
            currentPage = 1;
        }

        PageModel<Topic> page = new PageModel<Topic>(currentPage, WebConstants.PAGE_SIZE);
        page.insertQuery("forumId", forumId);
        page.insertQuery("primeLevel", primeLevel);

        page = topicService.query(page);
        List<Category> categoryList = categoryService.queryBySite(GlobalConfigHolder.SITE_ID_BBS);
        Forum forum = forumService.loadById(forumId + "");

        model.put("listUrl", "/bbs/topic/jh/list/" + forumId + "/");
        model.put("forum", forum);
        model.put("categoryList", categoryList);
        model.put("page", page);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "bbs/topic/list/list_index.ftl";
    }
    
    @RequestMapping("/singleIndex")
    public String singleIndex(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("id");
        return detail(request, response, Integer.parseInt(id), 1, model);
    }
    
    @RequestMapping("/singleIndex/{id}/{currentPage}")
    public String singleIndex(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            @PathVariable("currentPage") int currentPage, ModelMap model) {
        return detail(request, response, id, currentPage, model);
    }

    @RequestMapping("/detail/{id}")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        return detail(request, response, id, 1, model);
    }
    
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response,
            ModelMap model) {
        String id = request.getParameter("id");
        return detail(request, response, Integer.parseInt(id), 1, model);
    }

    @RequestMapping("/detail/{id}/{currentPage}")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            @PathVariable("currentPage") int currentPage, ModelMap model) {
        if (currentPage < 1) {
            currentPage = 1;
        }
        Topic topic = topicService.queryById(id, currentPage, WebConstants.PAGE_SIZE);
        List<Category> categoryList = categoryService.queryBySite(GlobalConfigHolder.SITE_ID_BBS);
        Forum forum = forumService.loadById(topic.getForumId() + "");

        model.put("listUrl", "/bbs/topic/detail/" + id + "/");
        model.put("forum", forum);
        model.put("categoryList", categoryList);
        model.put("topic", topic);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "bbs/topic/single/single_index.ftl";
    }

    @RequestMapping("/reply")
    public String reply(HttpServletRequest request, HttpServletResponse response, int topicId,
            @RequestParam(defaultValue = "-1") int type, @RequestParam(defaultValue = "0") int postId,
            @RequestParam(defaultValue = "1") int indexCount, ModelMap model) {
        LOG.info("reply Post {}", topicId);
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }
        
        Topic topic = topicService.loadById(topicId + "");
        Forum forum = forumService.loadById(topic.getForumId() + "");
        List<Category> categoryList = categoryService.queryBySite(GlobalConfigHolder.SITE_ID_BBS);
        Post post = null;
        if (postId != 0) {
            post = postService.loadById(postId + "");
        }

        if (type == 1) {
            // String contentText = JsoupUtils.plainText(post.getContent());
            String contentText = post.getContent();
            // if(contentText != null){
            // if(contentText.length() > 200){
            // contentText = contentText.substring(0, 200) + "...";
            // }
            // }
            model.put("content", "<pre class=\"brush:plain;\">" + contentText + "</pre>\n\r\n\r");
        } else if (type == 2) {
            model.put("content", "<pre class=\"brush:plain;\">回复：" + indexCount + "楼, @" + post.getNickname()
                    + "</pre>\n\r\n\r");
        }

        model.put("categoryList", categoryList);
        model.put("forum", forum);
        model.put("topic", topic);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "bbs/topic/reply/reply_index.ftl";
    }

    @RequestMapping("/replyCommit")
    public String replyCommit(HttpServletRequest request, HttpServletResponse response, Post post, ModelMap model) {
        LOG.info("submit Post {}", post);

        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }

        buildExtCreatorPara(request, post);
        if (user != null) {
            post.setCreator(user.getUsername());
            post.setCreaterId(user.getId());
            post.setUsername(user.getUsername());
        }
        postService.newPost(post);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "redirect:detail/" + post.getTopicId() + "/1" + ".html";
    }

    @RequestMapping("/newTopic/{forumId}")
    public String newTopic(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("forumId") int forumId, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }

        List<Tag> tagList = tagService.queryByIsSysAndSite(GlobalConfigHolder.IS_STR_TRUE,
                GlobalConfigHolder.SITE_ID_BBS);

        model.put("forumId", forumId);
        model.put("tagList", tagList);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "bbs/topic/post/post_index.ftl";
    }

    @RequestMapping("/topicCommit")
    public String topicCommit(HttpServletRequest request, HttpServletResponse response, Topic topic, ModelMap model) {
        if (topic == null) {
            return ERROR;
        }

        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }

        topic.setSiteId(GlobalConfigHolder.SITE_ID_BBS);
        buildExtCreatorPara(request, topic);
        if (user != null) {
            topic.setCreator(user.getUsername());
            topic.setCreaterId(user.getId());
            topic.setUsername(user.getUsername());
        }

        topicService.newTopic(topic);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_BBS_CODE);
        return "redirect:detail/" + topic.getId() + "/0.html";
    }

    @RequestMapping("/batchCommit")
    public void batchCommit(HttpServletRequest request, HttpServletResponse response, Topic topic, ModelMap model)
            throws IOException {
        LOG.info("submit Topic {}", topic);

        topic.setSiteId(GlobalConfigHolder.SITE_ID_BBS);
        buildExtCreatorPara(request, topic);
        User user = getSessionUser(request);
        if (user != null) {
            topic.setCreator(user.getUsername());
            topic.setCreaterId(user.getId());
            topic.setUsername(user.getUsername());
        } else {
            topic.setCreaterId(SpiderConfigs.SPIDER_USERID);
            topic.setUsername(SpiderConfigs.SPIDER);
            topic.setCreator(SpiderConfigs.SPIDER);
        }
        if (StringUtils.isEmpty(topic.getTitle()) || StringUtils.isEmpty(topic.getContent())) {
            response.getWriter().println(0);
            return;
        }
        topicService.newTopic(topic);

        response.getWriter().println(topic.getId());
    }

    @RequestMapping("/batchPostCommit")
    public void batchPostCommit(HttpServletRequest request, HttpServletResponse response, Post post, ModelMap model)
            throws IOException {
        LOG.info("submit Post {}", post);

        buildExtCreatorPara(request, post);
        User user = getSessionUser(request);
        if (user != null) {
            post.setCreator(user.getUsername());
            post.setCreaterId(user.getId());
            post.setUsername(user.getUsername());
        } else {
            post.setCreaterId(SpiderConfigs.SPIDER_USERID);
            post.setUsername(SpiderConfigs.SPIDER);
            post.setCreator(SpiderConfigs.SPIDER);
        }
        postService.newPost(post);

        response.getWriter().println(post.getId());
    }
}
