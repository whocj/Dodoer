package com.summer.whm.spider.examples.seed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.model.seed.StorySeed;
import com.summer.whm.spider.service.StorySeedService;

public class ShuyuewuSpider {

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
            ProxyConfig proxyConfig = new ProxyConfig();
            proxyConfig.setProxyAutoConfigUrl("http://it.cnsuning.com/zongbu.pac");
            webclient.setProxyConfig(proxyConfig);

            // final WebClient webclient = new WebClient(BrowserVersion.FIREFOX_2, "192.168.71.33", 8080);

            webclient.setThrowExceptionOnScriptError(false);
            webclient.setThrowExceptionOnFailingStatusCode(false);
            webclient.setJavaScriptEnabled(false);
            webclient.setActiveXNative(false);
            webclient.setCssEnabled(false);
            webclient.setThrowExceptionOnScriptError(false);
            webclient.setTimeout(3000);

            final HtmlPage htmlpage = webclient.getPage("http://www.shuyuewu.com/kehuan/");

            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//li/span[@class='s2']/a");
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            if (anchorList != null && anchorList.size() > 0) {
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));
                    if (htmlAnchor.getAttribute("href") != null) {
                        seedList.add(new StorySeed(htmlAnchor.getTextContent(), "25", "1", htmlAnchor
                                .getAttribute("href"), "* 1 2 * * * *"));
                    }
                }
            }
            StorySeedService.saveFile(seedList);
            System.out.println("========");
            System.out.println(htmlpage.getTitleText());
            System.out.println("========");

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
