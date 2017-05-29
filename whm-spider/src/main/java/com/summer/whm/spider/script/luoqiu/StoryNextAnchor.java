package com.summer.whm.spider.script.luoqiu;

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
        java.util.List elementList = (java.util.List) htmlPage.getByXPath("//div[@id='centerm']/table[1]/tbody/tr/td/table[3]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr[5]/td/a[2]");
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
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.luoqiu.com/book/102151.html");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
      
       //List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@id='content']/div[2]/div[2]/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/table/tbody/tr[4]/td/div/a[1]");
        List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@style='height:20px; float:left; margin-top:0px;font-size:14px']/a[1]");
       System.out.println(nextAnchorList); 
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
