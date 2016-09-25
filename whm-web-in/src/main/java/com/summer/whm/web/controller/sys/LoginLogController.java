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
import com.summer.whm.entiry.sys.LoginLog;
import com.summer.whm.service.sys.LoginLogService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/loginLog")
public class LoginLogController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginLogController.class);
    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<LoginLog> page = loginLogService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/loginLog/list.ftl";
    }
    
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete LoginLog id={}", ids);
        loginLogService.deleteById(ids);
        return "redirect:list.htm";
    }
}
