package com.summer.whm.spider.utils.img;

public class Image {
    private String name;

    private String allName;

    private String endName;
    
    private String url;
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllName() {
        return allName;
    }

    public void setAllName(String allName) {
        this.allName = allName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    @Override
    public String toString() {
        return "Image [name=" + name + ", allName=" + allName + ", endName=" + endName + ", url=" + url + "]";
    }

}
