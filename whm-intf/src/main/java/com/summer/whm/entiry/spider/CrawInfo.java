package com.summer.whm.entiry.spider;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class CrawInfo extends BaseEntity {

    private String url;
    
    private Integer crawTemplateId;
    
    private Integer crawLogId;
    
    private String type;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CrawInfo() {
    }

    public CrawInfo(String url) {
        super();
        this.url = url;
        this.setCreateTime(new Date());
    }
    
    public CrawInfo(String url, Integer crawTemplateId, Integer crawLogId, String type) {
        super();
        this.url = url;
        this.crawTemplateId = crawTemplateId;
        this.crawLogId = crawLogId;
        this.setCreateTime(new Date());
        this.type = type;
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
