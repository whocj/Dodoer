package com.summer.whm.web.controller.story;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.service.stroy.StoryDetailService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/story/detail")
public class StoryDetailController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(StoryDetailController.class);

    @Autowired
    private StoryInfoService storyInfoService;
    
    @Autowired
    private StoryDetailService storyDetailService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, 
            @RequestParam(defaultValue = "") String title, ModelMap model) {
        PageModel<StoryInfo> page = new PageModel<StoryInfo>(currentPage, WebConstants.PAGE_SIZE);
        if(StringUtils.isNotEmpty(title)){
            page.insertQuery("title", "%" + title + "%");
        }
        
        storyInfoService.list(page);
        model.put("page", page);
        return "story/info/list.ftl";
    }

    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, 
            @RequestParam(defaultValue = "0") int storyId,
            @RequestParam(defaultValue = "") String title, ModelMap model) {
        List<StoryDetail>  detailList  = storyDetailService.queryByStoryId(storyId);
        model.put("detailList", detailList);
        return "story/info/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        StoryInfo storyInfo = storyInfoService.loadById(id + "");
        model.put("storyInfo", storyInfo);
        LOG.info("edit StoryInfo id={}", id);
        return "story/info/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("storyInfo", new StoryInfo());
        return "story/info/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete StoryInfo id={}", ids);
        storyInfoService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, StoryInfo storyInfo) {
        LOG.info("submit storyTopic {}", storyInfo);
        if (storyInfo != null && storyInfo.isNew()) {
            buildExtCreatorPara(request, storyInfo);
            storyInfoService.insert(storyInfo);
        } else {
            storyInfoService.update(storyInfo);
        }

        return "redirect:list.htm";
    }
}
