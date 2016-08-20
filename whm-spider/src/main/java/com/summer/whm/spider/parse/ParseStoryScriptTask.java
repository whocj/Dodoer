package com.summer.whm.spider.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.summer.whm.spider.model.html.Anchor;
import com.summer.whm.spider.script.ScriptManager;
import com.summer.whm.spider.service.SpiderStoryJobService;
import com.summer.whm.spider.store.CrawlStore;
import com.summer.whm.spider.utils.URLUtil;
import com.summer.whm.spider.utils.img.Image;
import com.summer.whm.spider.utils.img.ImageService;

public class ParseStoryScriptTask implements Runnable {

    public static final int SIZE = 5;

    private SpiderContext spiderContext;

    private StoryInfoService storyInfoService;

    private StoryPartService storyPartService;

    private StoryDetailService storyDetailService;

    private SpiderStoryJobService spiderStoryJobService;

    private static final String HTML_PAGE_KEY = "htmlPage";
    
    public ParseStoryScriptTask(SpiderContext spiderContext, StoryInfoService storyInfoService,
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

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(HTML_PAGE_KEY, htmlPage);
        
        if (current == 1) {// 第一页
            if (spiderStoryJob.getStoryId() == null) {// 第一次抓取数据，需要抓取文章基本信息
                
                String title = (String)ScriptManager.getInstance().run(template.getTitleXPath(), map);//处理标题
                if(title == null){
                    System.out.println("基本信息处理失败，" + storyInfo.getCrawlUrl());
                    return;
                }else{
                    storyInfo.setTitle(title);
                }
                
                String author = (String)ScriptManager.getInstance().run(template.getAuthorXPath(), map);//处理作者
                if(author != null){
                    storyInfo.setAuthor(author);
                }
                
                String outline = (String)ScriptManager.getInstance().run(template.getOutlineXPath(), map);//处理概要
                if(outline != null){
                    storyInfo.setOutline(outline);
                }

                String picPath = (String)ScriptManager.getInstance().run(template.getPicPathXPath(), map);//处理图片
                if(picPath != null){
                    Image image = ImageService.downloadImgByUrl(picPath);
                    if (image != null) {
                        storyInfo.setPicPath(image.getUrl());
                    }else{
                        System.out.println("图片下载失败。");
                    }
                }

                // 保存数据库
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
                
                // 处理明细
                
                Object anchorObj = ScriptManager.getInstance().run(template.getDetailXPath(), map);//处理明细URL
                if(anchorObj != null){
                    String href = null;
                    String url = null;
                    
                    List<Anchor> anchorList = (List<Anchor>)anchorObj;
                    for(Anchor anchor : anchorList){
                        href = anchor.getUrl();
                        if (URLUtil.getHost(href) != null) {
                            // 校验有站内连接
                            url = href;
                        } else if (href.startsWith("/")) {
                            url = SpiderConfigs.HTTP_PROTOCOL + URLUtil.getHost(spiderStoryJob.getUrl()) + href;
                        } else {
                            if (htmlPage.getWebResponse().getRequestSettings().getUrl().toString().endsWith("/")) {
                                url = htmlPage.getWebResponse().getRequestSettings().getUrl() + href;
                            } else {
                                url = htmlPage.getWebResponse().getRequestSettings().getUrl() + "/" + href;
                            }
                        }
                        
                        StoryDetail storyDetail = new StoryDetail();
                        storyDetail.setTitle(anchor.getTxt());
                        storyDetail.setStoryId(storyInfo.getId());
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

            } else {
                //多次处理，判断图片是否有值，如果为空则继续下载，否则退出
                StoryInfo storyInfo2 = storyInfoService.loadById(spiderStoryJob.getStoryId() + "");
                if (storyInfo2.getPicPath() == null) {
                    String picPath = (String)ScriptManager.getInstance().run(template.getPicPathXPath(), map);//处理图片
                    if(picPath != null){
                        Image image = ImageService.downloadImgByUrl(picPath);
                        if (image != null) {
                            storyInfo.setPicPath(image.getUrl());
                            storyInfoService.save(storyInfo);
                        }else{
                            System.out.println("图片下载失败。");
                        }
                    }
                }

                //第一次抓取失败的任务，这再再次启动任务
                List<StoryDetail> storyDetailList = storyDetailService.queryByStoryIdAndContentIsNull(spiderStoryJob.getStoryId());
                if(storyDetailList != null && storyDetailList.size() > 0){
                    for(StoryDetail storyDetail : storyDetailList){
                        CrawlElement crawlElement =  new CrawlElement(storyDetail.getCrawlUrl(), CrawlType.StoryDetail);
                        crawlElement.setObj(storyDetail);
                        crawlElement.setAgain(true);
                        urlQueue.put(crawlElement);
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

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(HTML_PAGE_KEY, htmlPage);

        String content = (String)ScriptManager.getInstance().run(template.getDetailContentXPath(), map);//处理图片
        storyDetail.setContent(content);

        //更新小说内容
        storyDetail.setId(parseStoryElement.getStoryDetail().getId());
        saveDB(storyDetail);

        StoryInfo storyInfo = new StoryInfo();
        //更新最新章节
        storyInfo.setId(spiderStoryJob.getStoryId());
        storyInfo.setLastDetailId(storyDetail.getId());
        storyInfo.setLastDetailTitle(parseStoryElement.getStoryDetail().getTitle());
        saveDB(storyInfo);
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
