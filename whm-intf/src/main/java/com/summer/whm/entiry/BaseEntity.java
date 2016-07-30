package com.summer.whm.entiry;

import java.util.Date;

public class BaseEntity {
    private Integer id;
    private Date createTime;
    private String creator;
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getLastUpdate() {
        return lastUpdate == null ? new Date() : lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isNew() {
        return this.id == null || id == 0;
    }
}
