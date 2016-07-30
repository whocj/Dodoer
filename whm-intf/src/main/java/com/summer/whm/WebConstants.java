package com.summer.whm;

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
   * 匿名用户
   */
  public static final String ANONYMOUS_USER = "Anonymous";
  public static final int ANONYMOUS_USER_ID = 3;
  /**
   * 以/backend开头,非/login结尾
   */
  public static final String BACKEND_URL = "^/backend.*(?<!/login|/rcode)$";

  public static String INDEX_PATH = "/opt/whm/index/post";
  
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
