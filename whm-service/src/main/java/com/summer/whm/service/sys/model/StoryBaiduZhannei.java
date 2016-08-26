package com.summer.whm.service.sys.model;

public class StoryBaiduZhannei {

    private String loc;
    private String lastmod;

    private String name;
    private String authorName;
    private String authorUrl;
    private String image;
    private String description;
    private String genre;
    private String wordCount;
    private String updateStatus;
    private String trialStatus = "免费";
    private String keywords;
    private String totalClick;
    private String newestChapterHeadline;
    private String newestChapterUrl;
    private String newestChapterDateModified;
    private String dateModified;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getTrialStatus() {
        return trialStatus;
    }

    public void setTrialStatus(String trialStatus) {
        this.trialStatus = trialStatus;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTotalClick() {
        return totalClick;
    }

    public void setTotalClick(String totalClick) {
        this.totalClick = totalClick;
    }

    public String getNewestChapterHeadline() {
        return newestChapterHeadline;
    }

    public void setNewestChapterHeadline(String newestChapterHeadline) {
        this.newestChapterHeadline = newestChapterHeadline;
    }

    public String getNewestChapterUrl() {
        return newestChapterUrl;
    }

    public void setNewestChapterUrl(String newestChapterUrl) {
        this.newestChapterUrl = newestChapterUrl;
    }

    public String getNewestChapterDateModified() {
        return newestChapterDateModified;
    }

    public void setNewestChapterDateModified(String newestChapterDateModified) {
        this.newestChapterDateModified = newestChapterDateModified;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public StoryBaiduZhannei() {
        super();
    }

    public StoryBaiduZhannei(String loc, String lastmod, String name, String authorName,
            String image, String description, String genre, String updateStatus, 
            String keywords, String totalClick, String newestChapterHeadline, String newestChapterUrl,
            String newestChapterDateModified) {
        super();
        this.loc = loc;
        this.lastmod = lastmod;
        this.name = name;
        this.authorName = authorName;
        this.image = image;
        this.description = description;
        this.genre = genre;
        this.updateStatus = updateStatus;
        this.keywords = keywords;
        this.totalClick = totalClick;
        this.newestChapterHeadline = newestChapterHeadline;
        this.newestChapterUrl = newestChapterUrl;
        this.newestChapterDateModified = newestChapterDateModified;
        this.dateModified = newestChapterDateModified;
    }
    
}
