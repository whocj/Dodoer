package com.summer.whm.web.controller.blog;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.summer.whm.entiry.blog.BlogOptions;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.blog.BlogOptionsService;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

public class BlogBaseController extends BaseController {

    public static final Integer TOP_8 = 8;
    
    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private BlogOptionsService blogOptionsService;

    public void addParam(HttpServletRequest request, HttpServletResponse response, ModelMap model, String creator) {
        BlogOptions blogOptions = blogOptionsService.queryByCreator(creator);
        List<Tag> tagList = tagService.queryByCreatorAndSite(creator, Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(creator);
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(TOP_8);

        model.put("currentDate", new Date());
        model.put("blogPostTop", blogPostTop);
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);
    }
}
