package com.summer.whm.web.controller.story;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.summer.whm.Constants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryRegister;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryRegisterService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/story/register")
public class StoryRegisterController  extends BaseController{
   
    @Autowired
    private StoryRegisterService storyRegisterService;

    @Autowired
    private StoryInfoService storyInfoService;
    
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/list/{cp}")
    public String list(HttpServletRequest request, HttpServletResponse response, @PathVariable("cp") int cp,
            ModelMap model) {
        if (cp < 1) {
            cp = 1;
        }

        PageModel<StoryRegister> page = new PageModel<StoryRegister>(cp, WebConstants.PAGE_SIZE);

        storyRegisterService.list(page);
        model.put("page", page);

        // 阅读最多的
        List<StoryInfo> readStoryList = storyInfoService.queryTopHot(null, TOP_10);
        // 用户最喜欢
        List<StoryInfo> likeStoryList = storyInfoService.queryTopLike(null, TOP_10);

        model.put("readStoryList", readStoryList);
        model.put("likeStoryList", likeStoryList);

        return getForward(request, response, "story/register/list/list_index.ftl");
    }
    
    @RequestMapping("/edit/{id}")
    public String info(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        User user = this.getSessionUser(request);
        if (user == null) {
            return LOGIN_URL;
        }
        
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        StoryRegister storyRegister = storyRegisterService.loadById(id + "");
        if(storyRegister == null){
            storyRegister = new StoryRegister();
        }
        
        model.put("storyRegister", storyRegister);
        model.put("categoryList", categoryList);
        return getForward(request, response, "story/register/edit/register_edit_index.ftl");
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        if (user == null) {
            return LOGIN_URL;
        }
        
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        StoryRegister storyRegister = new StoryRegister();

        model.put("storyRegister", storyRegister);
        model.put("categoryList", categoryList);
        return getForward(request, response, "story/register/edit/dialog_register.ftl");
    }
    
    @RequestMapping("/commit")
    public void commit(HttpServletRequest request, HttpServletResponse response, StoryRegister storyRegister, ModelMap model) {
        User user = this.getSessionUser(request);
        if (user == null) {
            return ;
        }
        Map<String, String> map = new HashMap<String, String>();
        Integer registerId = null;
        try{
            this.buildExtCreatorPara(request, storyRegister);
            storyRegister.setId(null);
            storyRegister.setStatus("0");
            registerId = storyRegisterService.save(storyRegister);
        }catch(Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }

        if(registerId == null){
            map.put("status", "F");
        }else{
            map.put("status", "S");
            map.put("title", storyRegister.getTitle());
            map.put("author", storyRegister.getAuthor());
            map.put("registerId", registerId + "");
        }
        String jsonString = JSONObject.toJSONString(map);
        this.ajaxJson(response, jsonString);
    }
}
