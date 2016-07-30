package com.summer.whm.entiry.category;

import java.util.List;

import com.summer.whm.entiry.BaseEntity;
import com.summer.whm.entiry.forum.Forum;

public class Category extends BaseEntity {
    private Integer siteId;
    
    private Integer parentId;

    /**
     * 访问路径
     */
    private String path;

    /**
     * 标题
     */
    private String title;

    /**
     * 排列顺序
     */
    private Integer priority;

    /**
     * 板块列数
     */
    private String forumCols;

    /**
     * 版主
     */
    private String moderators;
    
    //是否显示，0隐藏，1显示
    private int view;

    private List<CategoryUser> categoryUserList;
    
    private List<Forum> forumList;
    
    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getForumCols() {
        return forumCols;
    }

    public void setForumCols(String forumCols) {
        this.forumCols = forumCols;
    }

    public String getModerators() {
        return moderators;
    }

    public void setModerators(String moderators) {
        this.moderators = moderators;
    }
    
    public List<CategoryUser> getCategoryUserList() {
        return categoryUserList;
    }

    public void setCategoryUserList(List<CategoryUser> categoryUserList) {
        this.categoryUserList = categoryUserList;
    }
    
    public List<Forum> getForumList() {
        return forumList;
    }

    public void setForumList(List<Forum> forumList) {
        this.forumList = forumList;
    }

    @Override
    public String toString() {
        return "Category [siteId=" + siteId + ", path=" + path + ", title=" + title + ", priority=" + priority
                + ", forumCols=" + forumCols + ", moderators=" + moderators + "]";
    }
    
}
