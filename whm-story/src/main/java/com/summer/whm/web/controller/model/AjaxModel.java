package com.summer.whm.web.controller.model;

public class AjaxModel {
    private String status;
    
    private String msg;

    private String exception;
    
    public AjaxModel() {
        super();
    }

    public AjaxModel(String status) {
        super();
        this.status = status;
    }

    public AjaxModel(String status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
    
}
