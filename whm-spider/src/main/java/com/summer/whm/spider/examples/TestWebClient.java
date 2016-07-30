package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestWebClient {

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
        HtmlPage htmlpage = webclient.getPage("http://blog.csdn.net/zstu_cc/article/details/42065439");
        System.out.println(htmlpage.asText());
        webclient.closeAllWindows();
        
        htmlpage = webclient.getPage("http://blog.csdn.net/zstu_cc/article/details/42089877");
        System.out.println(htmlpage.asText());
        webclient.closeAllWindows();
    }

}
