package com.summer.whm.web.controller.forum;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.entiry.forum.ForumGroupOpt;
import com.summer.whm.entiry.forum.ForumUser;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.forum.ForumGroupOptService;
import com.summer.whm.service.forum.ForumService;
import com.summer.whm.service.forum.ForumUserService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/forum")
public class ForumController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(ForumController.class);

    @Autowired
    private ForumService forumService;

    @Autowired
    private ForumUserService forumUserService;

    @Autowired
    private ForumGroupOptService forumGroupOptService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Forum> page = forumService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "forum/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Forum forum = forumService.loadById(id + "");

        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_BBS);

        model.put("categoryList", categoryList);
        model.put("forum", forum);
        LOG.info("edit Forum id={}", id);
        return "forum/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_BBS);

        model.put("categoryList", categoryList);
        model.put("forum", new Forum());
        return "forum/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete Forum id={}", ids);
        forumService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Forum forum) {
        LOG.info("submit Forum {}", forum);
        if (forum != null && forum.isNew()) {
            forum.setCreateTime(new Date());
            forum.setLastTime(new Date());
            forum.setLastUpdate(new Date());
            forumService.insert(forum);
        } else {

            buildExtPara(forum);
            List<ForumUser> forumUserList = forum.getForumUserList();
            StringBuffer sb = new StringBuffer();
            for (ForumUser forumUser : forumUserList) {
                sb.append(forumUser.getUsername()).append(",");
            }
            String str = sb.toString();
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
                forum.setModerators(str);
            }
            forum.setLastTime(new Date());
            forum.setLastUpdate(new Date());
            forum.setGroupReplies(appendStr(forum.getGroupReplyList()));
            forum.setGroupTopics(appendStr(forum.getGroupTopicList()));
            forum.setGroupViews(appendStr(forum.getGroupViewList()));

            forumService.update(forum);
        }

        return "redirect:list.htm";
    }

    private String appendStr(List<ForumGroupOpt> groupOptList) {
        if (groupOptList != null && groupOptList.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (ForumGroupOpt forumGroupOpt : groupOptList) {
                sb.append(forumGroupOpt.getGroupName()).append(",");
            }
            String str = sb.toString();
            if (str.endsWith(",")) {
                str = str.substring(0, str.length() - 1);
            }
            return str;
        }
        return null;
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        LOG.info("addUser Forum's moderators forumId={}", id);
        Forum forum = forumService.loadById(id + "");
        buildExtPara(forum);

        model.put("showUser", "1");
        model.put("forum", forum);
        return "forum/edit.ftl";
    }

    @RequestMapping("/submitUser")
    public String submitUser(HttpServletRequest request, HttpServletResponse response, ForumUser forumUser,
            ModelMap model) {
        LOG.info("submitUser ForumUser={}", forumUser);
        buildExtCreatorPara(request, forumUser);
        forumUserService.insert(forumUser);

        Forum forum = forumService.loadById(forumUser.getForumId() + "");
        buildExtPara(forum);

        model.put("showUser", "1");
        model.put("forum", forum);
        return "forum/edit.ftl";
    }

    private void buildExtPara(Forum forum) {
        if (forum != null) {
            List<ForumUser> forumUserList = forumUserService.queryByForumId(forum.getId());
            if (forumUserList != null) {
                forum.setForumUserList(forumUserList);
            }

            Map<String, List<ForumGroupOpt>> map = forumGroupOptService.queryByForumId(forum.getId());
            if (map != null) {
                forum.setGroupReplyList(map.get(ForumGroupOptService.FORUM_GROUP_REPLY));
                forum.setGroupTopicList(map.get(ForumGroupOptService.FORUM_GROUP_TOPIC));
                forum.setGroupViewList(map.get(ForumGroupOptService.FORUM_GROUP_VIEW));
            }

        }
    }

    @RequestMapping("/addGroup")
    public String addGroup(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "id") int id, ModelMap model) {
        LOG.info("addUser Forum's moderators forumId={}", id);
        Forum forum = forumService.loadById(id + "");
        buildExtPara(forum);

        model.put("showGroup", "1");
        model.put("forum", forum);
        return "forum/edit.ftl";
    }

    @RequestMapping("/submitGroup")
    public String submitGroup(HttpServletRequest request, HttpServletResponse response, ForumGroupOpt forumGroupOpt,
            ModelMap model) {
        LOG.info("submitGroup ForumGroupOpt={}", forumGroupOpt);
        buildExtCreatorPara(request, forumGroupOpt);
        forumGroupOptService.insert(forumGroupOpt);

        Forum forum = forumService.loadById(forumGroupOpt.getForumId() + "");
        buildExtPara(forum);

        model.put("showGroup", "1");
        model.put("forum", forum);
        return "forum/edit.ftl";
    }

    @RequestMapping("/cleanUser")
    public String cleanUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) {
        LOG.info("cleanUser Forum moderators categoryId={}", id);
        forumUserService.cleanByForumId(id);
        return "redirect:list.htm";
    }

    @RequestMapping("/deleteForumUserById")
    public String deleteForumUserById(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "forumUserId") int forumUserId, @RequestParam(value = "forumId") int forumId) {
        LOG.info("deleteForumUserById Forum's moderators forumUserId={}", forumUserId);
        forumUserService.deleteById(forumUserId + "");
        return "redirect:addUser.htm?id=" + forumId;
    }

    @RequestMapping("/deleteForumGroupById")
    public String deleteForumGroupById(HttpServletRequest request, HttpServletResponse response, int forumGroupId,
            int forumId) {
        LOG.info("deleteForumGroupById Forum's Group forumGroupId={}", forumGroupId);
        forumGroupOptService.deleteById(forumGroupId + "");
        return "redirect:addGroup.htm?id=" + forumId;
    }
}
