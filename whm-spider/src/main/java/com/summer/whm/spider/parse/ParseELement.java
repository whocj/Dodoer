package com.summer.whm.spider.parse;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ParseELement<T> {
    private HtmlPage htmlPage;

    private T template;

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }

    public void setHtmlPage(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }

    public T getTemplate() {
        return template;
    }

    public void setTemplate(T template) {
        this.template = template;
    }
}
