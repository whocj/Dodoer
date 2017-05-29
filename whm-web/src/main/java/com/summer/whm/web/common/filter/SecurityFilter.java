package com.summer.whm.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.summer.whm.entiry.user.User;
import com.summer.whm.plugin.ApplicationContextUtil;
import com.summer.whm.web.common.session.CookieRemberManager;
import com.summer.whm.web.common.utils.WebConstants;

public class SecurityFilter implements Filter {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否需要验证码
     */
    private boolean isIdentCode = false;

    ApplicationContext atx = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        atx = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        ApplicationContextUtil.setApplicationContext(atx);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        try {
            if (!(StringUtils.contains(uri, "login") || StringUtils.contains(uri, "logout"))) {
                checkSession(req, res);
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            gotoLogin(req, res);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    public void gotoLogin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, res);
        res.setHeader("Cache-Control", "no-store");
        res.setDateHeader("Expires", 0);
        res.setHeader("Pragma", "no-cache");
    }

    public User checkSession(HttpServletRequest req, HttpServletResponse res) {
        Object obj = req.getSession().getAttribute(WebConstants.SESSION_USER);
        User user = null;
        if (obj == null) {
            user = CookieRemberManager.extractValidRememberMeCookieUser(req, res);
            if (user != null) {
                req.getSession().setAttribute(WebConstants.SESSION_USER, user);
            }
        } else {
            user = (User) obj;
        }

        return user;
    }

    /**
     * 
     * 功能描述: <br>
     * 判断用户名登录次数，如果到达阀值，则需要输入验证吗
     */
    public Integer errorRemaining(String username) {
        return 0;
    }

    public boolean isIdentCode() {
        return isIdentCode;
    }

    public void setIdentCode(boolean isIdentCode) {
        this.isIdentCode = isIdentCode;
    }

}
