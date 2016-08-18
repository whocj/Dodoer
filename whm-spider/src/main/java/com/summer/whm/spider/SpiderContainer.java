package com.summer.whm.spider;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.summer.whm.spider.crawl.CrawlElement;
import com.summer.whm.spider.parse.ParseDetailElement;
import com.summer.whm.spider.parse.ParseListELement;
import com.summer.whm.spider.parse.ParseStoryElement;

public class SpiderContainer {

    public BlockingQueue<CrawlElement> urlQueue = new ArrayBlockingQueue<CrawlElement>(SpiderConfigs.URL_QUEUE_SIZE);
    public BlockingQueue<ParseDetailElement> parseDetailQueue = new ArrayBlockingQueue<ParseDetailElement>(
            SpiderConfigs.URL_QUEUE_SIZE);
    public BlockingQueue<ParseListELement> parseListQueue = new ArrayBlockingQueue<ParseListELement>(
            SpiderConfigs.URL_QUEUE_SIZE);
    public BlockingQueue<ParseStoryElement> parseStoryQueue = new ArrayBlockingQueue<ParseStoryElement>(
            SpiderConfigs.URL_QUEUE_SIZE);

    public BlockingQueue<CrawlElement> getUrlQueue() {
        return urlQueue;
    }

    public void setUrlQueue(BlockingQueue<CrawlElement> urlQueue) {
        this.urlQueue = urlQueue;
    }

    public BlockingQueue<ParseDetailElement> getParseDetailQueue() {
        return parseDetailQueue;
    }

    public void setParseDetailQueue(BlockingQueue<ParseDetailElement> parseDetailQueue) {
        this.parseDetailQueue = parseDetailQueue;
    }

    public BlockingQueue<ParseListELement> getParseListQueue() {
        return parseListQueue;
    }

    public void setParseListQueue(BlockingQueue<ParseListELement> parseListQueue) {
        this.parseListQueue = parseListQueue;
    }

    public BlockingQueue<ParseStoryElement> getParseStoryQueue() {
        return parseStoryQueue;
    }

    public void setParseStoryQueue(BlockingQueue<ParseStoryElement> parseStoryQueue) {
        this.parseStoryQueue = parseStoryQueue;
    }

}
