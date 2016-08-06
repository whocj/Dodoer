package com.summer.whm.web.controller.index;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.service.sys.SitemapService;

@Controller
public class SitemapController {

    @Autowired
    private SitemapService sitemapService;

    @RequestMapping("sitemap")
    public String sitemap(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        Map<String, List<String>> sitemap = sitemapService.buildSitemap(GlobalConfigHolder.SITEMAP_COUNT);
        model.put("sitemap", sitemap);
        return "sitemap.ftl";
    }

}
