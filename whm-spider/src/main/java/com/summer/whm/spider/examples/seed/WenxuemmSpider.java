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

public class WenxuemmSpider {

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
                  "http://www.wenxuemm.com/book/sort16/0/1.html",
                  "http://www.wenxuemm.com/book/sort16/0/2.html",
                  "http://www.wenxuemm.com/book/sort16/0/3.html",
                  "http://www.wenxuemm.com/book/sort16/0/4.html",
                  "http://www.wenxuemm.com/book/sort16/0/5.html",
                  "http://www.wenxuemm.com/book/sort16/0/6.html",
                  "http://www.wenxuemm.com/book/sort16/0/7.html",
                  "http://www.wenxuemm.com/book/sort16/0/8.html",
                  "http://www.wenxuemm.com/book/sort16/0/9.html",
                  "http://www.wenxuemm.com/book/sort16/0/10.html",
                  "http://www.wenxuemm.com/book/sort16/0/11.html",
                  "http://www.wenxuemm.com/book/sort16/0/12.html",
                  
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
            List<StorySeed> seedList = new ArrayList<StorySeed>();
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//table[@class='grid']/tbody/tr/td[1]/a");
            if (anchorList != null && anchorList.size() > 0) {
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));
                    
                    if (htmlAnchor.getAttribute("href") != null) {
                      seedList.add(new StorySeed(htmlAnchor.getTextContent(), "40", "1", htmlAnchor.getAttribute("href"), ""));     
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
