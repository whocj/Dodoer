package com.summer.whm.spider.parse;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.entiry.story.StoryDetail;

public class ParseStoryElement {
    
    private boolean isDetail = true;
    
    private boolean isEnd = false;

    private HtmlPage htmlPage;
    
    private StoryDetail storyDetail;
    
    public ParseStoryElement() {
        super();
    }

    public ParseStoryElement(boolean isEnd) {
        super();
        this.isEnd = isEnd;
    }
    
    public ParseStoryElement(HtmlPage htmlPage) {
        super();
        this.htmlPage = htmlPage;
    }
    
    public ParseStoryElement(boolean isDetail, HtmlPage htmlPage) {
        super();
        this.htmlPage = htmlPage;
        this.isDetail = isDetail;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean isDetail) {
        this.isDetail = isDetail;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
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
}
