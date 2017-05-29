package com.summer.whm.web.controller.blog.web.post;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/blog")
public class BlogPostController extends BaseController {

    public static final Integer TOP_8 = 8;

    @Autowired
    private BlogPostService blogPostService;

    @Autowired
    private TagService tagService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Autowired
    private BlogOptionsService blogOptionsService;

    @Autowired
    private UserService userService;
    
    @RequestMapping("/post/singleIndex/{id}")
    public String singleIndex(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        return detail(request, response, id, model);
    }

    @RequestMapping("/post/detail/{id}")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        BlogPost blogPost = blogPostService.queryById(id);
        if (blogPost != null && !"1".equals(blogPost.getPstatus())) {
            return "redirect:/blog/post/gotoUpdate.html?id=" + blogPost.getId();
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

    @RequestMapping("/post/main/{creator}/{pstatus}/{currentPage}")
    public String mainIndex(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("creator") String creator,
            @PathVariable("currentPage") int currentPage, @PathVariable("pstatus") String pstatus, ModelMap model) {
        if (pstatus == null) {
            pstatus = "1";
        }
        if (currentPage < 1) {
            currentPage = 1;
        }
        PageModel<BlogPost> page = new PageModel<BlogPost>(currentPage, WebConstants.PAGE_SIZE);

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
        model.put("listUrl", "/blog/post/main/" + creator + "/" + pstatus + "/");
        return "blog/web/post/main_index.ftl";
    }

    @RequestMapping("/main/{user}")
    public String main(HttpServletRequest request, HttpServletResponse response, @PathVariable("user") String user,
            ModelMap model) {
        int currentPage = 1;
        String pstatus = "1";
        if (StringUtils.isEmpty(user)) {
            User u = this.getSessionUser(request);
            if (u != null) {
                user = u.getUsername();
            } else {
                return "redirect:/blog/index.html";
            }
        }else{
            User u = this.getSessionUser(request);
            if (u != null) {
               User tempUser = userService.loadById(u.getId() + "");
               if(StringUtils.isEmpty(tempUser.getPassword())){
                   return "redirect:/blog/backend/userinfo/index.html?type=1";
               }
            }
        }
        return mainIndex(request, response,user, currentPage, pstatus, model);
    }
}
