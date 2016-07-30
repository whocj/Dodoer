package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ImgSpider {

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
        
        final HtmlPage htmlpage = webclient.getPage("http://www.baidu.com/");
        List<HtmlElement> imgList = (List<HtmlElement>) htmlpage.getByXPath("//img");
        for(HtmlElement img : imgList){
            System.out.println(img.getAttribute("src"));
        }
            
            
        System.out.println(imgList.size());
    }

}
