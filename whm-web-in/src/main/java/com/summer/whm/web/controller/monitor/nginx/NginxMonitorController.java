package com.summer.whm.web.controller.monitor.nginx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/monitor/nginx")
public class NginxMonitorController extends BaseController {

    @RequestMapping("/view")
    public String view(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "sys/monitor/nginx/index.ftl";
    }
}
