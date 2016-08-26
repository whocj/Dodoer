package com.summer.whm.spider.script.aiyousheng;

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
        java.util.List list = new java.util.ArrayList();
        
        java.util.List elementList = (java.util.List) htmlPage.getByXPath("//p[@class='tuijian']/a");
        if (elementList != null && elementList.size() > 0) {
            for (int i = 0; i < elementList.size(); i++) {
                com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList
                        .get(i);
                System.out.println(element.getTextContent() + element.getAttribute("href"));
                list.add(new com.summer.whm.spider.model.html.Anchor(element.getAttribute("href"), element.getTextContent()));
            }
        }
        elementList = (java.util.List) htmlPage.getByXPath("//div[@class='haoshu']/a");
        if (elementList != null && elementList.size() > 0) {
            for (int i = 0; i < elementList.size(); i++) {
                com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList
                        .get(i);
                System.out.println(element.getTextContent() + element.getAttribute("href"));
                list.add(new com.summer.whm.spider.model.html.Anchor(element.getAttribute("href"), element.getTextContent()));
            }
        }
        
        return list;
    }

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.aiyousheng.com/22755/");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
        StorySeedAnchor storyDetailAnchor = new StorySeedAnchor();
        Object obj = storyDetailAnchor.process(map);
        System.out.println(obj);
        
    }

}
