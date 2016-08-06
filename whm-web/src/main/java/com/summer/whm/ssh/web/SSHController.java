package com.summer.whm.ssh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.common.utils.MD5;

@Controller
@RequestMapping("/ssh")
public class SSHController {
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String key = MD5.encode(System.currentTimeMillis() + "");
        request.setAttribute("key", key);
        return "ssh/index.ftl";
    }
}
