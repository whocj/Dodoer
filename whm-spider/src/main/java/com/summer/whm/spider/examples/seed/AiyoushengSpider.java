package com.summer.whm.spider.examples.seed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.model.seed.StorySeed;
import com.summer.whm.spider.service.StorySeedService;

public class AiyoushengSpider {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws Exception {

        String[] urls = new String[] { "http://www.aiyousheng.com/xiaoyuan/",
                "http://www.aiyousheng.com/xiaoyuan/2.html", "http://www.aiyousheng.com/xiaoyuan/3.html",
                "http://www.aiyousheng.com/xiaoyuan/4.html", "http://www.aiyousheng.com/xiaoyuan/5.html",
                "http://www.aiyousheng.com/xiaoyuan/6.html", "http://www.aiyousheng.com/xiaoyuan/7.html",
                "http://www.aiyousheng.com/xiaoyuan/8.html", "http://www.aiyousheng.com/xiaoyuan/9.html",
                "http://www.aiyousheng.com/xiaoyuan/10.html", "http://www.aiyousheng.com/xiaoyuan/11.html" };
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
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//div[@class='title']/h2/a");
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            String baseUrl = "http://www.aiyousheng.com/";
            if (anchorList != null && anchorList.size() > 0) {
                String href = "";
                String id = "";
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));

                    href = htmlAnchor.getAttribute("href");
                    if (href != null) {
                        id = href.substring(href.lastIndexOf("/") + 1, href.lastIndexOf("."));
                        href = baseUrl + id + "/";
                        seedList.add(new StorySeed(htmlAnchor.getTextContent(), "31", "3", href, "* 1 2 * * * *"));
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
