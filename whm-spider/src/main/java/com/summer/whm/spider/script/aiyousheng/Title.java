package com.summer.whm.spider.script.aiyousheng;


import java.util.Map;

import com.summer.whm.spider.script.IBshScript;

public class Title implements IBshScript {

    @Override
    public String process(Map<String, Object> map) {
        com.gargoylesoftware.htmlunit.html.HtmlPage htmlPage = (com.gargoylesoftware.htmlunit.html.HtmlPage)map.get("htmlPage");
        java.util.List titleElementList = (java.util.List) htmlPage.getByXPath("//div[@id='info']/h1");
        String ret = null;
        if (titleElementList != null && titleElementList.size() > 0) {
            com.gargoylesoftware.htmlunit.html.HtmlElement titleHtmlElement = (com.gargoylesoftware.htmlunit.html.HtmlElement)titleElementList.get(0);
            ret = titleHtmlElement.asText();
        } else {
            ret = htmlPage.getTitleText();
        }

        if (ret == null || "".equals(ret.trim())) {
            ret = "无题";
        }

        return ret;

    }

}
