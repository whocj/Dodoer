package com.summer.whm.web.common.utils;

import java.util.HashMap;
import java.util.Map;

public class WebConstants{
  private WebConstants(){
  }

  /**
   * 站点标题前缀
   */
  public static final String PRE_TITLE_KEY = "ptitle";

  public static String APPLICATION_PATH;
  public static final String PREFIX = "/WEB-INF/jsp/";
  private static String DOMAIN;

  public static final String SESSION_USER = "sessionUser";
  public static final String COOKIE_KEY = "com.summer.whm.cookie";
  
  public static final String MESSAGE_TXT = "message";
  
  /**
   * 分页，每页页面条数
   */
  public static final int PAGE_SIZE = 30;
  
  /**
   * TOP
   */
  public static final int TOP_SIZE_12 = 12;
  
  /**
   * TOP
   */
  public static final int TOP_SIZE_100 = 100;
  
  /**
   * 匿名用户
   */
  public static final String ANONYMOUS_USER = "Anonymous";
  public static final int ANONYMOUS_USER_ID = 3;
  /**
   * 以/backend开头,非/login结尾
   */
  public static final String BACKEND_URL = "^/backend.*(?<!/login|/rcode)$";

  public static String INDEX_PATH = "/opt/whm/index/post";
  
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
  
  public static final String JSESSION_UA = "ua";

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
  
  public static final Integer SITE_ID_STORY = 4;
  
  public static final Integer SITE_ID_ASK = 2;

  public static final Integer SITE_ID_BBS = 1;
  
  public static final Integer SITE_ID_BLOG = 3;

  public final static String CURRENT_MENU_CODE = "CURRENT_MENU_CODE";
  public static final Integer MENU_DEFAULT_CODE = 0;
  public static final Integer MENU_ASK_CODE = 1;
  public static final Integer MENU_QUESTION_CODE = 2;
  public static final Integer MENU_BBS_CODE = 3;
  public static final Integer MENU_BLOG_CODE = 4;
  public static final Integer MENU_MORE_CODE = 5;

  public static final String FRIST_DO_MAIN = "www.dodoer.com";
  
  public static final String MOBILE_DO_MAIN = "m.dodoer.com";
  
  public static String PUBLIC_AUTH_SOURCE_QQ = "QQ";

  public static String PUBLIC_AUTH_SOURCE_WEBO = "WEBO";

  public static String PUBLIC_AUTH_SOURCE_SINA = "SINA";

  public static final String PUBLIC_AUTH_SOURCE_QQ_KEY = "openId";
  public static final String PUBLIC_AUTH_SOURCE_SINA_KEY = "uid";
  public static final String PUBLIC_AUTH_SOURCE_WEBO_KEY = "weboOpenId";
  
  public static final String RESP_STATUS_SUCC = "200";
  
  //传入参数不对
  public static final String RESP_STATUS_ERR_PARM = "401";
  
  //权限不够
  public static final String RESP_STATUS_PRO = "403";
  
  public static final String RESP_STATUS_ERR = "500";
  
  public static final String UA_TYPE_MOBILE = "mobile";
  
  public static final String UA_TYPE_PC = "pc";
  
  static {
      SKIN_MAP.put("blue", "blue");
      SKIN_MAP.put("green", "green");
      SKIN_MAP.put("red", "red");
      SKIN_MAP.put("default", "default");
      
      BLOG_POST_STATUS_MAP.put("0", "草稿箱");
      BLOG_POST_STATUS_MAP.put("1", "博客主题");
      BLOG_POST_STATUS_MAP.put("99", "回收站");
  }
  
  public static String getDomain(){
    return DOMAIN;
  }

  /**
   * 设置当前站点域名，因为{@link getDomain()}
   * 为ThreadLocal方式实现获取domain,这样不在servlet线程中执行时会拿到null值
   * 
   * @param domain
   */
  public static void setDomain(String domain){
    DOMAIN = domain;
  }

  public static String getDomainLink(String path){
    return getDomain() + path;
  }

}
