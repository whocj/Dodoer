package com.summer.whm.web.controller.blog.backend.post;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.JsoupUtils;
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
@RequestMapping("/blog/backend/post")
public class BlogPostBackendController extends BaseController {

    public static final Integer TOP_8 = 8;

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private BlogOptionsService blogOptionsService;

    private static final String LOGIN_URL = "redirect:/login/index.htm";

    @RequestMapping("/singleIndex")
    public String singleIndex(HttpServletRequest request, HttpServletResponse response, Integer id,
            @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        return detail(request, response, id, currentPage, model);
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, HttpServletResponse response, Integer id,
            @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        BlogPost blogPost = blogPostService.queryById(id);
        if (blogPost != null && !"1".equals(blogPost.getPstatus())) {
            return "redirect:gotoUpdate.html?id=" + blogPost.getId();
        }

        String creator = blogPost.getCreator();
        BlogOptions blogOptions = blogOptionsService.queryByCreator(creator);
        List<Tag> tagList = tagService.queryByCreatorAndSite(creator, Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(creator);
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(TOP_8);

        model.put("currentDate", new Date());
        model.put("blogPostTop", blogPostTop);
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);
        model.put("blogPost", blogPost);

        return "blog/web/post/single_index.ftl";
    }

    @RequestMapping("/mainIndex")
    public String mainIndex(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "1") String pstatus,
            String creator, ModelMap model) {

        return main(request, response, currentPage, pstatus, creator, model);
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "1") String pstatus,
            String creator, ModelMap model) {

        PageModel<BlogPost> page = new PageModel<BlogPost>(currentPage, WebConstants.PAGE_SIZE);

        if (StringUtils.isEmpty(creator)) {
            User user = this.getSessionUser(request);
            creator = user.getUsername();
        }

        page.insertQuery("creator", creator);// 用户名称
        page.insertQuery("pstatus", pstatus);// 贴子状态
        page = blogPostService.queryByCreatorId(page);

        BlogOptions blogOptions = blogOptionsService.queryByCreator(creator);
        List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(10);
        List<Tag> tagList = tagService.queryByCreatorAndSite(creator, Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(creator);
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(TOP_8);

        model.put("htmlTitle", Constants.BLOG_POST_STATUS_MAP.get(pstatus));
        model.put("currentDate", new Date());
        model.put("blogPostTop", blogPostTop);
        model.put("page", page);
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("blogPostList", blogPostList);
        model.put("friendLinkList", friendLinkList);
        model.put("listUrl", "/blog/post/main.html?creator=" + creator + "&currentPage=");
        return "blog/web/post/main_index.ftl";
    }

    @RequestMapping("/gotoUpdate")
    public String gotoUpdate(HttpServletRequest request, HttpServletResponse response, Integer id, ModelMap model) {

        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        BlogOptions blogOptions = blogOptionsService.queryByCreator(user.getUsername());
        List<Tag> tagList = tagService.queryByCreatorAndSite(user.getUsername(), Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(user.getUsername());
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(TOP_8);
        BlogPost blogPost = blogPostService.queryById(id);
        if (blogPost == null) {
            blogPost = new BlogPost();
        }
        model.put("blogPost", blogPost);
        model.put("currentDate", new Date());
        model.put("blogPostTop", blogPostTop);
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);

        return "blog/web/post/update_index.ftl";
    }
    
    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, BlogPost blogPost, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        if (blogPost == null) {
            return gotoUpdate(request, response, null, model);
        }

        blogPost.setCategoryId(user.getId());
        blogPost.setCreator(user.getUsername());

        blogPost.setLastUpdate(new Date());
        blogPost.setCreateTime(new Date());
        blogPost.setCstatus("1");
        blogPost.setPstatus("1");
        blogPost.setCcount(0);
        blogPost.setRcount(1);
        blogPost.setWeekCCount(0);
        blogPost.setWeekRCount(1);

        String tempExcerpt = JsoupUtils.plainText(blogPost.getContent());
        if (tempExcerpt != null && tempExcerpt.length() > 200) {
            tempExcerpt = tempExcerpt.substring(0, 200);
        }
        blogPost.setContentText(tempExcerpt);
        blogPost.setExcerpt(tempExcerpt);
        blogPostService.save(blogPost);

        return "redirect:/blog/post/detail/" + blogPost.getId() + ".html";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, Integer id, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        blogPostService.delete(id);

        return "redirect:/blog/main/" + user.getUsername() + ".html";
    }
}
