/**
 * SUNING APPLIANCE CHAINS.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package com.summer.whm.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/error")
public class ErrorController {
    
    @RequestMapping("/error")
    public String doHttpError(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String userAgent = request.getHeader("User-Agent");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (uri == null) {
            uri = request.getRequestURI();
        }
        HashMap<String, Object> error = new HashMap<String, Object>();
        
        error.put("code", code);
        error.put("url", uri);
        error.put("userAgent", userAgent);
        Exception ex = (Exception) request.getAttribute("exception");
        if (ex != null) {
            error.put("class", ex.getClass().getName());
            error.put("message", ex.getMessage());
        }
        
        request.setAttribute("error", error);
        return "error.ftl";
    }

    @RequestMapping("/404")
    public String do404(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String userAgent = request.getHeader("User-Agent");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (uri == null) {
            uri = request.getRequestURI();
        }
        HashMap<String, Object> error = new HashMap<String, Object>();
        
        error.put("code", code);
        error.put("url", uri);
        error.put("userAgent", userAgent);
        Exception ex = (Exception) request.getAttribute("exception");
        if (ex != null) {
            error.put("class", ex.getClass().getName());
            error.put("message", ex.getMessage());
        }
        
        request.setAttribute("error", error);
        return "error_404.ftl";
    }
    
    @RequestMapping("/500")
    public String do500(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String userAgent = request.getHeader("User-Agent");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (uri == null) {
            uri = request.getRequestURI();
        }
        HashMap<String, Object> error = new HashMap<String, Object>();
        
        error.put("code", code);
        error.put("url", uri);
        error.put("userAgent", userAgent);
        Exception ex = (Exception) request.getAttribute("exception");
        if (ex != null) {
            error.put("class", ex.getClass().getName());
            error.put("message", ex.getMessage());
        }
        
        request.setAttribute("error", error);
        return "error_500.ftl";
    }
    
    @RequestMapping("/400")
    public String do400(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String userAgent = request.getHeader("User-Agent");
        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (uri == null) {
            uri = request.getRequestURI();
        }
        HashMap<String, Object> error = new HashMap<String, Object>();
        
        error.put("code", code);
        error.put("url", uri);
        error.put("userAgent", userAgent);
        Exception ex = (Exception) request.getAttribute("exception");
        if (ex != null) {
            error.put("class", ex.getClass().getName());
            error.put("message", ex.getMessage());
        }
        
        request.setAttribute("error", error);
        return "error_400.ftl";
    }
    
    @RequestMapping("/exception")
    public String doHttpException(HttpServletRequest request, HttpServletResponse response) {
        return doHttpError(request, response);
    }
}
