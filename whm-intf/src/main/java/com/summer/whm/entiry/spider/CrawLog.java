package com.summer.whm.entiry.spider;

import java.util.Date;

import com.summer.whm.entiry.BaseEntity;

public class CrawLog extends BaseEntity {

    private Integer crawTemplateId;
    private Date beginTime;
    private Date endTime;
    private String status;
    private String log;

    public Integer getCrawTemplateId() {
        return crawTemplateId;
    }

    public void setCrawTemplateId(Integer crawTemplateId) {
        this.crawTemplateId = crawTemplateId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
