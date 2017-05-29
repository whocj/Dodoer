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

public class Qbxs8Spider {

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

        String[] urls = new String[] {"http://www.qbxs8.net/wangfei.asp?page=1",
                "http://www.qbxs8.net/wangfei.asp?page=2",
                "http://www.qbxs8.net/wangfei.asp?page=3",
                "http://www.qbxs8.net/wangfei.asp?page=4",
                "http://www.qbxs8.net/wangfei.asp?page=5",
                "http://www.qbxs8.net/wangfei.asp?page=6",
                "http://www.qbxs8.net/wangfei.asp?page=7",
                "http://www.qbxs8.net/wangfei.asp?page=8",
                "http://www.qbxs8.net/wangfei.asp?page=9",
                "http://www.qbxs8.net/wangfei.asp?page=10",
                "http://www.qbxs8.net/wangfei.asp?page=11",
                "http://www.qbxs8.net/wangfei.asp?page=12",
                "http://www.qbxs8.net/wangfei.asp?page=13",
                "http://www.qbxs8.net/wangfei.asp?page=14",
                "http://www.qbxs8.net/wangfei.asp?page=15",
                "http://www.qbxs8.net/wangfei.asp?page=16",
                "http://www.qbxs8.net/wangfei.asp?page=17",
                "http://www.qbxs8.net/wangfei.asp?page=18",
                "http://www.qbxs8.net/wangfei.asp?page=19",
                "http://www.qbxs8.net/wangfei.asp?page=20",
                "http://www.qbxs8.net/wangfei.asp?page=21",
                "http://www.qbxs8.net/wangfei.asp?page=22",
                "http://www.qbxs8.net/wangfei.asp?page=23",
                "http://www.qbxs8.net/wangfei.asp?page=24"
};
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
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//h4/em/a");
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            if (anchorList != null && anchorList.size() > 0) {
                String href = "";
                String id = "";
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));

                    href = htmlAnchor.getAttribute("href");
                    if (href != null) {
                        Integer categoryId = null;
                        try {
                            //Thread.sleep(2000);
                            //HtmlPage page = webClientPool.borrowWebClient().getPage(href);
                            categoryId = 28;
                        } catch (Exception e) {
                            
                        }
                        if (categoryId != null) {
                            seedList.add(new StorySeed(htmlAnchor.getTextContent(), categoryId + "", "47",  href,
                                    "* 1 2 * * * *"));
                        }
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

    public static Integer getCategoryId(HtmlPage page) {
        java.util.List elementList = (java.util.List) page.getByXPath("//div[@class='path']/a");
        java.lang.Integer ret = null;
        if (elementList != null && elementList.size() > 1) {
            com.gargoylesoftware.htmlunit.html.HtmlElement element = (com.gargoylesoftware.htmlunit.html.HtmlElement) elementList
                    .get(1);
            String categoryName = element.asText();
            categoryName = categoryName.trim();

            if ("玄幻小说".equals(categoryName)) {
                ret = 30;
            } else if ("修真小说".equals(categoryName)) {
                ret = 29;
            } else if ("都市小说".equals(categoryName)) {
                ret = 28;
            } else if ("穿越小说".equals(categoryName)) {
                ret = 32;
            } else if ("网游小说".equals(categoryName)) {
                ret = 26;
            } else if ("科幻小说".equals(categoryName)) {
                ret = 25;
            } else if ("悬疑小说".equals(categoryName)) {
                ret = 32;
            } else if ("言情小说".equals(categoryName)) {
                ret = 28;
            }
            return ret;
        }

        return ret;
    }

}
