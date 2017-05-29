package com.summer.whm.spider;


public class SpiderConfigs {
    public final static int URL_QUEUE_SIZE = 10000;// 阻塞队列大小
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
    
    public static int HTTP_CONNECT_TIMEOUT = 5000;
    public static int HTTP_SO_TIMEOUT = 5000;
    public static int HTTP_POOL_TIMEOUT = 10000;
    public static int HTTP_MAX_ROUTE_CONNECTIONS = 1024;
    public static int HTTP_MAX_TOTAL_CONNECTIONS = 1024;
    
    public static boolean REINIT_HTTP_POOL_SHUTDOWN_ENABLED =true;
    
    //当自定义重用策略时，空闲链接存活时间 默认300 单位秒
    public static int SPECIFIED_KEEP_ALIIVE_DUR = 300;
    
    public static String DOMAIN_TYPE_QUESTION = "question";
    
    public static String DOMAIN_TYPE_TOPIC = "topic";
    
    public static String DOMAIN_TYPE_BLOG = "blog";
    
    public static String DOMAIN_TYPE_STORY = "story";
    
    public static String DOMAIN_TYPE_STORY_TEMPLATE = "storyTemplate";
    
    public static final String STATUS_DEFAULT = "Default";

    public static final String STATUS_WAIT = "Wait";
    
    public static final String STATUS_RUN = "Run";

    public static final String STATUS_STOP = "Stop";

    public static final String STATUS_ERROR = "Error";
    
    //小说任务状态，初始化
    public static final String STORY_JOB_STATUS_INIT = "0";
    
    //小说任务状态，完结
    public static final String STORY_JOB_STATUS_FINISH = "3";
    
    //小说任务状态，重复
    public static final String STORY_JOB_STATUS_REPEAT = "98";
    
    //小说任务状态，错误
    public static final String STORY_JOB_STATUS_ERROR = "99";
    
    //小说状态草稿
    public static final String STORY_STATUS_INIT = "0";
    
    //小说状态上线
    public static final String STORY_STATUS_ONLINE = "2";
    
    //小说状态完结
    public static final String STORY_STATUS_FINISH = "3";
    
    //小说删除或错误
    public static final String STORY_STATUS_ERROR = "99";
    
    //最大30天未更新的小说，算完结
    public static final int MAX_DAY_FINISH = 30;
    
    public static final String TRUE = "1";
    
    public static final String FALSE = "0";
    
    public static String IMG_DOAMIN = "http://www.dodoer.com";
    
    public static String IMG_UPLOAD_QUERY = "/spider/story/pic/download.html?id=%s&picPath=%s";
}
