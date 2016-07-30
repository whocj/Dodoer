package com.summer.whm.web.controller.spider;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.spider.CrawLog;
import com.summer.whm.spider.service.CrawLogService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/crawLog")
public class CrawLogController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CrawLogController.class);

    @Autowired
    private CrawLogService crawLogService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<CrawLog> page = crawLogService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "spider/crawLog/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        CrawLog crawLog = crawLogService.loadById(id + "");
        model.put("crawLog", crawLog);
        LOG.info("edit CrawLog id={}", id);
        return "spider/crawLog/edit.ftl";
    }

}
