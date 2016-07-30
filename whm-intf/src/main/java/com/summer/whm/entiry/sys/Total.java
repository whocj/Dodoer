package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class Total extends BaseEntity {

    private Integer siteId;
    private Integer topicCount;
    private Integer postCount;
    private Integer userCount;
    private Integer onlineUserCount;
    private Integer sortDate;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getOnlineUserCount() {
        return onlineUserCount;
    }

    public void setOnlineUserCount(Integer onlineUserCount) {
        this.onlineUserCount = onlineUserCount;
    }

    public Integer getSortDate() {
        return sortDate;
    }

    public void setSortDate(Integer sortDate) {
        this.sortDate = sortDate;
    }
}
