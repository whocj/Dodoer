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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.summer.whm.plugin.ApplicationContextUtil;
import com.summer.whm.web.common.utils.WebConstants;

public class SecurityFilter implements Filter {

    ApplicationContext atx = null;
    
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否需要验证码
     */
    private boolean isIdentCode = false;

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
            if (!checkSession(req)) {
                if (!(StringUtils.contains(uri, "login") || StringUtils.contains(uri, "logout"))) {
                    gotoLogin(req, res);
                    return;
                }
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

    public boolean checkSession(HttpServletRequest req) {
        Object obj = req.getSession().getAttribute(WebConstants.SESSION_USER);
        if (obj == null) {
            return false;
        }
        return true;
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
