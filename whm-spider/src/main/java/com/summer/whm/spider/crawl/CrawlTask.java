package com.summer.whm.spider.crawl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.BlockingQueue;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.entiry.spider.CrawInfo;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.spider.SpiderContext;
import com.summer.whm.spider.client.WebClientPool;
import com.summer.whm.spider.parse.ParseDetailElement;
import com.summer.whm.spider.parse.ParseListELement;
import com.summer.whm.spider.parse.ParseStoryElement;
import com.summer.whm.spider.service.CrawInfoService;

public class CrawlTask implements Runnable {

    private SpiderContext spiderContext;
    private WebClient[] webclient = new WebClient[SpiderConfigs.WEBCLIENT_COUNT];
    private WebClientPool webClientPool = new WebClientPool();
    private CrawInfoService crawInfoService;

    private Object obj = new Object();
    private int status = 0;// 0默认，1运行，2暂停，99停止

    public CrawlTask(SpiderContext spiderContext, CrawInfoService crawInfoService) {
        this.spiderContext = spiderContext;
        initWebClient();
        this.crawInfoService = crawInfoService;
    }

    public void initWebClient() {
        for (int i = 0; i < SpiderConfigs.WEBCLIENT_COUNT; i++) {
            webclient[i] = new WebClient(BrowserVersion.FIREFOX_3);
//            ProxyConfig proxyConfig = new ProxyConfig();
//            proxyConfig.setProxyAutoConfigUrl("http://it.cnsuning.com/zongbu.pac");
//            webclient[i].setProxyConfig(proxyConfig);
            
            webclient[i].setThrowExceptionOnScriptError(false);
            webclient[i].setThrowExceptionOnFailingStatusCode(false);
            webclient[i].setJavaScriptEnabled(false);
            webclient[i].setActiveXNative(false);
            webclient[i].setCssEnabled(false);
            webclient[i].setThrowExceptionOnScriptError(false);
            webclient[i].setTimeout(3000);
        }
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();
            BlockingQueue<ParseDetailElement> parseDetailQueue = spiderContext.getParseDetailQueue();
            BlockingQueue<ParseListELement> parseListQueue = spiderContext.getParseListQueue();
            BlockingQueue<ParseStoryElement> parseStoryQueue = spiderContext.getParseStoryQueue();

            CrawlElement crawlElement = null;
            HtmlPage htmlPage = null;
            while (!done) {
                synchronized (obj) {
                    // 取出队首元素，如果队列为空，则阻塞
                    crawlElement = urlQueue.take();
                    if (CrawlType.End.equals(crawlElement.getType())) {
                        System.out.println(Thread.currentThread().getName() + " Stop.");
                        spiderContext.setDone(true);
                        parseDetailQueue.put(new ParseDetailElement(true));
                        parseListQueue.put(new ParseListELement(true));
                        parseStoryQueue.put(new ParseStoryElement(true));
                        return;
                    }

                    if (status == SpiderContext.THREAD_STATUS_PAUSE) {
                        obj.wait();
                    }

                    if (CrawlType.Detail.equals(crawlElement.getType()) || CrawlType.StoryDetail.equals(crawlElement.getType())) {
                        if (isCraw(crawlElement.getUrl())) {
                            insertDB(crawlElement.getUrl());
                        } else {
                            continue;
                        }
                    }
                    
                    Thread.sleep(GlobalConfigHolder.SPIDER_SLEEP_TIME);// 休息1秒钟
                    htmlPage = crawl(crawlElement.getUrl());
                    if (htmlPage != null) {
                        if (CrawlType.Detail.equals(crawlElement.getType())) {
                            parseDetailQueue.put(new ParseDetailElement(htmlPage));
                        } else if (CrawlType.List.equals(crawlElement.getType())) {
                            parseListQueue.put(new ParseListELement(htmlPage));
                        } else if (CrawlType.StoryInfo.equals(crawlElement.getType())) {
                            parseStoryQueue.put(new ParseStoryElement(false, htmlPage));
                        } else if (CrawlType.StoryDetail.equals(crawlElement.getType())) {
                            parseStoryQueue.put(new ParseStoryElement(true, htmlPage));
                        }
                    } else {
                        System.out.println("Null-URL:" + crawlElement.getUrl());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clean() {
        spiderContext.getUrlQueue().clear();
        spiderContext.getParseDetailQueue().clear();
        spiderContext.getParseListQueue().clear();
    }

    public void stop() {
        try {
            clean();
            if (status == SpiderContext.THREAD_STATUS_PAUSE) {
                reStart();
            }
            spiderContext.getUrlQueue().put(new CrawlElement(CrawlType.End));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status = SpiderContext.THREAD_STATUS_STOP;
    }

    public void pause() {
        status = SpiderContext.THREAD_STATUS_PAUSE;
    }

    public void reStart() {
        status = SpiderContext.THREAD_STATUS_RUN;
        obj.notify();
    }

    public HtmlPage crawl(String url) {
        try {
            HtmlPage htmlPage = webClientPool.borrowWebClient().getPage(url);
            System.out.println("URL=" + url);
            return htmlPage;
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insertDB(String url) {
        try {
            crawInfoService.insert(new CrawInfo(url, spiderContext.getTemplateId(), spiderContext
                    .getCrawLog().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCraw(String url) {
        try {
            CrawInfo info = crawInfoService.queryByUrl(url);
            if (info == null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {

    }

}
