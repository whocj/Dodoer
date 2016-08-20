package com.summer.whm.spider.script.aiyousheng;

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
            
            //去重
            for (int i = 0; i < elementList.size(); i++) {
                com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList
                        .get(i);
                System.out.println(element.getTextContent() + element.getAttribute("href"));
                anchorMap.put(element.getAttribute("href"), element.getTextContent());
            }
            
            //排序
            java.util.List list = new java.util.ArrayList();
            java.util.Iterator iterator = anchorMap.entrySet().iterator();
            while(iterator.hasNext()){
                java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
                list.add(new com.summer.whm.spider.model.html.Anchor((String)entry.getKey(), (String)entry.getValue()));
            }
            java.util.Collections.sort(list, new java.util.Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    com.summer.whm.spider.model.html.Anchor a1 = (com.summer.whm.spider.model.html.Anchor)o1;
                    com.summer.whm.spider.model.html.Anchor a2 = (com.summer.whm.spider.model.html.Anchor)o2;
                    
                    if(a1.getUrl().compareTo(a2.getUrl()) > 0){
                        return 1;
                    }else {
                        return -1;
                    }
                }});
            return list;
        }
        
        return null;
    }

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.aiyousheng.com/17612/");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
        StoryDetailAnchor storyDetailAnchor = new StoryDetailAnchor();
        Object obj = storyDetailAnchor.process(map);
        System.out.println(obj);
        
    }

}
