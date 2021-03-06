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
                  "http://www.biquge.tw/quanben/"
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
            List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//div[@class='topbooks']/ul/li/a");
            if (anchorList != null && anchorList.size() > 0) {
                int i = 0;
                for (HtmlAnchor htmlAnchor : anchorList) {
                    System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));
                    i++;
                    String dirId = null;
                    if (htmlAnchor.getAttribute("href") != null) {
                        if(i / 45 == 0){
                            dirId = "30";
                        }else if(i / 45 == 1){
                            dirId = "29";
                        }else if(i / 45 == 2){
                            dirId = "28";
                        }else if(i / 45 == 3){
                            dirId = "27";
                        }else if(i / 45 == 4){
                            dirId = "25";
                        }else if(i / 45 == 5){
                            dirId = "26";
                        }else if(i / 45 == 6){
                            dirId = "28";
                        }
                        if(dirId != null){
                            seedList.add(new StorySeed(htmlAnchor.getTextContent(), dirId, "1", "http://www.biquge.tw"+htmlAnchor.getAttribute("href"), ""));     
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

}
