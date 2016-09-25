package com.summer.whm.web.controller.sys;

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
import com.summer.whm.entiry.sys.Site;
import com.summer.whm.service.sys.SiteService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/site")
public class SiteController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private SiteService siteService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Site> page = siteService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/site/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Site site = siteService.loadById(id + "");
        model.put("site", site);
        LOG.info("edit Site id={}", id);
        return "sys/site/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("site", new Site());
        return "sys/site/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete Site id={}", ids);
        siteService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Site site) {
        LOG.info("submit Site {}", site);
        if (site != null && site.isNew()) {
            siteService.insert(site);
        } else {
            siteService.update(site);
        }

        return "redirect:list.htm";
    }
}
