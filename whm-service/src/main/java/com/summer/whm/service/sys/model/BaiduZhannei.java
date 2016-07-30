package com.summer.whm.service.sys.model;

public class BaiduZhannei {
    
    private String loc;
    private String lastmod;
    private String changefreq;
    private String priority;
    private String title;
    private String content;
    private String tag;
    private String pubTime;

    public BaiduZhannei() {
        super();
    }

    public BaiduZhannei(String loc, String lastmod, String changefreq, String priority, String title, String tag,
            String pubTime) {
        super();
        this.loc = loc;
        this.lastmod = lastmod;
        this.changefreq = changefreq;
        this.priority = priority;
        this.title = title;
        this.tag = tag;
        this.pubTime = pubTime;
    }

    public BaiduZhannei(String loc, String lastmod, String title, String content, String tag) {
        super();
        this.loc = loc;
        this.lastmod = lastmod;
        this.changefreq = "always";
        this.priority = "0.7";
        this.title = title;
        this.content = content;
        this.tag = tag;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLastmod() {
        return lastmod;
    }

    public void setLastmod(String lastmod) {
        this.lastmod = lastmod;
    }

    public String getChangefreq() {
        return changefreq;
    }

    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
}
