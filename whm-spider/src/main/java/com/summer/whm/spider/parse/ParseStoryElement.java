package com.summer.whm.spider.parse;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;

public class ParseStoryElement {
    
    private boolean detail = true;
    
    private boolean seed = false;
    
    private boolean end = false;

    private HtmlPage htmlPage;
    
    private StoryDetail storyDetail;
    
    private SpiderStoryJob spiderStoryJob;
    
    private StoryInfo storyInfo;
    
    public StoryInfo getStoryInfo() {
        return storyInfo;
    }

    public void setStoryInfo(StoryInfo storyInfo) {
        this.storyInfo = storyInfo;
    }

    public ParseStoryElement() {
        super();
    }

    public ParseStoryElement(boolean end) {
        super();
        this.end = end;
    }
    
    public ParseStoryElement(HtmlPage htmlPage) {
        super();
        this.htmlPage = htmlPage;
    }
    
    public ParseStoryElement(boolean detail, HtmlPage htmlPage) {
        super();
        this.htmlPage = htmlPage;
        this.detail = detail;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public boolean isSeed() {
        return seed;
    }

    public void setSeed(boolean seed) {
        this.seed = seed;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }

    public void setHtmlPage(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }

    public StoryDetail getStoryDetail() {
        return storyDetail;
    }

    public void setStoryDetail(StoryDetail storyDetail) {
        this.storyDetail = storyDetail;
    }

    public SpiderStoryJob getSpiderStoryJob() {
        return spiderStoryJob;
    }

    public void setSpiderStoryJob(SpiderStoryJob spiderStoryJob) {
        this.spiderStoryJob = spiderStoryJob;
    }
}
