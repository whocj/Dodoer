package com.summer.whm.spider.script.d8qu;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.IBshScript;

public class StoryNextAnchor implements IBshScript {

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.d8qu.com/books/86865.html");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
       List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@class='btn']/a[1]");
        if (nextAnchorList != null && nextAnchorList.size() > 0) {
            try {
                HtmlPage nextHtmlPage = nextAnchorList.get(0).click();
                System.out.println(nextHtmlPage.asXml());
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public Object process(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }

}
