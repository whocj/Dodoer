package com.summer.whm.web.controller.sys.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.web.common.utils.ShellUtils;

@Controller
@RequestMapping("/sys/app/release")
public class AppReleaseController {
    
    public static final String DOMAIN = "http://127.0.0.1";
    
    @RequestMapping("/main")
    public String main(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        return "sys/app/main.ftl";
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        return "sys/app/file/upload.ftl";
    }

    @RequestMapping("/restart")
    public void restart(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model)
            throws Exception {
        ShellUtils.execShell("service default resetart");
    }
    
    @RequestMapping("/initIndex")
    public void initIndex(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model)
            throws Exception {
        WebClientPool webClientPool = new WebClientPool();
        webClientPool.borrowWebClient().getPage(DOMAIN + "/admin/initAssWordIndex.html");
    }
    
    @RequestMapping("/initAsswordFile")
    public void initAsswordFile(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model)
            throws Exception {
        WebClientPool webClientPool = new WebClientPool();
        webClientPool.borrowWebClient().getPage(DOMAIN + "/admin/iniAssWordFile.html");
    }
}
