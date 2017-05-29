package com.summer.whm.web.controller.blog.backend.options;

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
import com.summer.whm.common.model.PageModel;
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
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/blog/backend/options")
public class BlogOptionsController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BlogOptionsController.class);

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
        
        PageModel<BlogPost> page = new PageModel<BlogPost>(currentPage, WebConstants.PAGE_SIZE);
        page = blogPostService.queryHotBlogPost(page);
        
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(10);
        List<Tag> tagList = tagService.queryByCreatorAndSite(user.getUsername(), Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(user.getUsername());
        BlogOptions blogOptions = blogOptionsService.queryByCreator(user.getUsername());
        
        model.put("page", page);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);
        model.put("blogPostTop", blogPostTop);
        model.put("blogOptions", blogOptions);
        model.put(GlobalConfigHolder.CURRENT_MENU_CODE, GlobalConfigHolder.MENU_BLOG_CODE);
        return "blog/web/options/options_index.ftl";
    }
  
    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, ModelMap model, BlogOptions blogOptions) {
        LOG.info("submit BlogOptions {}", blogOptions);

        if (blogOptions != null) {
            blogOptions.setLastUpdate(new Date());
            User user = getSessionUser(request);
            blogOptions.setCreator(user.getUsername());
        }

        if (blogOptions != null && blogOptions.isNew()) {
            blogOptions.setCreateTime(new Date());
            blogOptionsService.insert(blogOptions);
        } else {
            blogOptionsService.update(blogOptions);
        }

        return "redirect:index.html";
    }
}
