package com.summer.whm.spider.script.xyshu8;

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
        return null;
    }

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.xyshu8.com/Book/24422/Index.aspx");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
       List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@class='button']/ul/li[1]/a");
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
