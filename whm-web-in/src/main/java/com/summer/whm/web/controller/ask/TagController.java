package com.summer.whm.web.controller.ask;

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
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/ask/tag")
public class TagController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Tag> page = tagService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "ask/tag/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Tag tag = tagService.loadById(id + "");
        model.put("tag", tag);
        LOG.info("edit Tag id={}", id);
        return "ask/tag/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("tag", new Tag());
        return "ask/tag/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete Tag id={}", ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Tag tag) {
        LOG.info("submit Tag {}", tag);
        if (tag != null && tag.isNew()) {
            tagService.insert(tag);
        } else {
            tagService.update(tag);
        }

        return "redirect:list.htm";
    }
}
