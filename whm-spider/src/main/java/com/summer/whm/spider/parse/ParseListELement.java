package com.summer.whm.spider.parse;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.entiry.spider.ListTemplate;

public class ParseListELement {
    
    public boolean isEnd = false;
    
    private HtmlPage htmlPage;

    private ListTemplate listTemplate;
    
    public ParseListELement(boolean isEnd) {
        super();
        this.isEnd = isEnd;
    }

    public ParseListELement(HtmlPage htmlPage) {
        super();
        this.htmlPage = htmlPage;
    }

    public ParseListELement(HtmlPage htmlPage, ListTemplate listTemplate) {
        super();
        this.htmlPage = htmlPage;
        this.listTemplate = listTemplate;
    }

    public ParseListELement(boolean isEnd, HtmlPage htmlPage, ListTemplate listTemplate) {
        super();
        this.isEnd = isEnd;
        this.htmlPage = htmlPage;
        this.listTemplate = listTemplate;
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

    public ListTemplate getListTemplate() {
        return listTemplate;
    }

    public void setListTemplate(ListTemplate listTemplate) {
        this.listTemplate = listTemplate;
    }
}
