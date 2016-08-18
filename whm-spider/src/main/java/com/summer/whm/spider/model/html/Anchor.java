package com.summer.whm.spider.model.html;

public class Anchor {
    private String url;

    private String txt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Anchor(String url, String txt) {
        super();
        this.url = url;
        this.txt = txt;
    }

    public Anchor() {
        super();
    }

    @Override
    public String toString() {
        return "Anchor [url=" + url + ", txt=" + txt + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        Anchor other = (Anchor) obj;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}
