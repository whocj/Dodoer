package com.summer.whm.web.controller.blog.backend.tag;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.configs.GlobalConfigHolder;
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
@RequestMapping("/blog/backend/tag")
public class BlogTagController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(BlogTagController.class);

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
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model,
            @RequestParam(defaultValue = "1") int currentPage) {

        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }
        
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(10);
        List<Tag> tagList = tagService.queryByCreatorAndSite(user.getUsername(), Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(user.getUsername());
        BlogOptions blogOptions = blogOptionsService.queryByCreator(user.getUsername());
        
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);
        model.put("blogPostTop", blogPostTop);
        model.put(GlobalConfigHolder.CURRENT_MENU_CODE, GlobalConfigHolder.MENU_BLOG_CODE);
        return "blog/web/tag/tag_index.ftl";
    }
    
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Tag tag = new Tag();
        tag.setStyleBold("0");
        tag.setStyleItalic("0");
        model.put("tag", tag);
        return "blog/web/tag/tag_update.ftl";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer id) {
        Tag tag = null;
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        if(id == null || new Integer(0).equals(id)){
            tag = new Tag();
        }else{
            tag = tagService.loadById(id + "");
        }
        
        BlogOptions blogOptions = blogOptionsService.queryByCreator(user.getUsername());

        model.put("blogOptions", blogOptions);
        model.put("tag", tag);
        LOG.info("update Tag id={}", id);
        return "blog/web/tag/tag_update.ftl";
    }

    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, ModelMap model, Tag tag) {
        LOG.info("submit Tag {}", tag);

        tag.setSiteId(Constants.SITE_ID_BLOG);
        
        if (tag != null && tag.isNew()) {
            tag.setCreateTime(new Date());
            tag.setLastUpdate(new Date());
            User user = getSessionUser(request);
            tag.setCreator(user.getUsername());
            tag.setIsSystem("0");
            tagService.insert(tag);
        } else {
            tag.setLastUpdate(new Date());
            tagService.update(tag);
        }

        return "redirect:index.html";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap model, Integer id) {
        LOG.info("delete Tag id={}", id);
        tagService.deleteById(id + "");
        return "redirect:index.html";
    }
}
