package com.summer.whm.web.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.sys.Authentication;
import com.summer.whm.service.sys.AuthenticationService;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/authentication")
public class AuthenticationController extends BaseController {
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Authentication> page = authenticationService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "sys/authentication/list.ftl";
    }
}
