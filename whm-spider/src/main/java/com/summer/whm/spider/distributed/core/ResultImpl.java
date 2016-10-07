package com.summer.whm.spider.distributed.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */

public class ResultImpl implements Result, Serializable {

    private static final long serialVersionUID = -6925924956850004727L;

    private String id;

    private int index = 0;

    private Object result;

    private Throwable exception;

    private Map<String, String> attachments = new HashMap<String, String>();

    public ResultImpl(String id) {
        this.id = id;
    }

    public ResultImpl() {
    }

    public ResultImpl(String id, Object result) {
        this.id = id;
        this.result = result;
    }

    public ResultImpl(String id, Throwable exception) {
        this.id = id;
        this.exception = exception;
    }

    public ResultImpl(String id, Object result, Throwable exception) {
        this.id = id;
        this.result = result;
        this.exception = exception;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object recreate() throws Throwable {
        if (exception != null) {
            throw exception;
        }
        return result;
    }

    public Object getValue() {
        return result;
    }

    public void setValue(Object value) {
        this.result = value;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable e) {
        this.exception = e;
    }

    public boolean hasException() {
        return exception != null;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public String getAttachment(String key) {
        return attachments.get(key);
    }

    public String getAttachment(String key, String defaultValue) {
        String result = attachments.get(key);
        if (result == null || result.length() == 0) {
            result = defaultValue;
        }
        return result;
    }

    public void setAttachments(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            attachments.putAll(map);
        }
    }

    public void setAttachment(String key, String value) {
        attachments.put(key, value);
    }

    @Override
    public String toString() {
        return "Result [result=" + result + ", exception=" + exception + "]";
    }

    public void setId(String id) {
        this.id = id;
    }
}