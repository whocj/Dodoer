package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ITEyeBlogSpider {

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException,
            GeneralSecurityException {
        WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3, "10.19.110.31", 8080);
        webclient.setUseInsecureSSL(true);
        webclient.setThrowExceptionOnScriptError(false);
        webclient.setThrowExceptionOnFailingStatusCode(false);
        webclient.setJavaScriptEnabled(false);
        webclient.setActiveXNative(false);
        webclient.setCssEnabled(false);
        webclient.setThrowExceptionOnScriptError(false);

        // HtmlPage htmlpage = webclient.getPage("http://www.baidu.com");
        HtmlPage htmlpage = webclient.getPage("https://imququ.com/archives.html");
        System.out.println(htmlpage.getBody());
        // List<Object> anchorList = (List<Object>) htmlpage.getByXPath("//div[@class='post']/div[@class='postBody']");
        List<HtmlAnchor> detailAnchorList = (List<HtmlAnchor>) htmlpage
                .getByXPath("//div[@class='entry-content']/ul/li/a");
        // System.out.println(anchorList.get(0));
        System.out.println(detailAnchorList);

        htmlpage = webclient.getPage("https://imququ.com/post/sth-about-switch-to-https-3.html");
        List<HtmlElement> titleElementList = (List<HtmlElement>) htmlpage.getByXPath("//h1[@class='title']");
        if (titleElementList != null && titleElementList.size() > 0) {
            System.out.println(titleElementList.get(0).asText());
        }

        List<HtmlElement> contentElementList = (List<HtmlElement>) htmlpage.getByXPath("//div[@class='entry-content']");
        if (contentElementList != null && contentElementList.size() > 0) {
            HtmlElement htmlElement = contentElementList.get(0);
            ((List<HtmlElement>)htmlElement.getByXPath("//div[@id='toc']")).get(0).remove();
            ((List<HtmlElement>)htmlElement.getByXPath("//p[@class='post-info']")).get(0).remove();
            ((List<HtmlElement>)htmlElement.getByXPath("//p[@class='donate-tips']")).get(0).remove();
            ((List<HtmlElement>)htmlElement.getByXPath("//div[@class='entry-series']")).get(0).remove();
            System.out.println(htmlElement.asXml());
        }
        
    }
}
