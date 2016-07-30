package com.summer.whm.entiry.user;

import com.summer.whm.entiry.BaseEntity;

public class UserGroup extends BaseEntity {

    private Integer siteId;

    /**
     * 头衔
     */
    private String name;

    /**
     * 组类别
     */
    private Integer groupType;

    /**
     * 图片
     */
    private String groupImg;

    /**
     * 升级积分
     */
    private Integer groupPoint;

    /**
     * 是否默认组
     */
    private Integer isDefault;

    /**
     * 评分
     */
    private Integer gradeNum;

    /**
     * 用户组道具包容量
     */
    private Integer magicPacketSize;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public Integer getGroupPoint() {
        return groupPoint;
    }

    public void setGroupPoint(Integer groupPoint) {
        this.groupPoint = groupPoint;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(Integer gradeNum) {
        this.gradeNum = gradeNum;
    }

    public Integer getMagicPacketSize() {
        return magicPacketSize;
    }

    public void setMagicPacketSize(Integer magicPacketSize) {
        this.magicPacketSize = magicPacketSize;
    }

    @Override
    public String toString() {
        return "UserGroup [siteId=" + siteId + ", name=" + name + ", groupType=" + groupType + ", groupImg=" + groupImg
                + ", groupPoint=" + groupPoint + ", isDefault=" + isDefault + ", gradeNum=" + gradeNum
                + ", magicPacketSize=" + magicPacketSize + "]";
    }

}
