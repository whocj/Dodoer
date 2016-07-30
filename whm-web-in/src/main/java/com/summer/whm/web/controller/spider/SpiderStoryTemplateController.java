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
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.spider.service.SpiderStoryTemplateService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/sotry/template")
public class SpiderStoryTemplateController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(SpiderStoryTemplateController.class);

    private SpiderStoryTemplateService spiderStoryTemplateService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        LOG.info("list SpiderStoryTemplate currentPage={}",currentPage);
        PageModel<SpiderStoryTemplate> page = spiderStoryTemplateService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/story/template/list.ftl";
    }
    

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        CrawTemplate crawTemplate = spiderStoryTemplateService.loadById(id + "");
        model.put("crawTemplate", crawTemplate);
        PageModel<SpiderStoryTemplate> page = spiderStoryTemplateService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        LOG.info("edit CrawTemplate id={}", id);
        return "spider/crawTemplate/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("crawTemplate", new CrawTemplate());
        PageModel<SpiderStoryTemplate> page = spiderStoryTemplateService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        return "spider/crawTemplate/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete CrawTemplate id={}", ids);
        spiderStoryTemplateService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, CrawTemplate crawTemplate) {
        LOG.info("submit crawTemplate {}", crawTemplate);
        if (crawTemplate != null && crawTemplate.isNew()) {
            buildExtCreatorPara(request, crawTemplate);
            spiderStoryTemplateService.insert(crawTemplate);
        } else {
            spiderStoryTemplateService.update(crawTemplate);
        }

        return "redirect:list.htm";
    }
}
