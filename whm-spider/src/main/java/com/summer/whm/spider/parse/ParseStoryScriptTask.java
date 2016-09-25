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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.common.utils.DateUtils;
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
import com.summer.whm.spider.service.SpiderSiteCategoryService;
import com.summer.whm.spider.service.SpiderStoryJobService;
import com.summer.whm.spider.store.CrawlStore;
import com.summer.whm.spider.utils.URLUtil;
import com.summer.whm.spider.utils.img.Image;
import com.summer.whm.spider.utils.img.ImageService;

public class ParseStoryScriptTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ParseStoryScriptTask.class);
    
    public static final int SIZE = 5;

    private SpiderContext spiderContext;

    private StoryInfoService storyInfoService;

    private StoryPartService storyPartService;

    private StoryDetailService storyDetailService;

    private SpiderStoryJobService spiderStoryJobService;

    private static final String HTML_PAGE_KEY = "htmlPage";
    
    private static final String SITE_CATEGORY_KEY = "siteCategory";
    
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
                            spiderStoryJobService.update(tempJob);
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
        if (parseStoryElement.isDetail()) {//小说明细
            parseDetail(parseStoryElement);
        } else if(parseStoryElement.isSeed()){//小说种子
            parseStoryJob(parseStoryElement);
        } else {//小说基本信息
            parseInfo(parseStoryElement, current, list);
        }
    }

    public void parseStoryJob(ParseStoryElement parseStoryElement)
            throws InterruptedException {
        HtmlPage htmlPage = parseStoryElement.getHtmlPage();
        
        SpiderStoryJob tempSpiderStoryJob = parseStoryElement.getSpiderStoryJob();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(HTML_PAGE_KEY, htmlPage);
        map.put(SITE_CATEGORY_KEY, SpiderSiteCategoryService.getCategoryMapByTemplateId(tempSpiderStoryJob.getTemplateId()));
        SpiderStoryTemplate template = spiderContext.getSpiderStoryTemplate();
        Integer categoryId = (Integer)ScriptManager.getInstance().run(template.getCategoryXPath(), map);//处理分类
        if(categoryId != null){
            tempSpiderStoryJob.setCategoryId(categoryId);
            saveDB(tempSpiderStoryJob);
        }else{
            LOG.info("目录分析失败，" + tempSpiderStoryJob.getTitle() + "=" + tempSpiderStoryJob.getUrl());
        }
    }
    
    public void parseInfo(ParseStoryElement parseStoryElement, int current, List<String> list)
            throws InterruptedException {
        BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();
        SpiderStoryTemplate template = spiderContext.getSpiderStoryTemplate();
        SpiderStoryJob spiderStoryJob = spiderContext.getSpiderStoryJob();
        HtmlPage htmlPage = parseStoryElement.getHtmlPage();
        StoryInfo storyInfo = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(HTML_PAGE_KEY, htmlPage);
        
        if (current == 1) {// 第一页
            if (spiderStoryJob.getStoryId() == null) {// 第一次抓取数据，需要抓取文章基本信息
                
                storyInfo = new StoryInfo();
                storyInfo.setId(spiderStoryJob.getStoryId());
                storyInfo
                        .setCrawlUrl(parseStoryElement.getHtmlPage().getWebResponse().getRequestSettings().getUrl().toString());
                storyInfo.setCategoryId(spiderStoryJob.getCategoryId());
                storyInfo.setCategoryName(spiderStoryJob.getCategoryName());
                
                String title = (String)ScriptManager.getInstance().run(template.getTitleXPath(), map);//处理标题
                if(title == null){
                    System.out.println("基本信息处理失败，" + storyInfo.getCrawlUrl());
                    return;
                }else{
                    storyInfo.setTitle(title);
                }
                
                String author = (String)ScriptManager.getInstance().run(template.getAuthorXPath(), map);//处理作者
                if(author == null){
                    author = "佚名";
                }
                storyInfo.setAuthor(author);
                
                boolean exists = storyInfoService.exists(title, author);
                if(exists){
                    //更新小说任务状态
                    SpiderStoryJob tempSpiderStoryJob = new SpiderStoryJob();
                    tempSpiderStoryJob.setId(spiderStoryJob.getId());
                    tempSpiderStoryJob.setStatus(SpiderConfigs.STORY_JOB_STATUS_REPEAT);
                    tempSpiderStoryJob.setSpiderStatus(SpiderConfigs.STATUS_STOP);
                    spiderStoryJobService.update(tempSpiderStoryJob);
                    
                    System.out.println("小说已经存在，" + title + "|" + author);
                    return;
                }
                
                String outline = (String)ScriptManager.getInstance().run(template.getOutlineXPath(), map);//处理概要
                if(outline != null){
                    storyInfo.setOutline(outline);
                }

                String status = (String)ScriptManager.getInstance().run(template.getStatusXPath(), map);//处理小说状态
                if(outline != null){
                    storyInfo.setStatus(status);
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
                storyInfo.setStatus(SpiderConfigs.STORY_STATUS_ONLINE);//上架
                storyInfo.setLastUpdateDetail(new Date());
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
                    List<Anchor> anchorList = (List<Anchor>)anchorObj;
                    parseDetailAnchor(anchorList, parseStoryElement, storyInfo, 0);
                }

            } else {
                //多次处理，判断图片是否有值，如果为空则继续下载，否则退出
                storyInfo = storyInfoService.loadById(spiderStoryJob.getStoryId() + "");
                if (storyInfo.getPicPath() == null || "/RES/images/default_120_150.jpg".equals(storyInfo.getPicPath())) {
                    String picPath = (String)ScriptManager.getInstance().run(template.getPicPathXPath(), map);//处理图片
                    if(picPath != null){
                        Image image = ImageService.downloadImgByUrl(picPath);
                        if (image != null) {
                            StoryInfo tempStoryInfo = new StoryInfo();
                            tempStoryInfo.setId(storyInfo.getId());
                            tempStoryInfo.setPicPath(image.getUrl());
                            storyInfoService.save(tempStoryInfo);
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
                
                //二次抓取连更数据
                int count = storyDetailService.queryCountByStoryId(spiderStoryJob.getStoryId());
                // 处理明细
                Object anchorObj = ScriptManager.getInstance().run(template.getDetailXPath(), map);//处理明细URL
                if(anchorObj != null){
                    List<Anchor> anchorList = (List<Anchor>)anchorObj;
                    parseDetailAnchor(anchorList, parseStoryElement, storyInfo, count);
                }

            }
            
            
            //处理推荐小说，小说种子处理
            if(StringUtils.isNotEmpty(template.getRecommendDetailXPath())){
                // 小说种子明细
                Object anchorObj = ScriptManager.getInstance().run(template.getRecommendDetailXPath(), map);//处理明细URL
                if(anchorObj != null){
                    List<Anchor> anchorList = (List<Anchor>)anchorObj;
                    String url = null;
                    SpiderStoryJob tempSpiderStoryJob = null;
                    if(StringUtils.isNotEmpty(template.getCategoryXPath())){
                        for(Anchor anchor : anchorList){
                            tempSpiderStoryJob = new SpiderStoryJob();
                            tempSpiderStoryJob.setCreateTime(new Date());
                            tempSpiderStoryJob.setUserId(SpiderConfigs.SPIDER_USERID);
                            tempSpiderStoryJob.setUsername(SpiderConfigs.SPIDER);
                            tempSpiderStoryJob.setStatus(SpiderConfigs.STORY_JOB_STATUS_INIT);
                            tempSpiderStoryJob.setTitle(anchor.getTxt());
                            url = buildURL(anchor.getUrl(), spiderStoryJob, htmlPage);
                            
                            tempSpiderStoryJob.setUrl(url);
                            tempSpiderStoryJob.setTemplateId(spiderStoryJob.getTemplateId());
                            tempSpiderStoryJob.setCreator(SpiderConfigs.SPIDER);
                            CrawlElement crawlElement =  new CrawlElement(url, CrawlType.StoryJob);
                            crawlElement.setObj(tempSpiderStoryJob);
                            crawlElement.setAgain(true);
                            urlQueue.put(crawlElement);
                        }
                    }else{
                        for(Anchor anchor : anchorList){
                            url = buildURL(anchor.getUrl(), spiderStoryJob, htmlPage);
                            tempSpiderStoryJob = new SpiderStoryJob();
                            tempSpiderStoryJob.setCategoryId(spiderStoryJob.getCategoryId());
                            tempSpiderStoryJob.setCreateTime(new Date());
                            tempSpiderStoryJob.setUserId(SpiderConfigs.SPIDER_USERID);
                            tempSpiderStoryJob.setUsername(SpiderConfigs.SPIDER);
                            tempSpiderStoryJob.setStatus(SpiderConfigs.STORY_JOB_STATUS_INIT);
                            tempSpiderStoryJob.setTitle(anchor.getTxt());
                            tempSpiderStoryJob.setUrl(url);
                            tempSpiderStoryJob.setTemplateId(spiderStoryJob.getTemplateId());
                            tempSpiderStoryJob.setCreator(SpiderConfigs.SPIDER);

                            saveDB(tempSpiderStoryJob);
                        }
                    }
                }
            }
        }else{
            //二次抓取连更数据
            int count = storyDetailService.queryCountByStoryId(spiderStoryJob.getStoryId());
            storyInfo = parseStoryElement.getStoryInfo();
            // 处理明细
            Object anchorObj = ScriptManager.getInstance().run(template.getDetailXPath(), map);//处理明细URL
            if(anchorObj != null){
                List<Anchor> anchorList = (List<Anchor>)anchorObj;
                parseDetailAnchor(anchorList, parseStoryElement, storyInfo, count);
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
                    ParseStoryElement nextParseStoryElement = new ParseStoryElement(false, nextHtmlPage);
                    nextParseStoryElement.setStoryInfo(storyInfo);
                    parse(nextParseStoryElement, current + 1, list);
                } catch (Exception e) {
                    System.out.println(htmlPage.getWebResponse().getRequestSettings().getUrl());
                    LOG.error("解析小说一下页出错", e);
                }
            } else {
                urlQueue.put(new CrawlElement(CrawlType.End));
            }
        } else {
            urlQueue.put(new CrawlElement(CrawlType.End));
        }
    }

    public String buildURL(String href, SpiderStoryJob spiderStoryJob, HtmlPage htmlPage){
        String url = null;
        if (URLUtil.getHost(href) != null) {
            // 校验有站内连接
            url = href;
        } else if (href.startsWith("/")) {
            url = SpiderConfigs.HTTP_PROTOCOL + URLUtil.getHost(spiderStoryJob.getUrl()) + href;
        } else {
            if (htmlPage.getWebResponse().getRequestSettings().getUrl().toString().endsWith("/")) {
                url = htmlPage.getWebResponse().getRequestSettings().getUrl() + href;
            } else {
                url = htmlPage.getWebResponse().getRequestSettings().getUrl() + "";
                url = url.substring(0, url.lastIndexOf("/") + 1);
                url = url + href;
            }
        }

        return url;
    }
    
    //分析小说明细入口,count已入库条数
    private void parseDetailAnchor(List<Anchor> anchorList, ParseStoryElement parseStoryElement, StoryInfo storyInfo, int count) throws InterruptedException{
        SpiderStoryJob spiderStoryJob = spiderContext.getSpiderStoryJob();
        HtmlPage htmlPage = parseStoryElement.getHtmlPage();
        BlockingQueue<CrawlElement> urlQueue = spiderContext.getUrlQueue();
        String url = null;
        
        if(anchorList.size() > count){
            int begin = count - 1;
            if(begin < 0){
                begin = 0;
            }
            
            for(int i = begin; i < anchorList.size(); i++){
                Anchor anchor = anchorList.get(i);
                
                url = buildURL(anchor.getUrl(), spiderStoryJob, htmlPage);
                
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

        //计算章节最后一次更新时间，如果超过30天未更新章节，则可确认小说已完结，或已弃更
        if(DateUtils.compare2Day(storyInfo.getLastUpdateDetail(), new Date()) > SpiderConfigs.MAX_DAY_FINISH){
            SpiderStoryJob tempSpiderStoryJob = new SpiderStoryJob();
            tempSpiderStoryJob.setId(spiderStoryJob.getId());
            tempSpiderStoryJob.setStatus(SpiderConfigs.STORY_JOB_STATUS_FINISH);
            tempSpiderStoryJob.setSpiderStatus(SpiderConfigs.STATUS_STOP);
            spiderStoryJobService.update(tempSpiderStoryJob);
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
        storyDetail.setStoryId(parseStoryElement.getStoryDetail().getStoryId());
        
        if(storyDetail.getId() == null || storyDetail.getStoryId() == null){
            throw new ParseException("解析小说章节明细出错，Id或StoryId为空。");
        }
        
        saveDB(storyDetail);
        
        StoryInfo storyInfo = new StoryInfo();
        //更新最新章节
        storyInfo.setId(spiderStoryJob.getStoryId());
        storyInfo.setLastDetailId(storyDetail.getId());
        storyInfo.setLastDetailTitle(parseStoryElement.getStoryDetail().getTitle());
        storyInfo.setLastUpdateDetail(new Date());
        
        if(isStoryFinish(parseStoryElement.getStoryDetail())){
            storyInfo.setStatus(SpiderConfigs.STORY_STATUS_FINISH);
        }

        saveDB(storyInfo);
    }

    //判断小说只否已经结束
    public boolean isStoryFinish(StoryDetail storyDetail){
        if(storyDetail != null && storyDetail.getTitle() != null){
            String title = storyDetail.getTitle();
            if(title.indexOf("大结局") != -1 
                    || title.indexOf("完本感言") != -1){
                return true;
            }
        }

        return false;
    }
    
    public void saveDB(StoryDetail storyDetail) {
        storyDetailService.save(storyDetail);
    }

    public void saveDB(StoryInfo storyInfo) {
        storyInfoService.save(storyInfo);
    }

    public void saveDB(SpiderStoryJob spiderStoryJob){
        if(spiderStoryJobService.queryByUrl(spiderStoryJob.getUrl()) == null){
            spiderStoryJobService.insert(spiderStoryJob);
        }else{
           LOG.info(spiderStoryJob.getTitle()+ "="+ spiderStoryJob.getUrl() + "已存在");
        }
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
