package com.summer.whm.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.entiry.BaseEntity;
import com.summer.whm.entiry.user.User;
import com.summer.whm.web.common.utils.CheckMobile;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.common.ForwardManager;

public class BaseController {
    public static Logger log = LoggerFactory.getLogger(BaseController.class);

    public final static String SUCCESS_STR = "Success";

    public final static String FAILURE_STR = "Failure";
    
    public final static String MESSAGE_STR = "message";

    public final static String ERROR = "error_404.ftl";
    
    public static final String ERROR_ROLE = "error_role.ftl";

    public static final String LOGIN_URL = "redirect:/login/index.html";

    public static final Integer TOP_10 = 10;
    
    /**
     * 检查访问方式是否为移动端
     * 
     * @Title: check
     * @Date : 2014-7-7 下午03:55:19
     * @param request
     * @throws IOException
     */
    public boolean checkMobile(HttpServletRequest request, HttpServletResponse response){
        boolean isFromMobile = false;

        HttpSession session = request.getSession();
        // 检查是否已经记录访问方式（移动端或pc端）
        if (null == session.getAttribute(WebConstants.JSESSION_UA)) {
            try {
                StringBuffer url = request.getRequestURL();
                //System.out.println("RequestURL:" + url);
                boolean flag = CheckMobile.checkUrl(url.toString());
                if(!flag){
                    // 获取ua，用来判断是否为移动端访问
                    String userAgent = request.getHeader("USER-AGENT").toLowerCase();
                    if (null == userAgent) {
                        userAgent = "";
                    }
                    isFromMobile = CheckMobile.check(userAgent);
                }
                // 判断是否为移动端访问
                if (flag || isFromMobile) {
//                    System.out.println("移动端访问");
                    isFromMobile = true;
                    session.setAttribute(WebConstants.JSESSION_UA, WebConstants.UA_TYPE_MOBILE);
                } else {
//                    System.out.println("pc端访问");
                    session.setAttribute(WebConstants.JSESSION_UA, WebConstants.UA_TYPE_PC);
                }
            } catch (Exception e) {
            }
        } else {
            isFromMobile = session.getAttribute(WebConstants.JSESSION_UA).equals(WebConstants.UA_TYPE_MOBILE);
        }

        return isFromMobile;
    }

    public User getSessionUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(WebConstants.SESSION_USER);
        return user;
    }

    /**
     * AJAX输出，返回null
     * 
     * @param content
     * @param type
     * @return
     */
    public String ajax(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error("IOException:", e);
        }
        return null;
    }

    /**
     * AJAX输出HTML，返回null
     * 
     * @param html
     * @return
     */
    public String ajaxHtml(HttpServletResponse response, String html) {
        return ajax(response, html, "text/html");
    }

    /**
     * AJAX输出XML，返回null
     * 
     * @param xml
     * @return
     */
    public String ajaxXml(HttpServletResponse response, String xml) {
        return ajax(response, xml, "text/xml");
    }

    /**
     * 根据字符串输出JSON，返回null
     * 
     * @param jsonString
     * @return
     */
    public String ajaxJson(HttpServletResponse response, String jsonString) {
        return ajax(response, jsonString, "text/html");
    }

    /**
     * 功能描述: <br>
     * 封装用户操作日志
     */
    public void buildExtCreatorPara(HttpServletRequest request, BaseEntity baseEntiry) {
        if (baseEntiry != null) {
            baseEntiry.setCreateTime(new Date());
            baseEntiry.setLastUpdate(new Date());
            User user = getSessionUser(request);
            if (user != null) {
                baseEntiry.setCreator(user.getUsername());
            }

        }
    }

    public int getCp(HttpServletRequest request) {
        String cp = request.getParameter("cp");
        if (StringUtils.isEmpty(cp)) {
            cp = "1";
        }
        try {
            return Integer.parseInt(cp);
        } catch (Exception e) {
            return 1;
        }

    }

    public void setSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(WebConstants.SESSION_USER, user);
    }
    
    public String getForward(HttpServletRequest request, HttpServletResponse response,  String forward){
        boolean isMobile = this.checkMobile(request, response);
        return ForwardManager.getForward(forward, isMobile);
    }
}
