package com.summer.whm.web.controller.spider;

import java.util.Date;

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
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.entiry.user.User;
import com.summer.whm.spider.service.SpiderStoryTemplateService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/story/template")
public class SpiderStoryTemplateController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(SpiderStoryTemplateController.class);

    @Autowired
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
        SpiderStoryTemplate spiderStoryTemplate = spiderStoryTemplateService.loadById(id + "");
        model.put("spiderStoryTemplate", spiderStoryTemplate);
        LOG.info("edit SpiderStoryTemplate id={}", id);
        return "spider/story/template/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("spiderStoryTemplate", new SpiderStoryTemplate());
        return "spider/story/template/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete SpiderStoryTemplate id={}", ids);
        spiderStoryTemplateService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, SpiderStoryTemplate spiderStoryTemplate) {
        LOG.info("submit SpiderStoryTemplate {}", spiderStoryTemplate);
        if (spiderStoryTemplate != null && spiderStoryTemplate.isNew()) {
            buildExtCreatorPara(request, spiderStoryTemplate);
            User user = getSessionUser(request);
            spiderStoryTemplate.setUserId(user.getId() + "");
            spiderStoryTemplate.setUsername(user.getUsername());
            spiderStoryTemplateService.insert(spiderStoryTemplate);
        } else {
            spiderStoryTemplate.setLastUpdate(new Date());
            spiderStoryTemplateService.update(spiderStoryTemplate);
        }

        return "redirect:list.htm";
    }
}
