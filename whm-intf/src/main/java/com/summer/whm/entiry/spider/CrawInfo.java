package com.summer.whm.entiry.spider;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class CrawInfo extends BaseEntity {

    private String url;
    
    private Integer crawTemplateId;
    
    private Integer crawLogId;
    
    private Date createTime;

    public CrawInfo() {
    }

    public CrawInfo(String url) {
        super();
        this.url = url;
        this.createTime = new Date();
    }
    
    public CrawInfo(String url, Integer crawTemplateId, Integer crawLogId) {
        super();
        this.url = url;
        this.crawTemplateId = crawTemplateId;
        this.crawLogId = crawLogId;
        this.createTime = new Date();
    }

    public Integer getCrawTemplateId() {
        return crawTemplateId;
    }

    public void setCrawTemplateId(Integer crawTemplateId) {
        this.crawTemplateId = crawTemplateId;
    }

    public Integer getCrawLogId() {
        return crawLogId;
    }

    public void setCrawLogId(Integer crawLogId) {
        this.crawLogId = crawLogId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getId();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof CrawInfo)) {
            return false;
        }

        return ((CrawInfo) o).getId() == this.getId();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder(64).append('[').append(this.getUrl()).append(", id=").append(this.getId()).toString();
    }
}
