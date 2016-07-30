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
import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.service.forum.ForumService;
import com.summer.whm.spider.service.ListTemplateService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/listTemplate")
public class ListTemplateController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(ListTemplateController.class);

    @Autowired
    private ListTemplateService listTemplateService;

    @Autowired
    private ForumService forumService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<ListTemplate> page = listTemplateService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/listTemplate/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        ListTemplate listTemplate = listTemplateService.loadById(id + "");
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        model.put("listTemplate", listTemplate);
        LOG.info("edit ListTemplate id={}", id);
        return "spider/listTemplate/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("listTemplate", new ListTemplate());
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        return "spider/listTemplate/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete ListTemplate id={}", ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, ListTemplate listTemplate) {
        LOG.info("submit ListTemplate {}", listTemplate);
        if (listTemplate != null && listTemplate.isNew()) {
            buildExtCreatorPara(request, listTemplate);
            listTemplateService.insert(listTemplate);
        } else {
            listTemplateService.update(listTemplate);
        }

        return "redirect:list.htm";
    }

    @RequestMapping("/editByCrawTemplateId")
    public String editByCrawTemplateId(HttpServletRequest request,
            @RequestParam(value = "crawTemplateId") int crawTemplateId, @RequestParam(value = "forumId") int forumId,
            ModelMap model) {
        ListTemplate listTemplate = listTemplateService.queryByCrawTemplateId(crawTemplateId);
        if(listTemplate == null){
            listTemplate = new ListTemplate();
            listTemplate.setCrawTemplateId(crawTemplateId);
            listTemplate.setForumId(forumId);
        }
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        model.put("listTemplate", listTemplate);
        LOG.info("edit ListTemplate crawTemplateId={}", crawTemplateId);
        return "spider/listTemplate/edit.ftl";
    }
}
