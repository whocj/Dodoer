package com.summer.whm.web.controller.sys;

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
import com.summer.whm.entiry.sys.Notice;
import com.summer.whm.entiry.sys.Site;
import com.summer.whm.service.sys.NoticeService;
import com.summer.whm.service.sys.SiteService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/notice")
public class NoticeController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(NoticeController.class);
    
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private SiteService siteService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Notice> page = noticeService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/notice/list.ftl";
    }
    
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Notice notice = noticeService.loadById(id + "");
        List<Site> siteList = siteService.queryAll();
        
        model.put("siteList", siteList);
        model.put("notice", notice);
        LOG.info("edit Notice id={}", id);
        return "sys/notice/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("notice", new Notice());
        List<Site> siteList = siteService.queryAll();
        
        model.put("siteList", siteList);
        return "sys/notice/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete Notice id={}", ids);
        noticeService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Notice notice) {
        LOG.info("submit Notice {}", notice);
        
        buildExtCreatorPara(request, notice);
        if (notice != null && notice.isNew()) {
            noticeService.insert(notice);
        } else {
            noticeService.update(notice);
        }

        return "redirect:list.htm";
    }

}
