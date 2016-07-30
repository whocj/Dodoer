package com.summer.whm.web.controller.ask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/ask")
public class AskController extends BaseController {
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "frame/ask_main.ftl";
    }

    @RequestMapping("/left")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        return "frame/ask_left.ftl";
    }
    
    @RequestMapping("/right")
    public String right(HttpServletRequest request, HttpServletResponse response) {
        return "ask/right.ftl";
    }
}
