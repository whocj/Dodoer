package com.summer.whm.spider.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.StringUtils;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.service.stroy.StoryDetailService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryPartService;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.spider.SpiderContext;
import com.summer.whm.spider.crawl.CrawlElement;
import com.summer.whm.spider.crawl.CrawlType;
import com.summer.whm.spider.store.CrawlStore;
import com.summer.whm.spider.utils.URLUtil;
import com.summer.whm.spider.utils.img.Image;
import com.summer.whm.spider.utils.img.ImageService;

public class ParseStoryTask implements Runnable {

    public static final int SIZE = 5;

    private SpiderContext spiderContext;

    private StoryInfoService storyInfoService;

    private StoryPartService storyPartService;

    private StoryDetailService storyDetailService;

    public ParseStoryTask(SpiderContext spiderContext, StoryInfoService storyInfoService,
            StoryPartService storyPartService, StoryDetailService storyDetailService) {
        this.spiderContext = spiderContext;
        this.storyInfoService = storyInfoService;
        this.storyPartService = storyPartService;
        this.storyDetailService = storyDetailService;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            BlockingQueue<ParseStoryElement> parseStoryElementQueue = spiderContext.getParseStoryQueue();
            ParseStoryElement parseStoryElement = null;
            while (!done) {
                // 取出队首元素，如果队列为空，则阻塞
                parseStoryElement = parseStoryElementQueue.take();
                try {
                    synchronized (this) {
                        if (parseStoryElement.isEnd()) {
                            System.out.println(Thread.currentThread().getName() + " Stop.");
                            return;
                        }

                        List<String> list = Arrays.asList(new String[] { null, null, null, null, null });
                        list.set(0, parseStoryElement.getHtmlPage().getWebResponse().getRequestSettings().getUrl()
                                .toString());
                        parse(parseStoryElement, 1, list);
                        parseStoryElement.getHtmlPage().getWebClient().closeAllWindows();
                    }
                } catch (Exception e) {
                    System.out.println(parseStoryElement.getHtmlPage().getWebResponse().getRequestSettings().getUrl());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(ParseStoryElement parseStoryElement, int current, List<String> list) throws InterruptedException {
        System.out
                .println("StoryURL=" + parseStoryElement.getHtmlPage().getWebResponse().getRequestSettings().getUrl());
        if (parseStoryElement.isDetail()) {
            parseDetail(parseStoryElement);
        } else {
            parseInfo(parseStoryElement, current, list);
        }
    }

    public void parseInfo(ParseStoryElement parseStoryElement, int current, List<String> list)
            throws InterruptedException {
        BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();
        SpiderStoryTemplate template = spiderContext.getSpiderStoryTemplate();
        SpiderStoryJob spiderStoryJob = spiderContext.getSpiderStoryJob();
        HtmlPage htmlPage = parseStoryElement.getHtmlPage();
        StoryInfo storyInfo = new StoryInfo();
        storyInfo.setId(spiderStoryJob.getStoryId());
        storyInfo
                .setCrawlUrl(parseStoryElement.getHtmlPage().getWebResponse().getRequestSettings().getUrl().toString());

        // 处理明细
        List<HtmlAnchor> detailAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath(template.getDetailXPath());
        if (detailAnchorList != null && detailAnchorList.size() > 0) {
            String href = null;
            for (HtmlAnchor htmlAnchor : detailAnchorList) {
                href = htmlAnchor.getAttribute("href");
                if (href != null) {
                    if (URLUtil.getHost(href) != null) {
                        // 校验有站内连接
                        if (URLUtil.compile(URLUtil.getHost(href), URLUtil.getHost(spiderStoryJob.getUrl()))) {
                            urlQueue.put(new CrawlElement(href, CrawlType.StoryDetail));
                        }
                    } else if (href.startsWith("/")) {
                        urlQueue.put(new CrawlElement(SpiderConfigs.HTTP_PROTOCOL
                                + URLUtil.getHost(spiderStoryJob.getUrl()) + href, CrawlType.StoryDetail));
                    } else {
                        
                        if(htmlPage.getWebResponse().getRequestSettings().getUrl().toString().endsWith("/")){
                            urlQueue.put(new CrawlElement(htmlPage.getWebResponse().getRequestSettings().getUrl() 
                                    + href, CrawlType.StoryDetail));
                        }else{
                            urlQueue.put(new CrawlElement(htmlPage.getWebResponse().getRequestSettings().getUrl() + "/"
                                    + href, CrawlType.StoryDetail));
                        }
                    }
                }
            }
        }

        if (current == 1) {// 第一页
            if (spiderStoryJob.getStoryId() == null) {// 第一次抓取数据，需要抓取文章基本信息
                List<HtmlElement> titleElementList = ((List<HtmlElement>) htmlPage.getByXPath(template.getTitleXPath()));// 处理标题
                if (titleElementList != null && titleElementList.size() > 0) {
                    HtmlElement titleHtmlElement = titleElementList.get(0);
                    storyInfo.setTitle(titleHtmlElement.asText());
                } else {
                    storyInfo.setTitle(htmlPage.getTitleText());
                }

                List<HtmlElement> authorElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                        .getAuthorXPath()));// 处理作者
                if (authorElementList != null && authorElementList.size() > 0) {
                    HtmlElement authorElement = authorElementList.get(0);
                    storyInfo.setAuthor(authorElement.asText().replace("作    者：", ""));
                }

                List<HtmlElement> outlineElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                        .getOutlineXPath()));// 处理概要
                if (outlineElementList != null && outlineElementList.size() > 0) {
                    HtmlElement outlineElement = outlineElementList.get(0);
                    storyInfo.setOutline(outlineElement.asText());
                }

                List<HtmlElement> picPathElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                        .getPicPathXPath()));// 处理图片
                if (picPathElementList != null && picPathElementList.size() > 0) {
                    HtmlElement picPathElement = picPathElementList.get(0);

                    String src = picPathElement.getAttribute("src");
                    String url = URLUtil.getHost(spiderStoryJob.getUrl()) + src;
                    Image image = ImageService.downloadImgByUrl(url);
                    if(image != null){
                        storyInfo.setPicPath(image.getUrl());
                        System.out.println("图片下载失败。");
                    }
                }
            }
        }

        // 取下一页数据
        if (StringUtils.isNotEmpty(template.getNextXPath())) {
            List<HtmlAnchor> nextAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath(template.getNextXPath());
            if (nextAnchorList != null && nextAnchorList.size() > 0) {
                try {
                    HtmlPage nextHtmlPage = nextAnchorList.get(0).click();
                    int index = current % SIZE;
                    if (current % (SIZE * SIZE) == 0) {
                        nextHtmlPage.getWebClient().closeAllWindows();
                        urlQueue.put(new CrawlElement(nextHtmlPage.getWebResponse().getRequestSettings().getUrl()
                                .toString(), CrawlType.List));
                        return;
                    }
                    // 发现进入循环，则立即跳出去
                    if (list.contains(nextHtmlPage.getWebResponse().getRequestSettings().getUrl().toString())) {
                        return;
                    } else {
                        list.set(index, nextHtmlPage.getWebResponse().getRequestSettings().getUrl().toString());
                    }
                    System.out.println("NextURL=" + nextHtmlPage.getWebResponse().getRequestSettings().getUrl());
                    parse(new ParseStoryElement(false, nextHtmlPage), current + 1, list);
                } catch (IOException e) {
                    System.out.println(htmlPage.getWebResponse().getRequestSettings().getUrl());
                    e.printStackTrace();
                }
            } else {
                urlQueue.put(new CrawlElement(CrawlType.End));
            }
        } else {
            urlQueue.put(new CrawlElement(CrawlType.End));
        }

        System.out.println(storyInfo);
    }

    public void parseDetail(ParseStoryElement parseStoryElement) {
        StoryDetail storyDetail = new StoryDetail();
        HtmlPage htmlPage = parseStoryElement.getHtmlPage();
        SpiderStoryTemplate template = spiderContext.getSpiderStoryTemplate();
        SpiderStoryJob spiderStoryJob = spiderContext.getSpiderStoryJob();

        List<HtmlElement> titleElementList = ((List<HtmlElement>) htmlPage.getByXPath(template.getDetailTitleXPath()));// 处理标题
        if (titleElementList != null && titleElementList.size() > 0) {
            HtmlElement titleElement = titleElementList.get(0);
            String title = titleElement.asText();
            if (title != null) {
                if (title.lastIndexOf("》") != -1) {
                    title = title.substring(title.lastIndexOf("》") + 1);
                }
                storyDetail.setTitle(title);
            }
        }

        List<HtmlElement> contentElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                .getDetailContentXPath()));// 处理内容
        if (contentElementList != null && contentElementList.size() > 0) {
            HtmlElement contentElement = contentElementList.get(0);
                                                                                                                                                                                                     
            String xml = contentElement.asXml().replaceAll("【\\?书\\?阅☆屋\\?www.shuyuewu.com】", "【多多儿#www.dodoer.com】");
            xml = xml.replaceAll("m.shuyuewu.com", "m.dodoer.com");
            xml = com.summer.whm.spider.utils.StringUtils.replaceAllIgnoreCase(xml, "www.shuyuewu.com", "www.dodoer.com");
            storyDetail.setContent(xml);
            String contentTxt = contentElement.asText();
            if (contentTxt != null) {
                contentTxt = contentTxt.replaceAll("【\\?书\\?阅☆屋\\?www.shuyuewu.com】", "【多多儿#www.dodoer.com】");
                contentTxt = contentTxt.replaceAll("m.shuyuewu.com", "m.dodoer.com");
                contentTxt = com.summer.whm.spider.utils.StringUtils.replaceAllIgnoreCase(contentTxt, "www.shuyuewu.com", "www.dodoer.com");
                storyDetail.setContentTxt(contentTxt);
            }
        }
        storyDetail.setStoryId(spiderStoryJob.getStoryId());
        
        System.out.println(storyDetail);
    }

    public void writeFile(CrawlStore crawlStore, DetailTemplate template) {
        String basePath = "D:/stackoverflow/";
        File file = new File(basePath + System.currentTimeMillis() + ".html");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String str : crawlStore.getContentList()) {
            pw.println(str);
            pw.println("======================");
        }
        pw.flush();
        pw.close();
    }
}
