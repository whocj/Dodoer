package com.summer.whm.web.controller.examples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/example")
public class ExampleController extends BaseController {
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "examples/index.ftl";
    }
}
