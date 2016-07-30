package com.summer.whm.service.search.keyword.associate;



/**
 * 该对象是TfAssociateWord的简化版 UI层不需要id、state、isHlt
 * 
 * @author Administrator
 * 
 */
public class AssociateWord implements Comparable<AssociateWord>{
    public static final int MANUAL_SIZE = 1000000;
    private String keyword;
    private String keywordView;
    private int sort;
    private int count;
    private String core;
    //去重使用
    private String uniqueKeyword;

    /**
     * 是否人为工,1是，0否
     */
    private String isManual;
    
    public AssociateWord(String keyword, int sort, int count, String core) {
        super();
        this.keyword = keyword;
        this.sort = sort;
        this.count = count;
        this.core = core;
        isManual = sort > MANUAL_SIZE ? "1" : "0";
    }

    public AssociateWord() {
        super();
    }
    
    public AssociateWord(String keyword) {
        super();
        this.keyword = keyword;
    }

    public AssociateWord(String keyword, String keywordView, String uniqueKeyword, int sort) {
        super();
        this.keyword = keyword;
        this.keywordView = keywordView;
        this.uniqueKeyword = uniqueKeyword;
        isManual = sort > MANUAL_SIZE ? "1" : "0";
    }
    
    public AssociateWord(String keyword, int sort) {
        super();
        this.keyword = keyword;
        this.sort = sort;
        isManual = sort > MANUAL_SIZE ? "1" : "0";
    }

    public String getIsManual() {
        return isManual;
    }

    public void setIsManual(String isManual) {
        this.isManual = isManual;
    }

    public String getUniqueKeyword() {
        return uniqueKeyword;
    }

    public void setUniqueKeyword(String uniqueKeyword) {
        this.uniqueKeyword = uniqueKeyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
        isManual = sort > MANUAL_SIZE ? "1" : "0";
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((core == null) ? 0 : core.hashCode());
        result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AssociateWord other = (AssociateWord) obj;
        if (core == null) {
            if (other.core != null)
                return false;
        } else if (!core.equals(other.core))
            return false;
        if (keyword == null) {
            if (other.keyword != null)
                return false;
        } else if (!keyword.equals(other.keyword))
            return false;
        return true;
    }

    public int compareTo(AssociateWord o) {
        AssociateWord other = o;
        return other.getSort() - this.sort;
    }

    public String getKeywordView() {
        return keywordView;
    }

    public void setKeywordView(String keywordView) {
        this.keywordView = keywordView;
    }
}
