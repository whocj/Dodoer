package com.summer.whm.spider.script.aiyousheng;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.script.IBshScript;
import com.summer.whm.spider.service.SpiderSiteCategoryService;

public class StoryCategory implements IBshScript {

    @Override
    public Object process(java.util.Map<String, Object> map) {
        com.gargoylesoftware.htmlunit.html.HtmlPage htmlPage = (com.gargoylesoftware.htmlunit.html.HtmlPage) map
                .get("htmlPage");
        java.util.Map categoryMap = (java.util.Map) map.get("siteCategory");
        java.util.List elementList = (java.util.List) htmlPage.getByXPath("//div[@class='crumbs']");
        if (elementList != null && elementList.size() > 0) {
            com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList.get(0);
            com.gargoylesoftware.htmlunit.html.DomNode categoryNode = (com.gargoylesoftware.htmlunit.html.DomNode)element.getChildNodes().get(2);
            String categoryName = categoryNode.asText();
            categoryName = categoryName.replace("»", "");
            categoryName = categoryName.trim();
            
            if(categoryMap.get(categoryName) != null){
                return categoryMap.get(categoryName);
            }
        }

        return null;
    }

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
        WebClientPool webClientPool = new WebClientPool();
        HtmlPage htmlPage = null;
        htmlPage = webClientPool.borrowWebClient().getPage("http://www.aiyousheng.com/10046/");
        
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        map.put("htmlPage", htmlPage);
        map.put("siteCategory", SpiderSiteCategoryService.getCategoryMapByTemplateId(3));
        
        StoryCategory storyDetailAnchor = new StoryCategory();
        Object obj = storyDetailAnchor.process(map);
        System.out.println(obj);
        
    }

}
