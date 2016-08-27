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
import com.summer.whm.spider.client.WebClientPool;
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
        String[] urls = new String[] { 
                  "http://www.shuyuewu.com/kehuan/"
                , "http://www.shuyuewu.com/kehuan/2.html"
                , "http://www.shuyuewu.com/kehuan/3.html"
                , "http://www.shuyuewu.com/kehuan/4.html"
                , "http://www.shuyuewu.com/kehuan/5.html"
                , "http://www.shuyuewu.com/kehuan/6.html"
                , "http://www.shuyuewu.com/kehuan/7.html"
                , "http://www.shuyuewu.com/kehuan/8.html"
                , "http://www.shuyuewu.com/kehuan/9.html"
                , "http://www.shuyuewu.com/kehuan/10.html"
                , "http://www.shuyuewu.com/kehuan/11.html"};
        List<StorySeed> list = new ArrayList<StorySeed>();
        for (String url : urls) {
            List<StorySeed> storySeedList = buildStorySeed(url);
            if (storySeedList != null && storySeedList.size() > 0) {
                list.addAll(storySeedList);
            }
        }

        StorySeedService.saveFile(list);
    }
    
    public static List<StorySeed> buildStorySeed(String url) {
        try {

            WebClientPool webClientPool = new WebClientPool();
            final HtmlPage htmlpage = webClientPool.borrowWebClient().getPage(url);
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//div[@class='l']/ul/li/span[1]/a");
            if (anchorList != null && anchorList.size() > 0) {
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));
                    if (htmlAnchor.getAttribute("href") != null) {
                        seedList.add(new StorySeed(htmlAnchor.getTextContent(), "25", "1", htmlAnchor
                                .getAttribute("href"), ""));
                    }
                }
            }
            
            return seedList;
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
