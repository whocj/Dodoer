package com.summer.whm.spider.script.zilang;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.IBshScript;

public class StorySeedAnchor implements IBshScript {

    @Override
    public Object process(java.util.Map<String, Object> map) {

        com.gargoylesoftware.htmlunit.html.HtmlPage htmlPage = (com.gargoylesoftware.htmlunit.html.HtmlPage) map
                .get("htmlPage");
        java.util.List elementList = (java.util.List) htmlPage.getByXPath("//div[@class='blockcontent']/ul/li/a");
        if (elementList != null && elementList.size() > 0) {
            java.util.List list = new java.util.ArrayList();
            
            for (int i = 0; i < elementList.size(); i++) {
                com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList
                        .get(i);
                System.out.println(element.getTextContent() + element.getAttribute("href"));
                list.add(new com.summer.whm.spider.model.html.Anchor(element.getAttribute("href"), element.getTextContent()));
            }

            return list;
        }
        
        return null;
    }

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.shuyuewu.com/kan_64207/");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
        StorySeedAnchor storyDetailAnchor = new StorySeedAnchor();
        Object obj = storyDetailAnchor.process(map);
        System.out.println(obj);
        
    }

}
