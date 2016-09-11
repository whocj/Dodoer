package com.summer.whm.web.controller.story;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryUserBookshelf;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryUserBookshelfService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.model.AjaxModel;

@Controller
@RequestMapping("/user/bookshelf")
public class StoryUserBookshelfController extends BaseController {

    @Autowired
    private StoryUserBookshelfService storyUserBookshelfService;

    @Autowired
    private StoryInfoService storyInfoService;

    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/add/{storyId}")
    public void add(HttpServletRequest request, HttpServletResponse response, @PathVariable("storyId") int storyId,
            ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            AjaxModel ajaxModel = new AjaxModel(WebConstants.RESP_STATUS_PRO, "未登录用户。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
            return;
        }

        StoryUserBookshelf userBookshelf = new StoryUserBookshelf();

        if (storyId != 0) {
            StoryInfo storyInfo = storyInfoService.loadById(storyId + "");
            if (storyInfo == null) {
                AjaxModel ajaxModel = new AjaxModel(WebConstants.RESP_STATUS_ERR_PARM, "传入参数不对。");
                this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
                return;
            }

            userBookshelf.setStoryId(storyId);
            userBookshelf.setTitle(storyInfo.getTitle());

            userBookshelf.setCreator(user.getUsername());
            userBookshelf.setCreateTime(new Date());
            userBookshelf.setUserId(user.getId());
            userBookshelf.setUsername(user.getUsername());
            userBookshelf.setStatus("0");
            storyUserBookshelfService.save(userBookshelf);
            AjaxModel ajaxModel = new AjaxModel(WebConstants.RESP_STATUS_SUCC, "添加书架成功。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
        } else {
            AjaxModel ajaxModel = new AjaxModel(WebConstants.RESP_STATUS_ERR_PARM, "传入参数不对。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
        }
    }

    @RequestMapping("/del/{id}")
    public String del(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }
        
        storyUserBookshelfService.deleteById(id + "");
        return "redirect:/user/bookshelf/list.html";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }
        List<StoryUserBookshelf> bookshelfList = storyUserBookshelfService.queryByUserId(user.getId());
        model.put("bookshelfList", bookshelfList);

        return  getForward(request, response, "story/bookshelf/bookshelf_index.ftl");
    }

    //手机使用分页使用
    @RequestMapping("/more/{cp}")
    public String more(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("cp") int cp, ModelMap model) {
        if(cp < 1){
            cp = 1;
        }
        PageModel<StoryInfo> page = new PageModel<StoryInfo>(cp, WebConstants.PAGE_SIZE);
     
        storyInfoService.list(page);
        model.put("page", page);
        
        return "m/story/list/list_more.ftl";
    }
    
}