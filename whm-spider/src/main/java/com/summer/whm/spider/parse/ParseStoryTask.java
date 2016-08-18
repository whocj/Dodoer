package com.summer.whm.spider.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
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
import com.summer.whm.spider.exception.ParseException;
import com.summer.whm.spider.service.SpiderStoryJobService;
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

    private SpiderStoryJobService spiderStoryJobService;

    public ParseStoryTask(SpiderContext spiderContext, StoryInfoService storyInfoService,
            StoryPartService storyPartService, StoryDetailService storyDetailService,
            SpiderStoryJobService spiderStoryJobService) {
        this.spiderContext = spiderContext;
        this.storyInfoService = storyInfoService;
        this.storyPartService = storyPartService;
        this.storyDetailService = storyDetailService;
        this.spiderStoryJobService = spiderStoryJobService;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            BlockingQueue<ParseStoryElement> parseStoryElementQueue = spiderContext.getParseStoryQueue();
            BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();
            ParseStoryElement parseStoryElement = null;
            while (!done) {
                // 取出队首元素，如果队列为空，则阻塞
                parseStoryElement = parseStoryElementQueue.take();
                try {
                    synchronized (this) {
                        if (parseStoryElement.isEnd()) {
                            System.out.println(Thread.currentThread().getName() + " Stop.");

                            SpiderStoryJob spiderStoryJob = spiderContext.getSpiderStoryJob();

                            SpiderStoryJob tempJob = new SpiderStoryJob();
                            tempJob.setId(spiderStoryJob.getId());
                            tempJob.setSpiderStatus(SpiderConfigs.STATUS_STOP);
                            spiderStoryJobService.update(spiderStoryJob);
                            System.out.println("#结束处理小说Job" + spiderStoryJob.getTitle());
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
                    urlQueue.put(new CrawlElement(CrawlType.End));// 结束抓取数据
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(ParseStoryElement parseStoryElement, int current, List<String> list) throws InterruptedException,
            ParseException {
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
        storyInfo.setCategoryId(spiderStoryJob.getCategoryId());
        storyInfo.setCategoryName(spiderStoryJob.getCategoryName());

        if (current == 1) {// 第一页
            if (spiderStoryJob.getStoryId() == null) {// 第一次抓取数据，需要抓取文章基本信息
                List<HtmlElement> titleElementList = ((List<HtmlElement>) htmlPage.getByXPath(template.getTitleXPath()));// 处理标题
                if (titleElementList != null && titleElementList.size() > 0) {
                    HtmlElement titleHtmlElement = titleElementList.get(0);
                    storyInfo.setTitle(titleHtmlElement.asText());
                } else {
                    storyInfo.setTitle(htmlPage.getTitleText());
                }

                if (StringUtils.isEmpty(storyInfo.getTitle().trim())) {
                    storyInfo.setTitle("无题");
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
                    if (!url.startsWith("http")) {
                        url = SpiderConfigs.HTTP_PROTOCOL + url;
                    }
                    Image image = ImageService.downloadImgByUrl(url);
                    if (image != null) {
                        storyInfo.setPicPath(image.getUrl());
                    } else {
                        System.out.println("图片下载失败。");
                    }
                }
            } else {
                StoryInfo storyInfo2 = storyInfoService.loadById(spiderStoryJob.getStoryId() + "");
                if (storyInfo2.getPicPath() == null) {
                    List<HtmlElement> picPathElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                            .getPicPathXPath()));// 处理图片
                    if (picPathElementList != null && picPathElementList.size() > 0) {
                        HtmlElement picPathElement = picPathElementList.get(0);

                        String src = picPathElement.getAttribute("src");
                        String url = URLUtil.getHost(spiderStoryJob.getUrl()) + src;
                        if (!url.startsWith("http")) {
                            url = SpiderConfigs.HTTP_PROTOCOL + url;
                        }
                        Image image = ImageService.downloadImgByUrl(url);
                        if (image != null) {
                            storyInfo.setPicPath(image.getUrl());
                        } else {
                            System.out.println("图片下载失败。");
                        }
                    }
                }
                
            }
        }

        // 保存数据库
        if (storyInfo != null && storyInfo.isNew()) {
            storyInfo.setCreateTime(new Date());
            storyInfo.setCreator(SpiderConfigs.SPIDER);
            storyInfo.setStatus("2");
            // 保存小说基本信息
            storyInfoService.save(storyInfo);
            spiderStoryJob.setStoryId(storyInfo.getId());

            // 更新小说抓取任务数据
            SpiderStoryJob tempStoryJob = new SpiderStoryJob();
            tempStoryJob.setId(spiderStoryJob.getId());
            tempStoryJob.setStoryId(storyInfo.getId());
            spiderStoryJobService.update(tempStoryJob);
        } else {
            storyInfoService.save(storyInfo);
        }

        // 处理明细
        List<HtmlAnchor> detailAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath(template.getDetailXPath());
        if (detailAnchorList != null && detailAnchorList.size() > 0) {
            String href = null;
            String url = null;
            for (HtmlAnchor htmlAnchor : detailAnchorList) {
                href = htmlAnchor.getAttribute("href");
                if (href != null) {
                    if (URLUtil.getHost(href) != null) {
                        // 校验有站内连接
                        // if (URLUtil.compile(URLUtil.getHost(href), URLUtil.getHost(spiderStoryJob.getUrl()))) {
                        // urlQueue.put(new CrawlElement(href, CrawlType.StoryDetail));
                        // }
                        url = href;
                    } else if (href.startsWith("/")) {
//                        urlQueue.put(new CrawlElement(SpiderConfigs.HTTP_PROTOCOL
//                                + URLUtil.getHost(spiderStoryJob.getUrl()) + href, CrawlType.StoryDetail));
                        url = SpiderConfigs.HTTP_PROTOCOL + URLUtil.getHost(spiderStoryJob.getUrl()) + href;
                    } else {
                        if (htmlPage.getWebResponse().getRequestSettings().getUrl().toString().endsWith("/")) {
//                            urlQueue.put(new CrawlElement(htmlPage.getWebResponse().getRequestSettings().getUrl()
//                                    + href, CrawlType.StoryDetail));
                            url = htmlPage.getWebResponse().getRequestSettings().getUrl() + href;
                        } else {
//                            urlQueue.put(new CrawlElement(htmlPage.getWebResponse().getRequestSettings().getUrl() + "/"
//                                    + href, CrawlType.StoryDetail));
                            url = htmlPage.getWebResponse().getRequestSettings().getUrl() + "/" + href;
                        }
                    }
                    
                    StoryDetail storyDetail = new StoryDetail();
                    storyDetail.setTitle(htmlAnchor.getTextContent());
                    storyDetail.setStoryId(spiderStoryJob.getStoryId());
                    storyDetail.setCrawlUrl(url);
                    storyDetail.setCreateTime(new Date());
                    storyDetail.setCreator(SpiderConfigs.SPIDER);
                    storyDetail.setStatus("0");
                    storyDetail.setReadCount(0);
                    storyDetail.setReplyCount(0);
                    saveDB(storyDetail);
                    
                    CrawlElement crawlElement =  new CrawlElement(url, CrawlType.StoryDetail);
                    crawlElement.setObj(storyDetail);
                    urlQueue.put(crawlElement);
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
                } catch (Exception e) {
                    System.out.println(htmlPage.getWebResponse().getRequestSettings().getUrl());
                    e.printStackTrace();
                }
            } else {
                urlQueue.put(new CrawlElement(CrawlType.End));
            }
        } else {
            urlQueue.put(new CrawlElement(CrawlType.End));
        }
    }

    public void parseDetail(ParseStoryElement parseStoryElement) throws InterruptedException, ParseException {
        StoryDetail storyDetail = new StoryDetail();
        HtmlPage htmlPage = parseStoryElement.getHtmlPage();
        SpiderStoryTemplate template = spiderContext.getSpiderStoryTemplate();
        SpiderStoryJob spiderStoryJob = spiderContext.getSpiderStoryJob();

        List<HtmlElement> contentElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                .getDetailContentXPath()));// 处理内容
        if (contentElementList != null && contentElementList.size() > 0) {
            HtmlElement contentElement = contentElementList.get(0);
            String text = contentElement.asText();
            text = com.summer.whm.spider.utils.StringUtils.replaceAllBrackets(text, "");
            text = com.summer.whm.spider.utils.StringUtils.replaceAllIgnoreCase(text, "www.shuyuewu.com", "");
            text = com.summer.whm.spider.utils.StringUtils.replaceAllIgnoreCase(text, "m.shuyuewu.com", "");
            text = com.summer.whm.spider.utils.StringUtils.replaceAllIgnoreCase(text, "ShuYueWu.Com", "");
            text = com.summer.whm.spider.utils.StringUtils.replaceAllIgnoreCase(text, "ShuYueWu", "");
            text = text.replaceAll("←百度搜索→", "");
            text = text.replaceAll("百度搜索", "");
            text = text.replaceAll("一秒记住，为您提供精彩小说阅读。", "");
            text = text.replaceAll("一秒记住，為您提供精彩小说阅读。", "");
            text = text.replaceAll("手机用户请浏览阅读，更优质的阅读体验。", "");
            String[] strs = text.split("\n\r");
            // strs[0] = "";
            // strs[strs.length - 1] = "";
            String html = StringUtils.join(strs, "<br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;");
            storyDetail.setContent(html);
            // storyDetail.setContentTxt(StringUtils.join(strs, "\n\r"));
        }

        StoryInfo storyInfo = new StoryInfo();
        // 第一次处理出现异常，第二次操作
        if (parseStoryElement.getStoryDetail() != null) {
            storyDetail.setId(parseStoryElement.getStoryDetail().getId());
            storyInfo.setLastDetailTitle(parseStoryElement.getStoryDetail().getTitle());
        }
        saveDB(storyDetail);
        
        storyInfo.setId(spiderStoryJob.getStoryId());
        storyInfo.setLastDetailId(storyDetail.getId());
        storyInfo.setLastDetailTitle(storyDetail.getTitle());
        saveDB(storyInfo);

        if (StringUtils.isEmpty(storyDetail.getContent())) {
            if (parseStoryElement.getStoryDetail() == null) {
                BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();

                CrawlElement crawlElement = new CrawlElement(htmlPage.getWebResponse().getRequestSettings().getUrl()
                        .toString(), CrawlType.StoryDetail);
                crawlElement.setAgain(true);
                crawlElement.setObj(storyDetail);
                urlQueue.put(crawlElement);

                System.out.println("小说明细解析失败，" + htmlPage.getWebResponse().getRequestSettings().getUrl().toString());
            } else {
                throw new ParseException("小说明细解析失败，"
                        + htmlPage.getWebResponse().getRequestSettings().getUrl().toString());
            }
        }
    }

    public void saveDB(StoryDetail storyDetail) {
        storyDetailService.save(storyDetail);
    }

    public void saveDB(StoryInfo storyInfo) {
        storyInfoService.save(storyInfo);
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
