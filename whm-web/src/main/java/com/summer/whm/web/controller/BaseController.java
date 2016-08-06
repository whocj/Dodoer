package com.summer.whm.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.WebConstants;
import com.summer.whm.entiry.BaseEntity;
import com.summer.whm.entiry.user.User;

public class BaseController {
    public static Logger log = LoggerFactory.getLogger(BaseController.class);

    public final static String SUCCESS_STR = "Success";

    public final static String FAILURE_STR = "Failure";

    public final static String ERROR = "error_404.ftl";

    public static final String LOGIN_URL = "redirect:/login/index.html";

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
}
