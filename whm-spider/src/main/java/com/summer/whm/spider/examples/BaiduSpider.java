package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BaiduSpider {
    public static void main(String[] args) {

        try {
            final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_2, "10.19.110.31", 8080);
            webclient.setThrowExceptionOnScriptError(false);  
            webclient.setThrowExceptionOnFailingStatusCode(false);  
            webclient.setJavaScriptEnabled(false);  
            webclient.setActiveXNative(false);  
            webclient.setCssEnabled(false);  
            webclient.setThrowExceptionOnScriptError(false);
            
            final HtmlPage htmlpage = webclient.getPage("http://blog.csdn.net/zstu_cc/article/details/42065439");
            String result = htmlpage.asXml();
            final HtmlDivision div = (HtmlDivision) htmlpage.getByXPath("//div[@id='article_details']").get(0);
            System.out.println(div.asText());
            div.getByXPath("");
            
            
            System.out.println("========");
            System.out.println(htmlpage.getTitleText());
            System.out.println("========");
            System.out.println(htmlpage.getTextContent());
            

//            System.out.println(baos.toString());
            webclient.closeAllWindows();
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
