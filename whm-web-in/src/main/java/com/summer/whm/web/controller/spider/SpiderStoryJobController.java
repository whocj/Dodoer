package com.summer.whm.web.controller.spider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.spider.service.SpiderStoryJobService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/story/job")
public class SpiderStoryJobController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(SpiderStoryJobController.class);
    
    private SpiderStoryJobService spiderStoryJobService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        LOG.info("list SpiderStoryJob currentPage={}",currentPage);
        PageModel<SpiderStoryJob> page = spiderStoryJobService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/story/job/list.ftl";
    }
    

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        SpiderStoryJob spiderStoryJob = spiderStoryJobService.loadById(id + "");
        model.put("spiderStoryJob", spiderStoryJob);
        PageModel<SpiderStoryJob> page = spiderStoryJobService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        LOG.info("edit CrawTemplate id={}", id);
        return "spider/crawTemplate/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("spiderStoryJob", new SpiderStoryJob());
        PageModel<SpiderStoryJob> page = spiderStoryJobService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        return "spider/crawTemplate/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete SpiderStoryJob id={}", ids);
        spiderStoryJobService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, SpiderStoryJob spiderStoryJob) {
        LOG.info("submit SpiderStoryJob {}", spiderStoryJob);
        if (spiderStoryJob != null && spiderStoryJob.isNew()) {
            buildExtCreatorPara(request, spiderStoryJob);
            spiderStoryJobService.insert(spiderStoryJob);
        } else {
            spiderStoryJobService.update(spiderStoryJob);
        }

        return "redirect:list.htm";
    }
}