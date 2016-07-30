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
import com.summer.whm.entiry.spider.CrawInfo;
import com.summer.whm.spider.service.CrawInfoService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/crawInfo")
public class CrawInfoController extends BaseController {
    
    private static final Logger LOG = LoggerFactory.getLogger(CrawInfoController.class);
    
    @Autowired
    private CrawInfoService crawInfoService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<CrawInfo> page = crawInfoService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/crawInfo/list.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete CrawInfo id={}", ids);
        crawInfoService.deleteById(ids);
        return "redirect:list.htm";
    }
}
