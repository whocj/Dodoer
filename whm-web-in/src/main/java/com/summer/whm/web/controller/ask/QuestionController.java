package com.summer.whm.web.controller.ask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/ask/question")
public class QuestionController extends BaseController {
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "frame/ask_main.ftl";
    }

    @RequestMapping("/left")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        return "frame/ask_left.ftl";
    }
}
