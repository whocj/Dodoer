package com.summer.whm.entiry.forum;

import com.summer.whm.entiry.BaseEntity;

public class ForumGroupOpt extends BaseEntity {

    private Integer forumId;

    private Integer groupId;

    private String groupName;

    private String type;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "ForumGroupOpt [forumId=" + forumId + ", groupId=" + groupId + ", groupName=" + groupName + ", type="
                + type + "]";
    }

}
