package com.summer.whm.web.controller.index;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index.ftl";
    }

    @RequestMapping("/index/top")
    public String top(HttpServletRequest request, ModelMap model) {
        // 需要获得站点列表
        return "top.ftl";
    }

    @RequestMapping("/index/main")
    public String main() {
        return "main.ftl";
    }

    @RequestMapping("/index/left")
    public String left() {
        return "left.ftl";
    }

    @RequestMapping("/index/right")
    public String right() {
        return "right.ftl";
    }
}
