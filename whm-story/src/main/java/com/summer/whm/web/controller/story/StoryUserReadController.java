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
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryUserRead;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryUserReadService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/user/read")
public class StoryUserReadController extends BaseController {

    @Autowired
    private StoryUserReadService storyUserReadService;

    @Autowired
    private StoryInfoService storyInfoService;

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "0") int storyId, ModelMap model) {

        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return;
        }

        StoryUserRead storyUserRead = new StoryUserRead();

        if (storyId != 0) {
            StoryInfo storyInfo = storyInfoService.loadById(storyId + "");
            if (storyInfo == null) {
                return;
            }

            storyUserRead.setStoryId(storyId);
            storyUserRead.setTitle(storyInfo.getTitle());
        }

        storyUserRead.setCreator(user.getUsername());
        storyUserRead.setCreateTime(new Date());
        storyUserRead.setUserId(user.getId());
        storyUserRead.setUsername(user.getUsername());

        storyUserReadService.save(storyUserRead);
    }

    @RequestMapping("/del/{id}")
    public String del(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }
        
        storyUserReadService.deleteById(id + "");
        return "redirect:/user/read/list.html";
    }
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        List<StoryUserRead> recordList = storyUserReadService.queryByUsername(user.getUsername());
        model.put("recordList", recordList);

        return "/story/record/record_index.ftl";
    }

}
