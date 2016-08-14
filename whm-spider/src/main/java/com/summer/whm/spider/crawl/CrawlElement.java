package com.summer.whm.spider.crawl;

public class CrawlElement {
    private String url;

    private CrawlType type;
    
    private Object obj;
    
    private boolean again = false;
    
    public boolean isAgain() {
        return again;
    }

    public void setAgain(boolean again) {
        this.again = again;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

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
