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

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.service.forum.ForumService;
import com.summer.whm.spider.service.DetailTemplateService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/detailTemplate")
public class DetailTemplateController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(DetailTemplateController.class);

    @Autowired
    private DetailTemplateService detailTemplateService;

    @Autowired
    private ForumService forumService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<DetailTemplate> page = detailTemplateService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/detailTemplate/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        DetailTemplate detailTemplate = detailTemplateService.loadById(id + "");
        model.put("detailTemplate", detailTemplate);
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        LOG.info("edit DetailTemplate id={}", id);
        return "spider/detailTemplate/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("detailTemplate", new DetailTemplate());
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        return "spider/detailTemplate/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete DetailTemplate id={}", ids);
        detailTemplateService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, DetailTemplate detailTemplate) {
        LOG.info("submit DetailTemplate {}", detailTemplate);
        if (detailTemplate != null && detailTemplate.isNew()) {
            buildExtCreatorPara(request, detailTemplate);
            detailTemplateService.insert(detailTemplate);
        } else {
            detailTemplateService.update(detailTemplate);
        }

        return "redirect:list.htm";
    }
    
    @RequestMapping("/editByCrawTemplateId")
    public String editByCrawTemplateId(HttpServletRequest request,
            @RequestParam(value = "crawTemplateId") int crawTemplateId, @RequestParam(value = "forumId") int forumId,
            ModelMap model) {
        DetailTemplate detailTemplate = detailTemplateService.queryByCrawTemplateId(crawTemplateId);
        if(detailTemplate == null){
            detailTemplate = new DetailTemplate();
            detailTemplate.setCrawTemplateId(crawTemplateId);
            detailTemplate.setForumId(forumId);
        }
        PageModel<Forum> page = forumService.list(1, WebConstants.PAGE_SIZE * WebConstants.PAGE_SIZE);
        model.put("forumList", page.getContent());
        model.put("detailTemplate", detailTemplate);
        LOG.info("edit DetailTemplate crawTemplateId={}", crawTemplateId);
        return "spider/detailTemplate/edit.ftl";
    }
}
