package com.summer.whm.spider.parse;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.entiry.spider.DetailTemplate;

public class ParseDetailElement {

    private boolean isEnd = false;
    
    private HtmlPage htmlPage;

    private DetailTemplate detailTemplate;
    
    public ParseDetailElement(boolean isEnd) {
        super();
        this.isEnd = isEnd;
    }

    public ParseDetailElement(HtmlPage htmlPage) {
        super();
        this.htmlPage = htmlPage;
    }

    public ParseDetailElement(HtmlPage htmlPage, DetailTemplate detailTemplate) {
        super();
        this.htmlPage = htmlPage;
        this.detailTemplate = detailTemplate;
    }
    
    public ParseDetailElement(boolean isEnd, HtmlPage htmlPage, DetailTemplate detailTemplate) {
        super();
        this.isEnd = isEnd;
        this.htmlPage = htmlPage;
        this.detailTemplate = detailTemplate;
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

    public DetailTemplate getDetailTemplate() {
        return detailTemplate;
    }

    public void setDetailTemplate(DetailTemplate detailTemplate) {
        this.detailTemplate = detailTemplate;
    }
}
