package com.summer.whm.web.controller.blog.web.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Constants;

@Controller
@RequestMapping("/blog")
public class BlogIndexController {

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model,
            @RequestParam(defaultValue = "1") int cp) {

        PageModel<BlogPost> page = new PageModel<BlogPost>(cp, WebConstants.PAGE_SIZE);
        page = blogPostService.queryHotBlogPost(page);

        List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(10);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryBySiteId(Constants.SITE_ID_BLOG);

        model.put("page", page);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);
        model.put("blogPostList", blogPostList);
        model.put("listUrl", "/blog/index.html?cp=");
        model.put(GlobalConfigHolder.CURRENT_MENU_CODE, GlobalConfigHolder.MENU_BLOG_CODE);
        return "blog/web/index.ftl";
    }
}
