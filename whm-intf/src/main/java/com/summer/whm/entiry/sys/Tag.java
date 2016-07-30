package com.summer.whm.entiry.sys;

import com.summer.whm.entiry.BaseEntity;

public class Tag extends BaseEntity {

    private String name;
    private Integer siteId;

    /**
     * 粗体
     */
    private String styleBold;
    
    /**
     * 斜体
     */
    private String styleItalic;
    
    /**
     * 颜色
     */
    private String styleColor;
    
    private String isSystem;
    
    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getStyleBold() {
        return styleBold;
    }

    public void setStyleBold(String styleBold) {
        this.styleBold = styleBold;
    }

    public String getStyleItalic() {
        return styleItalic;
    }

    public void setStyleItalic(String styleItalic) {
        this.styleItalic = styleItalic;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

}
