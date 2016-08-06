package com.summer.whm.web.controller.spider;

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
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.service.forum.ForumService;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.spider.service.CrawTemplateService;
import com.summer.whm.spider.service.CrawlService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/crawTemplate")
public class CrawTemplateController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CrawTemplateController.class);

    @Autowired
    private CrawTemplateService crawTemplateService;

    @Autowired
    private CrawlService crawlService;

    @Autowired
    private ForumService forumService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<CrawTemplate> page = crawTemplateService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/crawTemplate/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        CrawTemplate crawTemplate = crawTemplateService.loadById(id + "");
        model.put("crawTemplate", crawTemplate);
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        LOG.info("edit CrawTemplate id={}", id);
        return "spider/crawTemplate/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("crawTemplate", new CrawTemplate());
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        return "spider/crawTemplate/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete CrawTemplate id={}", ids);
        crawTemplateService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, CrawTemplate crawTemplate) {
        LOG.info("submit crawTemplate {}", crawTemplate);
        if (crawTemplate != null && crawTemplate.isNew()) {
            buildExtCreatorPara(request, crawTemplate);
            crawTemplateService.insert(crawTemplate);
        } else {
            crawTemplateService.update(crawTemplate);
        }

        return "redirect:list.htm";
    }

    @RequestMapping("/start")
    public String start(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        crawlService.start(id,  this.getSessionUser(request));
        return "redirect:list.htm";
    }

    @RequestMapping("/pause")
    public String pause(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        crawlService.pause(id,SpiderConfigs.DOMAIN_TYPE_TOPIC, this.getSessionUser(request));
        return "redirect:list.htm";
    }

    @RequestMapping("/stop")
    public String stop(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        crawlService.stop(id, SpiderConfigs.DOMAIN_TYPE_TOPIC, this.getSessionUser(request));
        return "redirect:list.htm";
    }
}
