package com.summer.whm.web.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * web常量
 * 
 * @author tom
 * 
 */
public class Constants {
    /**
     * 路径分隔符
     */
    public static final String SPT = "/";
    /**
     * 索引页
     */
    public static final String INDEX = "index";
    /**
     * 默认模板
     */
    public static final String DEFAULT = "default";
    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";
    /**
     * 提示信息
     */
    public static final String MESSAGE = "message";
    /**
     * cookie中的JSESSIONID名称
     */
    public static final String JSESSION_COOKIE = "JSESSIONID";
    /**
     * url中的jsessionid名称
     */
    public static final String JSESSION_URL = "jsessionid";

    /**
     * HTTP POST请求
     */
    public static final String POST = "POST";
    /**
     * HTTP GET请求
     */
    public static final String GET = "GET";

    public static final String COOKIE_CONTEXT_ID = "c_id";
    public static final String COOKIE_USER_NAME = "un";
    public static final String COOKIE_USER_ID = "ui";

    public static final Map<String, String> SKIN_MAP = new HashMap<String, String>();

    public static final Map<String, String> BLOG_POST_STATUS_MAP = new HashMap<String, String>();
    
    public static final Integer SITE_ID_ASK = 2;

    public static final Integer SITE_ID_BBS = 1;
    
    public static final Integer SITE_ID_BLOG = 3;
    
    public static final String IS_STR_TRUE = "1";

    public static final String IS_STR_FALSE = "0";

    public final static String CURRENT_MENU_CODE = "CURRENT_MENU_CODE";
    public static final Integer MENU_DEFAULT_CODE = 0;
    public static final Integer MENU_ASK_CODE = 1;
    public static final Integer MENU_QUESTION_CODE = 2;
    public static final Integer MENU_BBS_CODE = 3;
    public static final Integer MENU_BLOG_CODE = 4;
    public static final Integer MENU_MORE_CODE = 5;

    public static final String FRIST_DO_MAIN = "www.whohelpme.com";
    
    public static String PUBLIC_AUTH_SOURCE_QQ = "QQ";

    public static String PUBLIC_AUTH_SOURCE_WEBO = "WEBO";

    public static String PUBLIC_AUTH_SOURCE_SINA = "SINA";

    public static final String PUBLIC_AUTH_SOURCE_QQ_KEY = "openId";
    public static final String PUBLIC_AUTH_SOURCE_SINA_KEY = "uid";
    public static final String PUBLIC_AUTH_SOURCE_WEBO_KEY = "weboOpenId";
    
    static {
        SKIN_MAP.put("blue", "blue");
        SKIN_MAP.put("green", "green");
        SKIN_MAP.put("red", "red");
        SKIN_MAP.put("default", "default");
        
        BLOG_POST_STATUS_MAP.put("0", "草稿箱");
        BLOG_POST_STATUS_MAP.put("1", "博客主题");
        BLOG_POST_STATUS_MAP.put("99", "回收站");
    }
}
