package com.summer.whm.spider.store;

import java.util.ArrayList;
import java.util.List;

public class CrawlStore {
    private String url;
    private String title;
    private List<String> contentList = new ArrayList<String>();
    private List<String> contentTextList =  new ArrayList<String>();
    private List<String> commentsList = new ArrayList<String>();
    private List<String> commentsTextList = new ArrayList<String>();
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void addContentList(List<String> contentList) {
        this.contentList.addAll(contentList);
    }

    public List<String> getCommentsList() {
        return commentsList;
    }

    public void addCommentsList(List<String> commentsList) {
        this.commentsList.addAll(commentsList);
    }

    public void addContent(String content) {
        contentList.add(content);
    }

    public void addComments(String comments) {
        this.commentsList.add(comments);
    }

    public List<String> getContentTextList() {
        return contentTextList;
    }

    public void addContentTextList(List<String> contentTextList) {
        this.contentTextList.addAll(contentTextList);
    }
    
    public List<String> getCommentsTextList() {
        return commentsTextList;
    }

    public void addCommentsTextList(List<String> commentsTextList) {
        this.commentsTextList.addAll(commentsTextList);
    }

    @Override
    public String toString() {
        return "CrawlStore [url=" + url + ", title=" + title + ", contentList=" + contentList + ", commentsList="
                + commentsList + "]";
    }
}
