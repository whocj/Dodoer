package com.summer.whm.web.controller.blog.web.comment;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.blog.BlogComment;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.blog.BlogCommentService;
import com.summer.whm.web.common.utils.BrowseTool;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/blog/comment")
public class BlogCommentController extends BaseController {

    @Autowired
    private BlogCommentService blogCommentService;

    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, BlogComment comment,
            ModelMap model) {
        String ip = request.getRemoteHost();
        User user = this.getSessionUser(request);
        if (user != null) {
            comment.setCreatorId(user.getId());
            comment.setCreator(user.getUsername());
            comment.setEmail(user.getEmail());
        }
        
        comment.setIp(ip);
        comment.setCreateTime(new Date());
        comment.setLastUpdate(new Date());
        comment.setStatus("0");
        String userAgent = request.getHeader("user-agent");
        String agent = BrowseTool.checkBrowse(userAgent);
        comment.setAgent(agent);
        
        blogCommentService.save(comment);
        return "redirect:/blog/post/detail/" + comment.getPostId() + ".html";
    }
}
