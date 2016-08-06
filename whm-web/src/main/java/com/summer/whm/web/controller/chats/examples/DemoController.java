package com.summer.whm.web.controller.chats.examples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.user.User;
import com.summer.whm.web.controller.BaseController;


@Controller
@RequestMapping("/chats/demo1")
public class DemoController extends BaseController{
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }

        return "chats/demo1/index.ftl";
    }
}
