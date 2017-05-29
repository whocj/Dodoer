package com.summer.whm.web.controller.blog.web.post;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.model.MapContainer;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.blog.BlogOptions;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.sys.FriendLink;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.blog.BlogOptionsService;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.search.SimilarSearchService;
import com.summer.whm.service.search.model.DocType;
import com.summer.whm.service.search.model.PostType;
import com.summer.whm.service.sys.FriendLinkService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/b")
public class BController extends BaseController {

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

    @Autowired
    private SimilarSearchService similarSearchService;
    
    @RequestMapping("/detail/{id}")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        BlogPost blogPost = blogPostService.queryById(id);
        if (blogPost != null && !"1".equals(blogPost.getPstatus())) {
            return "redirect:/blog/post/gotoUpdate.html?id=" + blogPost.getId();
        }
        if(blogPost == null){
            return ERROR;
        }
        String creator = blogPost.getCreator();

        BlogPost prevPost = blogPostService.queryPrevByCreatorAndId(creator, id);
        BlogPost nextPost = blogPostService.queryNextByCreatorAndId(creator, id);

        BlogOptions blogOptions = blogOptionsService.queryByCreator(creator);
        List<Tag> tagList = tagService.queryByCreatorAndSite(creator, Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(creator);
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(TOP_8);

        List<MapContainer> meSimilarList = similarSearchService.searchBlogByMe(blogPost);
        Set<String> idSet = new HashSet<String>(); 
        idSet.add(PostType.POST_TYPE_BLOG + "@" + blogPost.getId());
        if(meSimilarList != null && meSimilarList.size() > 0){
            for(MapContainer c : meSimilarList){
                idSet.add((String) c.get(DocType.DOC_FIELD_ID));
            }
        }
        List<MapContainer> likeList = similarSearchService.search(blogPost.getTitle(), idSet);
        
        model.put("meSimilarList", meSimilarList);
        model.put("likeList", likeList);
        
        model.put("currentDate", new Date());
        model.put("blogPostTop", blogPostTop);
        model.put("prevPost", prevPost);
        model.put("nextPost", nextPost);
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("friendLinkList", friendLinkList);
        model.put("blogPost", blogPost);

        return "blog/web/post/single_index.ftl";
    }

    @RequestMapping("/main/{user}")
    public String main(HttpServletRequest request, HttpServletResponse response, @PathVariable("user") String user,
            @RequestParam(defaultValue = "1") int cp, @RequestParam(defaultValue = "1") int pstatus, ModelMap model) {
        if (StringUtils.isEmpty(user)) {
            User u = this.getSessionUser(request);
            if (u != null) {
                user = u.getUsername();
            } else {
                return "redirect:/blog/index.html";
            }
        } else {
            User u = this.getSessionUser(request);
            if (u != null) {
                User tempUser = userService.loadById(u.getId() + "");
                if (StringUtils.isEmpty(tempUser.getPassword())) {
                    return "redirect:/blog/backend/userinfo/index.html?type=1";
                }
            }
        }
        PageModel<BlogPost> page = new PageModel<BlogPost>(cp, WebConstants.PAGE_SIZE);

        page.insertQuery("creator", user);// 用户名称
        page.insertQuery("pstatus", pstatus);// 贴子状态
        page = blogPostService.queryByCreatorId(page);

        BlogOptions blogOptions = blogOptionsService.queryByCreator(user);
        List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(10);
        List<Tag> tagList = tagService.queryByCreatorAndSite(user, Constants.SITE_ID_BLOG);
        List<FriendLink> friendLinkList = friendLinkService.queryByCreator(user);
        List<BlogPost> blogPostTop = blogPostService.queryLatestTopNum(TOP_8);

        model.put("htmlTitle", Constants.BLOG_POST_STATUS_MAP.get(pstatus));
        model.put("currentDate", new Date());
        model.put("blogPostTop", blogPostTop);
        model.put("page", page);
        model.put("blogOptions", blogOptions);
        model.put("tagList", tagList);
        model.put("blogPostList", blogPostList);
        model.put("friendLinkList", friendLinkList);
        model.put("listUrl", "/main/" + user + ".html&pstatus=" + pstatus + "&cp=");
        return "blog/web/post/main_index.ftl";
    }
}
