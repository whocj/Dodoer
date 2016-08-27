package com.summer.whm.web.controller.story;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/story")
public class StoryController extends BaseController {
    
    private static final Logger LOG = LoggerFactory.getLogger(StoryController.class);
    
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "frame/story_main.ftl";
    }

    @RequestMapping("/left")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        return "story/left.ftl";
    }

    @RequestMapping("/right")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        return "story/right.ftl";
    }
}
