package com.summer.whm.spider.script.mianhuatang;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.IBshScript;

public class StoryDetailAnchor implements IBshScript {

    @Override
    public Object process(java.util.Map<String, Object> map) {

        com.gargoylesoftware.htmlunit.html.HtmlPage htmlPage = (com.gargoylesoftware.htmlunit.html.HtmlPage) map
                .get("htmlPage");
        // String url = htmlPage.getWebResponse().getRequestSettings().getUrl().toString();
        // String id = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        java.util.List elementList = (java.util.List) htmlPage.getByXPath("//dl/dd/a");
        if (elementList != null && elementList.size() > 0) {
            java.util.Map anchorMap = new java.util.HashMap();
            java.util.List list = new java.util.ArrayList();
            for (int i = 0; i < elementList.size(); i++) {
                com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList
                        .get(i);
                list.add(new com.summer.whm.spider.model.html.Anchor(element.getAttribute("href"), element.getTextContent()));
            }
            return list;
        }
        
        return null;
    }

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.mianhuatang.la/0/1/");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
        StoryDetailAnchor storyDetailAnchor = new StoryDetailAnchor();
        Object obj = storyDetailAnchor.process(map);
        System.out.println(obj);
        
    }

}
