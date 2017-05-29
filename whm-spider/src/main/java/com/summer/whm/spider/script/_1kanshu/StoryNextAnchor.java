package com.summer.whm.spider.script._1kanshu;

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
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.bxwx8.org/binfo/70/70093.htm");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
//        List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@id='centerm']/table[1]/tbody/tr/td/table[3]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr[5]/td/a[2]");
      //div[@id='centerm']/table[1]/tbody/tr/td/table[3]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr[5]/td/a[2]
      //div[@id='centerm']/table[1]/tbody/tr/td/table[3]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr[5]/td/a[2]
       List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath("//div[@id='centerm']/table[1]/tbody/tr/td/table[3]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr[5]/td/a[2]");
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
