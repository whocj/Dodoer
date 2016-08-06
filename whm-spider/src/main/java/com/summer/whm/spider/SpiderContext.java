package com.summer.whm.spider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.summer.whm.entiry.spider.CrawLog;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.spider.crawl.CrawlElement;
import com.summer.whm.spider.filter.StringFilter;
import com.summer.whm.spider.parse.ParseDetailElement;
import com.summer.whm.spider.parse.ParseListELement;
import com.summer.whm.spider.parse.ParseStoryElement;

public class SpiderContext {
    public BlockingQueue<CrawlElement> urlQueue = new ArrayBlockingQueue<CrawlElement>(SpiderConfigs.URL_QUEUE_SIZE);
    public BlockingQueue<ParseDetailElement> parseDetailQueue = new ArrayBlockingQueue<ParseDetailElement>(
            SpiderConfigs.URL_QUEUE_SIZE);
    public BlockingQueue<ParseListELement> parseListQueue = new ArrayBlockingQueue<ParseListELement>(
            SpiderConfigs.URL_QUEUE_SIZE);
    public BlockingQueue<ParseStoryElement> parseStoryQueue = new ArrayBlockingQueue<ParseStoryElement>(
            SpiderConfigs.URL_QUEUE_SIZE);
    
    public static final int THREAD_STATUS_DEFAULT = 0;
    public static final int THREAD_STATUS_RUN = 1;
    public static final int THREAD_STATUS_PAUSE = 2;
    public static final int THREAD_STATUS_ERROR = 3;
    public static final int THREAD_STATUS_STOP = 99;
    
    private CrawLog crawLog;
    private CrawTemplate crawTemplate;
    private DetailTemplate detailTemplate;
    private ListTemplate listTemplate;
    
    private SpiderStoryTemplate spiderStoryTemplate;
    
    private SpiderStoryJob spiderStoryJob;
    
    private StringFilter stringFilter;
    
    private boolean isDone = false;
    
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public BlockingQueue<CrawlElement> getUrlQueue() {
        return urlQueue;
    }

    public BlockingQueue<ParseDetailElement> getParseDetailQueue() {
        return parseDetailQueue;
    }

    public BlockingQueue<ParseListELement> getParseListQueue() {
        return parseListQueue;
    }

    public DetailTemplate getDetailTemplate() {
        return detailTemplate;
    }

    public void setDetailTemplate(DetailTemplate detailTemplate) {
        this.detailTemplate = detailTemplate;
    }

    public ListTemplate getListTemplate() {
        return listTemplate;
    }

    public void setListTemplate(ListTemplate listTemplate) {
        this.listTemplate = listTemplate;
    }

    public CrawLog getCrawLog() {
        return crawLog;
    }

    public void setCrawLog(CrawLog crawLog) {
        this.crawLog = crawLog;
    }

    public CrawTemplate getCrawTemplate() {
        return crawTemplate;
    }
    
    public void setCrawTemplate(CrawTemplate crawTemplate) {
        this.crawTemplate = crawTemplate;
    }

    public StringFilter getStringFilter() {
        return stringFilter;
    }

    public void setStringFilter(StringFilter stringFilter) {
        this.stringFilter = stringFilter;
    }

    public SpiderStoryTemplate getSpiderStoryTemplate() {
        return spiderStoryTemplate;
    }

    public SpiderStoryJob getSpiderStoryJob() {
        return spiderStoryJob;
    }

    public void setSpiderStoryTemplate(SpiderStoryTemplate spiderStoryTemplate) {
        this.spiderStoryTemplate = spiderStoryTemplate;
    }

    public void setSpiderStoryJob(SpiderStoryJob spiderStoryJob) {
        this.spiderStoryJob = spiderStoryJob;
    }

    public BlockingQueue<ParseStoryElement> getParseStoryQueue() {
        return parseStoryQueue;
    }
}
