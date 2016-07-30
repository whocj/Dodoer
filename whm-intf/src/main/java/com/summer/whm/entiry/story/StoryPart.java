package com.summer.whm.entiry.story;

import java.util.List;

import com.summer.whm.entiry.BaseEntity;

/**
 * 小说段落
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StoryPart extends BaseEntity {

    private Integer storyId;
    
    private String title;
    
    private String outline;

    private List<StoryDetail> storyDetailList;
    
    public List<StoryDetail> getStoryDetailList() {
        return storyDetailList;
    }

    public void setStoryDetailList(List<StoryDetail> storyDetailList) {
        this.storyDetailList = storyDetailList;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }
    
}
