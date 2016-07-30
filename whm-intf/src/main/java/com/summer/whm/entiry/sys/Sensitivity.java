package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class Sensitivity extends BaseEntity {

    private Integer siteId;

    /**
     * 敏感词
     */
    private String search;

    /**
     * 替换词
     */
    private String replacement;
    
    /**
     * 备注
     */
    private String remark;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Sensitivity [siteId=" + siteId + ", search=" + search + ", replacement=" + replacement + "]";
    }

    
    
}
