package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class FriendLink extends BaseEntity {

    private Integer siteId;

    private Integer friendLinkCtgId;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站地址
     */
    private String domain;

    /**
     * 图标
     */
    private String logo;

    /**
     * 站长邮箱
     */
    private String email;

    /**
     * 描述
     */
    private String description;

    /**
     * 点击次数
     */
    private Integer views;

    /**
     * 是否显示
     */
    private String isEnabled;

    /**
     * 排列顺序
     */
    private Integer priority;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getFriendLinkCtgId() {
        return friendLinkCtgId;
    }

    public void setFriendLinkCtgId(Integer friendLinkCtgId) {
        this.friendLinkCtgId = friendLinkCtgId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "FriendLink [siteId=" + siteId + ", friendLinkCtgId=" + friendLinkCtgId + ", siteName=" + siteName
                + ", domain=" + domain + ", logo=" + logo + ", email=" + email + ", description=" + description
                + ", views=" + views + ", isEnabled=" + isEnabled + ", priority=" + priority + "]";
    }
    
}
