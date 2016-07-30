package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Stackoverflow {
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//        final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3, "10.19.110.31", 8080);
        final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3);
        webclient.setThrowExceptionOnScriptError(false);  
        webclient.setThrowExceptionOnFailingStatusCode(false);  
        webclient.setJavaScriptEnabled(false);  
        webclient.setActiveXNative(false);  
        webclient.setCssEnabled(false);  
        webclient.setThrowExceptionOnScriptError(false);
        
        HtmlPage htmlpage = webclient.getPage("http://stackoverflow.com/questions?page=1&sort=votes");
//        final HtmlDivision div = (HtmlDivision) htmlpage.getByXPath("//div[@class='post-text']").get(0);
//        System.out.println(div.asXml());
//        List<HtmlDivision> divList = (List<HtmlDivision>) htmlpage.getByXPath("//div[@class='post-text']");
//        System.out.println(divList.size());
//        for(HtmlDivision division : divList){
//            System.out.println(division.asXml());
//        }
//        List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//a[@rel='next']");
//        htmlpage = anchorList.get(0).click();
        
        List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//a[@class='question-hyperlink']");
        for(HtmlAnchor htmlAnchor : anchorList){
            System.out.println(htmlAnchor.getAttribute("href"));
        }
       System.out.println(htmlpage.getWebResponse().getRequestSettings().getUrl());
    }
}
