package com.summer.whm.spider.script.aiyousheng;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.IBshScript;

public class DetailContent implements IBshScript {

    @Override
    public String process(Map<String, Object> map) {
        com.gargoylesoftware.htmlunit.html.HtmlPage htmlPage = (com.gargoylesoftware.htmlunit.html.HtmlPage)map.get("htmlPage");
        String url = htmlPage.getWebResponse().getRequestSettings().getUrl().toString();
        String id = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        java.util.List elementList = htmlPage.getByXPath("//div[@id='content" + id + "']");
        java.util.List tempElementList = null;
        
        String text = null;
        if (elementList != null && elementList.size() > 0) {
            com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement)elementList.get(0);
            
            tempElementList = element.getByXPath("//div[@class='other_links']");
            if(tempElementList != null && tempElementList.size() > 0){
                ((com.gargoylesoftware.htmlunit.html.HtmlElement)tempElementList.get(0)).remove();
            }
            tempElementList = element.getByXPath("//div[@class='chapter_go']");
            if(tempElementList != null && tempElementList.size() > 0){
                ((com.gargoylesoftware.htmlunit.html.HtmlElement)tempElementList.get(0)).remove();
            }
            
            text = element.asText();
            String[] strs = text.split("\n");
            text = "";
            boolean f = true;
            for(int i = 0; i < strs.length; i++){
                if(!"".equals(strs[i].trim())){
                    if(f){
                        text = "<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + strs[i].trim();
                        f = false;
                    }else{
                        text = text + "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;" + strs[i].trim();
                    }
                }
            }
        }
        return text;

    }
    
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.aiyousheng.com/22755/14933608.html");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        
        DetailContent detailContent = new DetailContent();
        Object obj = detailContent.process(map);
        System.out.println(obj);
        
    }

}
