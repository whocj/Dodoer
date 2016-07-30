package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class StackoverflowTag {
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
//        final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3, "10.19.110.31", 8080);
        final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3);
        webclient.setThrowExceptionOnScriptError(false);  
        webclient.setThrowExceptionOnFailingStatusCode(false);  
        webclient.setJavaScriptEnabled(false);  
        webclient.setActiveXNative(false);  
        webclient.setCssEnabled(false);  
        webclient.setThrowExceptionOnScriptError(false);
        
        HtmlPage htmlpage = webclient.getPage("http://stackoverflow.com/tags?page=1&tab=popular");

        do{
            System.out.println(htmlpage.getWebResponse().getRequestSettings().getUrl());
            printTag(htmlpage);
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//a[@rel='next']");
            htmlpage = anchorList.get(0).click();
        }while(htmlpage != null);
       
    }
    
    public static void printTag(HtmlPage htmlpage){
        List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//a[@class='post-tag']");
        for(HtmlAnchor htmlAnchor : anchorList){
            System.out.println(htmlAnchor.getTextContent());
        }
    }
}
