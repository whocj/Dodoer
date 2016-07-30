package com.summer.whm.spider.parse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.spider.SpiderContext;
import com.summer.whm.spider.crawl.CrawlElement;
import com.summer.whm.spider.crawl.CrawlType;
import com.summer.whm.spider.utils.URLUtil;

public class ParseListTask implements Runnable {
    
    public static final int SIZE = 5;
    
    private SpiderContext spiderContext;

    public ParseListTask(SpiderContext spiderContext) {
        this.spiderContext = spiderContext;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            BlockingQueue<ParseListELement> parseListQueue = spiderContext.getParseListQueue();
            ParseListELement parseListELement = null;
            while (!done) {
                // 取出队首元素，如果队列为空，则阻塞
                parseListELement = parseListQueue.take();
                try {
                    synchronized (this) {
                        if(parseListELement.isEnd()){
                            System.out.println(Thread.currentThread().getName() + " Stop.");
                            return;
                        }
                        
                        List<String> list = Arrays.asList(new String[]{null,null,null,null,null});
                        list.set(0, parseListELement.getHtmlPage().getWebResponse().getRequestSettings().getUrl().toString());
                        parse(parseListELement, 1, list);
                        parseListELement.getHtmlPage().getWebClient().closeAllWindows();
                    }
                } catch (Exception e) {
                    System.out.println(parseListELement.getHtmlPage().getWebResponse().getRequestSettings().getUrl());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public void parse(ParseListELement parseListELement, int current, List<String> list) throws InterruptedException {
        System.out.println("ListURL=" + parseListELement.getHtmlPage().getWebResponse().getRequestSettings().getUrl());
        BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();
        ListTemplate template = spiderContext.getListTemplate();

        HtmlPage htmlPage = parseListELement.getHtmlPage();
        List<HtmlAnchor> detailAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath(template.getDetailXPath());
        if (detailAnchorList != null && detailAnchorList.size() > 0) {
            String href = null;
            for (HtmlAnchor htmlAnchor : detailAnchorList) {
                href = htmlAnchor.getAttribute("href");
                if (href != null) {
                    if (URLUtil.getHost(href) != null) {
                        // 校验有站内连接
                        if (URLUtil.compile(URLUtil.getHost(href), URLUtil.getHost(template.getUrl()))) {
                            urlQueue.put(new CrawlElement(href, CrawlType.Detail));
                        }
                    } else if (href.startsWith("/")) {
                        urlQueue.put(new CrawlElement(SpiderConfigs.HTTP_PROTOCOL + URLUtil.getHost(template.getUrl()) + href,
                                CrawlType.Detail));
                    } else {
                        urlQueue.put(new CrawlElement(htmlPage.getWebResponse().getRequestSettings().getUrl() + "/"
                                + href, CrawlType.Detail));
                    }
                }
            }
        }
        
        //取下一页数据
        if(StringUtils.isNotEmpty(template.getNextXPath())){
            List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath(template.getNextXPath());
            if (nextAnchorList != null && nextAnchorList.size() > 0) {
                try {
                    // System.out.println(nextAnchorList.get(0).asXml());
                    HtmlPage nextHtmlPage = nextAnchorList.get(0).click();
                    int index = current % SIZE;
                    if(current % (SIZE * SIZE) == 0){
                        nextHtmlPage.getWebClient().closeAllWindows();
                        urlQueue.put(new CrawlElement(nextHtmlPage.getWebResponse().getRequestSettings().getUrl().toString(), CrawlType.List));
                        return;
                    }
                    //发现进入循环，则立即跳出去
                    if(list.contains(nextHtmlPage.getWebResponse().getRequestSettings().getUrl().toString())){
                        return;
                    }else{
                        list.set(index, nextHtmlPage.getWebResponse().getRequestSettings().getUrl().toString());
                    }
                    // System.out.println("NextURL=" +nextHtmlPage.getWebResponse().getRequestSettings().getUrl());
                    parse(new ParseListELement(nextHtmlPage, template), current + 1, list);
                } catch (IOException e) {
                    System.out.println(htmlPage.getWebResponse().getRequestSettings().getUrl());
                    e.printStackTrace();
                }
            }else{
                urlQueue.put(new CrawlElement(CrawlType.End));
            }
        }else{
            urlQueue.put(new CrawlElement(CrawlType.End));
        }
    }
}
