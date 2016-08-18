package com.summer.whm.spider.script.shuyuewu;

import java.util.Map;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.script.IBshScript;

public class Helloword implements IBshScript {


    @Override
    public String process(Map<String, Object> map) {
        String ret = "hello world!";
        com.gargoylesoftware.htmlunit.html.HtmlPage page = (com.gargoylesoftware.htmlunit.html.HtmlPage)map.get("htmlPage");
        java.util.List list = new java.util.ArrayList();
        list.add(ret);
        return list.toString();
    }

}
