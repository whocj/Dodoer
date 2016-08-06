package com.summer.whm.web.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.summer.whm.common.security.Base64Codec;

/**
 * Cookie 辅助类
 * 
 * @author tom
 * 
 */
public class CookieUtils {
    /**
     * 每页条数cookie名称
     */
    public static final String COOKIE_PAGE_SIZE = "_cookie_page_size";
    /**
     * 默认每页条数
     */
    public static final int DEFAULT_SIZE = 20;
    /**
     * 最大每页条数
     */
    public static final int MAX_SIZE = 200;

    private static Method setHttpOnlyMethod;
    private static final String PATH = "/";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String domain;

    static {
        setHttpOnlyMethod = ReflectionUtils.findMethod(Cookie.class, "setHttpOnly", boolean.class);
    }

    public CookieUtils(final HttpServletRequest request, final HttpServletResponse response) {
        this(request, response, null);
    }

    public CookieUtils(final HttpServletRequest request, final HttpServletResponse response, final String domain) {
        this.request = request;
        this.response = response;
        this.domain = domain;
    }

    /**
     * 获取cookie值，默认base64解码
     * 
     * @param name
     * @return
     */
    public String getCookie(String name) {
        return getCookie(name, true);
    }

    public String getCookie(String name, boolean decode) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                String value = cookie.getValue();
                if(value != null){
                    return decode ? decode(value) : value;
                }
            }
        }

        return null;
    }

    public void setCookie(String name, String value) {
        setCookie(name, value, false);
    }

    public void setCookie(String name, String value, boolean httpOnly) {
        setCookie(name, value, PATH, httpOnly);
    }

    public void setCookie(String name, String value, String path, boolean httpOnly) {
        setCookie(name, value, path, httpOnly, -1);
    }

    public void setCookie(String name, String value, boolean httpOnly, int expiry) {
        setCookie(name, value, PATH, httpOnly, expiry, true);
    }

    public void setCookie(String name, String value, String path, boolean httpOnly, int expiry) {
        setCookie(name, value, path, httpOnly, expiry, true);
    }

    public void setCookie(String name, String value, String path, boolean httpOnly, int expiry, boolean encode) {
        Cookie cookie = new Cookie(name, encode ? encode(value) : value);
        cookie.setPath(path);
        cookie.setMaxAge(expiry);
        /* 在javaee6以上,tomcat7以上支持Cookie.setHttpOnly方法,这里向下兼容 */
        if (setHttpOnlyMethod != null && httpOnly) {
            ReflectionUtils.invokeMethod(setHttpOnlyMethod, cookie, Boolean.TRUE);
        }

        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    public void removeCokie(String name) {
        setCookie(name, null, false, 0);
    }

    private String encode(String value) {
        return value == null ? null : new String(Base64Codec.encode(value));
    }

    private String decode(String value) {
        try {
            return value == null ? null : new String(Base64Codec.decode(value), Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 获得cookie的每页条数
     * 
     * 使用_cookie_page_size作为cookie name
     * 
     * @param request HttpServletRequest
     * @return default:20 max:200
     */
    public static int getPageSize(HttpServletRequest request) {
        Assert.notNull(request);
        Cookie cookie = getCookie(request, COOKIE_PAGE_SIZE);
        int count = 0;
        if (cookie != null) {
            try {
                count = Integer.parseInt(cookie.getValue());
            } catch (Exception e) {
            }
        }
        if (count <= 0) {
            count = DEFAULT_SIZE;
        } else if (count > MAX_SIZE) {
            count = MAX_SIZE;
        }
        return count;
    }

    /**
     * 获得cookie
     * 
     * @param request HttpServletRequest
     * @param name cookie name
     * @return if exist return cookie, else return null.
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request);
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * 根据部署路径，将cookie保存在根目录。
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param expiry
     * @return
     */
    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
            Integer expiry, String domain) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        String ctx = request.getContextPath();
        cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
        response.addCookie(cookie);
        return cookie;
    }

    public static Cookie addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
            Integer expiry, String domain, String path) {
        Cookie cookie = new Cookie(name, value);
        if (expiry != null) {
            cookie.setMaxAge(expiry);
        }
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        response.addCookie(cookie);
        return cookie;
    }

    /**
     * 取消cookie
     * 
     * @param response
     * @param name
     * @param domain
     */
    public static void cancleCookie(HttpServletResponse response, String name, String domain) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        if (!StringUtils.isBlank(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }
}
