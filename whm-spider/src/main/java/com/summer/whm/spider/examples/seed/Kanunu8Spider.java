package com.summer.whm.spider.examples.seed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.model.seed.StorySeed;
import com.summer.whm.spider.service.StorySeedService;

public class Kanunu8Spider {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String[] urls = new String[] { "http://www.kanunu8.com/files/writer/155.html" };
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
           // List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//td[@bgcolor='#ffffff']/a");
            //List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//td/strong/a");
           List<HtmlAnchor> anchorList = (List<HtmlAnchor>) htmlpage.getByXPath("//td[@class='p10-21']/a");
            String hrefTxt = "";
            String hrefUrl = "";
            String href = null;
            if (anchorList != null && anchorList.size() > 0) {
                for (HtmlAnchor htmlAnchor : anchorList) {
                    if (htmlAnchor.getAttribute("href") != null) {
                        hrefTxt = htmlAnchor.getTextContent();
                        hrefTxt = hrefTxt.trim();
                        if(StringUtils.isNotEmpty(hrefTxt)){
                            System.out.println(htmlAnchor.getTextContent() + " url=" + htmlAnchor.getAttribute("href"));
                            hrefTxt =  hrefTxt.replace("《", "");
                            hrefTxt = hrefTxt.replace("》", "");
                            href = htmlAnchor.getAttribute("href");
                            if(href.startsWith("/book") && !href.endsWith(".html")){
                                hrefUrl = "http://www.kanunu8.com" + href;
                                seedList.add(new StorySeed(hrefTxt, "40", "20", hrefUrl, ""));
                            }else if(href.startsWith("/files") && !href.startsWith("/files/little")){
                                if(href.split("/").length <= 5){
                                    hrefUrl = "http://www.kanunu8.com" + href;
                                    seedList.add(new StorySeed(hrefTxt, "40", "30", hrefUrl, ""));
                                }
                            }
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
