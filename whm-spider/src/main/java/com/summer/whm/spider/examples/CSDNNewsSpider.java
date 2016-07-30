package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CSDNNewsSpider {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws FailingHttpStatusCodeException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3, "10.19.110.31", 8080);
        webclient.setThrowExceptionOnScriptError(false);  
        webclient.setThrowExceptionOnFailingStatusCode(false);  
        webclient.setJavaScriptEnabled(false);  
        webclient.setActiveXNative(false);  
        webclient.setCssEnabled(false);  
        webclient.setThrowExceptionOnScriptError(false);
        
        HtmlPage htmlpage = webclient.getPage("http://www.csdn.net/article/2009-04-01/268381-210092");
        
        List<Object> anchorList = (List<Object>) htmlpage.getByXPath("//div[@class='detail']/h1[@class='title']");
//        htmlpage = anchorList.get(0).click();
        //HtmlDivision
        //HtmlAnchor
        System.out.println(anchorList.size());
        System.out.println(anchorList.get(0));
    }

}
