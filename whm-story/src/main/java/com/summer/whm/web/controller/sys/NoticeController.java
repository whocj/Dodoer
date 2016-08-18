package com.summer.whm.web.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.sys.Notice;
import com.summer.whm.service.sys.NoticeService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
    
    @Autowired
    private NoticeService noticeService;
    
    @RequestMapping("/{id}")
    public String list(HttpServletRequest request, @PathVariable("id") int id, ModelMap model) {
        Notice notice = noticeService.loadById(id + "");
        model.put("notice", notice);
        return "notice/detail_index.ftl";
    }

}
