package com.summer.whm.spider;


public class SpiderConfigs {
    public final static int URL_QUEUE_SIZE = 1000000;// 阻塞队列大小
    public final static int CRAWL_THREAD_SIZE = 1;// 抓取线程个数
    public final static int PARSE_THREAD_SIZE = 1;// 解析线程个数
    public final static int WEBCLIENT_COUNT = 100;// 解析线程个数
    
    public final static String HTTP_PROTOCOL = "http://";
    
    public final static String SPIDER = "Spider";
    
    public final static int SPIDER_USERID = 5;
    
    public final static Integer DEFAULT_POST_STATUS = 1;
    
    public static int HTTP_CLINET_KEEP_ALIVE = 1;
    public static final int DEFALUT_KEEP_ALIVE = 0;//默认重用策略
    public static final int SPECIFIED_KEEP_ALIVE = 1;//自定义重用策略
    public static final int NEVER_KEEP_ALIVE = 2;//从不重用策略
    
    public static int ENABLE_FAIL_LOG = 1;
    
    // SUNING_DOMAIN调用路径及httpclient相关配置
    public static String SUNING_DOMAIN = "b2cpre.cnsuning.com";
    public static int SUNING_DOMAIN_CONNECT_TIMEOUT = 2000;
    public static int SUNING_DOMAIN_SO_TIMEOUT = 2000;
    public static int SUNING_DOMAIN_POOL_TIMEOUT = 1000;
    public static int SUNING_DOMAIN_MAX_ROUTE_CONNECTIONS = 128;
    public static int SUNING_DOMAIN_MAX_TOTAL_CONNECTIONS = 128;
    
    public static int HTTP_CONNECT_TIMEOUT = 500;
    public static int HTTP_SO_TIMEOUT = 500;
    public static int HTTP_POOL_TIMEOUT = 1000;
    public static int HTTP_MAX_ROUTE_CONNECTIONS = 1024;
    public static int HTTP_MAX_TOTAL_CONNECTIONS = 1024;
    
    public static boolean REINIT_HTTP_POOL_SHUTDOWN_ENABLED =true;
    
    //当自定义重用策略时，空闲链接存活时间 默认300 单位秒
    public static int SPECIFIED_KEEP_ALIIVE_DUR = 300;

}
