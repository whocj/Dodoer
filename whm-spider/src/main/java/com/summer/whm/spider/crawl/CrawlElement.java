package com.summer.whm.spider.crawl;

public class CrawlElement {
    private String url;

    private CrawlType type;

    public CrawlElement() {
        super();
    }
    
    public CrawlElement(CrawlType type) {
        super();
        this.type = type;
    }

    public CrawlElement(String url, CrawlType type) {
        super();
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CrawlType getType() {
        return type;
    }

    public void setType(CrawlType type) {
        this.type = type;
    }
}
