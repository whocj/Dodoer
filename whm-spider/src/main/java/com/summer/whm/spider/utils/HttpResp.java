package com.summer.whm.spider.utils;

import java.io.InputStream;

import org.apache.http.Header;

public class HttpResp {
    
    private String code;
    
    private InputStream is;
    private Header contentType;
    private Header contentEncoding;

    private long contentLength;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public Header getContentType() {
        return contentType;
    }

    public void setContentType(Header contentType) {
        this.contentType = contentType;
    }

    public Header getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(Header contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    @Override
    public String toString() {
        return "HttpResp [code=" + code + ", is=" + is + ", contentType=" + contentType + ", contentEncoding="
                + contentEncoding + ", contentLength=" + contentLength + "]";
    }

    
}
