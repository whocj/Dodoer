package com.summer.whm.web.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "frame/sys_main.ftl";
    }

    @RequestMapping("/left")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        return "frame/sys_left.ftl";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        return "sys/right.ftl";
    }
}
