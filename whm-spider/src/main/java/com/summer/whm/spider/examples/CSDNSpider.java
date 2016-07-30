package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CSDNSpider {

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
        System.setProperty("http.proxySet", "true");  
        System.setProperty("http.proxyHost", "10.19.110.31");  
        System.setProperty("http.proxyPort", "8080");
        
        System.setProperty("https.proxyHost", "10.19.110.31");
        System.setProperty("https.proxyPort", "8080");
        // 使用ftp代理服务器的主机、端口以及不需要使用ftp代理服务器的主机
        System.setProperty("ftp.proxyHost", "10.19.110.31");
        System.setProperty("ftp.proxyPort", "8080");
        // socks代理服务器的地址与端口
        System.setProperty("socks.proxyHost", "10.19.110.31");
        System.setProperty("socks.proxyPort", "8000");
        
        final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_3);
        webclient.setThrowExceptionOnScriptError(false);  
        webclient.setThrowExceptionOnFailingStatusCode(false);  
        webclient.setJavaScriptEnabled(false);  
        webclient.setActiveXNative(false);  
        webclient.setCssEnabled(false);  
        webclient.setThrowExceptionOnScriptError(false);
        
        HtmlPage htmlpage = webclient.getPage("http://blog.csdn.net/jiuqiyuliang/article/details/45286191");
        
        List<HtmlDivision> anchorList = (List<HtmlDivision>) htmlpage.getByXPath("//div[@class='article_content']");
//        htmlpage = anchorList.get(0).click();
        //HtmlDivision
        //HtmlAnchor
        System.out.println(anchorList.size());
        System.out.println(anchorList.get(0).asXml());
    }

}
