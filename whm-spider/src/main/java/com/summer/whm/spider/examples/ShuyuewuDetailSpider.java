package com.summer.whm.spider.examples;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ShuyuewuDetailSpider {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        try {
            // final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_2, "10.19.110.31", 8080);
            final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_2);
            // ProxyConfig proxyConfig = new ProxyConfig();
            // proxyConfig.setProxyAutoConfigUrl("http://it.cnsuning.com/zongbu.pac");
            // webclient.setProxyConfig(proxyConfig);

            // final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_2, "192.168.71.33", 8080);

            webclient.setThrowExceptionOnScriptError(false);
            webclient.setThrowExceptionOnFailingStatusCode(false);
            webclient.setJavaScriptEnabled(false);
            webclient.setActiveXNative(false);
            webclient.setCssEnabled(false);
            webclient.setThrowExceptionOnScriptError(false);
            webclient.setTimeout(3000);

            final HtmlPage htmlpage = webclient.getPage("http://www.shuyuewu.com/kan_75582/13721033.html");
            String result = htmlpage.asXml();
            final HtmlDivision div = (HtmlDivision) htmlpage.getByXPath("//div[@id='content']").get(0);
                
            System.out.println(div.asText());
            
            String[] strs = div.asText().split("\n\r");
            strs[0] = "";
            strs[strs.length - 1] = "";
            String html = StringUtils.join(strs, "<br/>");
            System.out.println(html);
            // System.out.println(Arrays.toString(strs));

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
