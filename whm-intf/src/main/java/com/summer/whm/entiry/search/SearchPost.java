package com.summer.whm.entiry.search;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class SearchPost extends BaseEntity{
    
    //创建索引
    public static String POST_MODEL_INDEX = "Index";
    
    //创建索引
    public static String POST_MODEL_UPDATE = "Update";
    
    //删除索引
    public static String POST_MODEL_DELETE = "Delete";
    
    private String docId;
    
    //question,topic,blog
    private String type;
    
    private String title;
    
    private String content;
    
    private String author;
    
    private String createTime;
    
    private String tags;
    
    private String site;
    
    private String url;
    
    //0初始化，1本站更新索引，2百度更新索引
    private int isIndex = 0;
    
    /**
     * Index,Delete,
     */
    private String model;
    
    private static final SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM-dd HH:ss");

    public SearchPost() {
        super();
    }

    public SearchPost(String docId, String type, String title, String content, String author, String createTime,
            String tags) {
        super();
        this.docId = docId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createTime = createTime;
        this.tags = tags;
        this.isIndex = 0;
        this.model = POST_MODEL_INDEX;
    }

    public SearchPost(String docId, String type, String title, String content, String author, Date createTime,
            String tags) {
        super();
        this.docId = docId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.author = author;
        this.model = POST_MODEL_INDEX;
        this.isIndex = 0;
        this.createTime = dsf.format(createTime);
        this.tags = tags;
    }

    public SearchPost(String docId, String type, String title, String content, String author,
            String tags) {
        super();
        this.docId = docId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.author = author;
        this.model = POST_MODEL_INDEX;
        this.isIndex = 0;
        this.createTime = dsf.format(new Date());
        this.tags = tags;
    }
    
    public SearchPost(String docId, String type, String model) {
        super();
        this.docId = docId;
        this.type = type;
        this.model = model;
        this.isIndex = 0;
        this.createTime = dsf.format(new Date());
    }
    
    public String getAuthor() {
        return author == null ? "anonymous" : author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getCreateTimeStr() {
        
        if(createTime == null){
            this.createTime = dsf.format(new Date());
        }
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocuemntId(){
        return (this.getType() + "@" + this.getDocId()).toLowerCase();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(int isIndex) {
        this.isIndex = isIndex;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "SearchPost [docId=" + docId + ", type=" + type + ", title=" + title + ", content=" + content + ", author="
                + author + ", createTime=" + createTime + ", tags=" + tags + "]";
    }
    
}
