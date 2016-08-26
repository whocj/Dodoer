package com.summer.whm.spider.script.zhuaji;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.IBshScript;

public class StoryNextAnchor implements IBshScript {

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
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.zhuaji.org/book/2522");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
        List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@class='one_1']/a");
        if (nextAnchorList != null && nextAnchorList.size() > 0) {
            try {
                HtmlPage nextHtmlPage = nextAnchorList.get(0).click();
                System.out.println(nextHtmlPage.asXml());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }

}
