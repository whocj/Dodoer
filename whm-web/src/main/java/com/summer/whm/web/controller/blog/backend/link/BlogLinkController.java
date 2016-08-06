package com.summer.whm.web.controller.blog.backend.link;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.blog.BlogOptions;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.blog.BlogOptionsService;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/blog/backend/link")
public class BlogLinkController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BlogLinkController.class);

    private static final String LOGIN_URL = "redirect:/login/index.htm";

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private BlogOptionsService blogOptionsService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(user.getUsername());
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(10);
        List<Tag> tagList = tagService.queryByCreatorAndSite(user.getUsername(), Constants.SITE_ID_BLOG);
        BlogOptions blogOptions = blogOptionsService.queryByCreator(user.getUsername());

        model.put("blogOptions", blogOptions);
        model.put("friendLinkList", friendLinkList);
        model.put("tagList", tagList);
        model.put("blogPostTop", blogPostTop);

        return "blog/web/link/link_index.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        model.put("friendLink", new FriendLink());
        return "blog/web/link/link_update.ftl";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer id) {
        FriendLink friendLink = null;
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        if(id == null || new Integer(0).equals(id)){
            friendLink = new FriendLink();
        }else{
            friendLink = friendLinkService.loadById(id + "");
        }
        
        BlogOptions blogOptions = blogOptionsService.queryByCreator(user.getUsername());

        model.put("blogOptions", blogOptions);
        model.put("friendLink", friendLink);
        LOG.info("update FriendLink id={}", id);
        return "blog/web/link/link_update.ftl";
    }

    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, ModelMap model, FriendLink friendLink) {
        LOG.info("submit FriendLink {}", friendLink);

        if (friendLink != null) {
            friendLink.setCreateTime(new Date());
            friendLink.setLastUpdate(new Date());
            User user = getSessionUser(request);
            friendLink.setCreator(user.getUsername());
            friendLink.setIsEnabled("1");
        }

        if (friendLink != null && friendLink.isNew()) {
            friendLinkService.insert(friendLink);
        } else {
            friendLinkService.update(friendLink);
        }

        return "redirect:index.html";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer id) {
        LOG.info("delete FriendLink id={}", id);
        friendLinkService.deleteById(id + "");
        return "redirect:index.html";
    }

}
