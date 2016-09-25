package com.summer.whm.web.controller.story;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.story.StoryTopic;
import com.summer.whm.entiry.story.StoryTopicDetail;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.stroy.StoryTopicDetailService;
import com.summer.whm.service.stroy.StoryTopicService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/story/topic")
public class StoryTopicController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(StoryTopicController.class);

    @Autowired
    private StoryTopicDetailService storyTopicDetailService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private StoryTopicService storyTopicService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<StoryTopic> page = storyTopicService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "story/topic/list.ftl";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, 
            @RequestParam(defaultValue = "0") int topicId,
            @RequestParam(defaultValue = "") String title, ModelMap model) {
        List<StoryTopicDetail>  detailList  = storyTopicDetailService.queryByTopicId(topicId, 100);
        model.put("detailList", detailList);
        model.put("topicId", topicId);
        return "story/topic/detailList.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        StoryTopic storyTopic = storyTopicService.loadById(id + "");
        model.put("storyTopic", storyTopic);
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY);
        model.put("categoryList", categoryList);
        
        LOG.info("edit StoryTopic id={}", id);
        return "story/topic/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        StoryTopic storyTopic = new StoryTopic();
        storyTopic.setStatus("0");
        model.put("storyTopic", storyTopic);
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY);
        model.put("categoryList", categoryList);
        
        return "story/topic/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete StoryTopic id={}", ids);
        storyTopicService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, StoryTopic storyTopic) {
        LOG.info("submit storyTopic {}", storyTopic);
        if (storyTopic != null && storyTopic.isNew()) {
            buildExtCreatorPara(request, storyTopic);
            storyTopicService.insert(storyTopic);
        } else {
            storyTopicService.update(storyTopic);
        }

        return "redirect:list.htm";
    }
    
}
