package com.summer.whm.common.configs;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.common.utils.PropertiesUtil;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 配置文件读取，保存，刷新类。 <br>
 * 使用配置文件(/opt/search/datasupport/configs/ds_config.proerties) 覆盖GlobalConfigHolder中的值<br>
 * 开发需要自己定义GlobalConfigHolder可被覆盖的常量<br>
 * 如果该值依赖于环境dev/sit/pre/prd，请将值写在/opt/search/datasupport/configs/ds_config. proertis中<br>
 * 各个环境配置文件的svn地址为:<br>
 * http://svn.cnsuning.com/svn/SES-TOOLS/trunk/env-config
 * 
 * @author 12091669
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class GlobalConfigHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalConfigHolder.class);

    public static final String CONFIG_NAME = "/opt/whm/configs/config.properties";

    public static Properties GLOBAL_CONFIG = null;
    
    public static String BASE_PATH = "D:/";

    public static String DEFAULT_DELIMITER = "*-*";

    public static String SPLIT_DEFAULT_DELIMITER = "\\*\\-\\*";
    
    public static String ASSOCIATE_WORDS_CACHE = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
    
    /**
     * 路径分隔符
     */
    public static  String SPT = "/";
    /**
     * 索引页
     */
    public static  String INDEX = "index";
    /**
     * 默认模板
     */
    public static  String DEFAULT = "default";
    /**
     * UTF-8编码
     */
    public static  String UTF8 = "UTF-8";
    /**
     * 提示信息
     */
    public static  String MESSAGE = "message";
    /**
     * cookie中的JSESSIONID名称
     */
    public static  String JSESSION_COOKIE = "JSESSIONID";
    /**
     * url中的jsessionid名称
     */
    public static  String JSESSION_URL = "jsessionid";

    /**
     * HTTP POST请求
     */
    public static  String POST = "POST";
    /**
     * HTTP GET请求
     */
    public static  String GET = "GET";

    public static  String COOKIE_CONTEXT_ID = "c_id";
    public static  String COOKIE_USER_NAME = "un";
    public static  String COOKIE_USER_ID = "ui";

    public static final Map<String, String> SKIN_MAP = new HashMap<String, String>();

    public static  Integer SITE_ID_BBS = 1;

    public static  String IS_STR_TRUE = "1";

    public static  String IS_STR_FALSE = "0";

    public static String CURRENT_MENU_CODE = "CURRENT_MENU_CODE";
    public static  Integer MENU_DEFAULT_CODE = 0;
    public static  Integer MENU_ASK_CODE = 1;
    public static  Integer MENU_QUESTION_CODE = 2;
    public static  Integer MENU_BBS_CODE = 3;
    public static  Integer MENU_BLOG_CODE = 4;
    public static  Integer MENU_MORE_CODE = 5;

    public static  String FRIST_DO_MAIN = "www.whohelpme.com";
    public  static Integer DEFAULT_POST_STATUS = 1;
    
    public static String AUTHOR_ANONYMOUS = "anonymous";
    
    public static String DOMAIN_URL = "http://www.whohelpme.com";
    
    public static String DODOER_DOMAIN_URL = "http://www.dodoer.com";
    
    public static String DODOER_M_DOMAIN_URL = "http://m.dodoer.com";
    
    //主题贴
    public static String BBS_TOPIC_URL = DOMAIN_URL + "/bbs/topic/batchCommit.htm";
    
    //回复贴
    public static String BBS_POST_URL = DOMAIN_URL + "/bbs/topic/batchPostCommit.htm";
    
    //爬虫等等时间
    public static Integer SPIDER_SLEEP_TIME  = 2000;
    
    //超时时间
    public static Integer SPIDER_CLIENT_TIMEOUT  = 20000;
    
    //百度索引push
    public static String BAIDU_POST_URL = "http://data.zz.baidu.com/urls?site=www.whohelpme.com&token=LRAL2X93j8Mtg8wP";
    
    //百度索引每天可push数量
    public static Integer BAIDU_POST_COUNT = 500;
    
    //显示联想词个数
    public static Integer ASSOCIATE_WORD_SIZE = 6;
    
    public static final String IMAGE_PATH_ASK = "ask";

    public static final String IMAGE_PATH_BBS = "bbs";

    public static final String IMAGE_PATH_BLOG = "blog";
    
    public static final String IMAGE_PATH_DEFAULT = "default";
    
    public static final String IMAGE_PATH_USER = "user";

    public static final Map<String, String> IMAGE_TYPE_MAP = new HashMap<String, String>();
    
    public static String BASE_STATIC_PATH = "/opt/whm/static";
    
    public static String DODOER_STATIC_PATH = "/opt/dodoer/static";
    
    public static String IMG_PATH = "/images";
    
    public static String TEMP_STATIC_PATH = "/temp";
    
    public static String DOMAIN_TYPE_QUESTION = "question";
    
    public static String DOMAIN_TYPE_TOPIC = "topic";
    
    public static String DOMAIN_TYPE_BLOG = "blog";
    
    public static String DOMAIN_TYPE_STORY = "story";
    
    //默认加载5万条记录
    public static Integer SITEMAP_COUNT = 200;
    
    //图片等比缩放
    public static int PIC_MAX_LENGTH = 500;
    
    public static String DEFAULT_USER_PIC = "/images/user/avatar.png";

    //每爬100个页面休息5分钟
    public static Integer SPIDER_CRAWL_SLEEP_TIME = 300000;
    
    static {
        SKIN_MAP.put("blue", "blue");
        SKIN_MAP.put("green", "green");
        SKIN_MAP.put("red", "red");
        SKIN_MAP.put("default", "default");
        
        IMAGE_TYPE_MAP.put("questionContent", IMAGE_PATH_ASK);
        IMAGE_TYPE_MAP.put("answerContent", IMAGE_PATH_ASK);
        IMAGE_TYPE_MAP.put("topicContent", IMAGE_PATH_BBS);
        IMAGE_TYPE_MAP.put("blogContent", IMAGE_PATH_BLOG);
    }
    
    static {
        inital();
    }

    /**
     * 
     * 功能描述: <br>
     * 初始化静态变量
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void inital() {
        initDefaultValue();
        overrideDefaultByConfigFile();
    }

    public static void initDefaultValue() {

    }

    /**
     * 
     * 功能描述: <br>
     * 使用配置文件，覆盖静态属性值<br>
     * 支持自动覆盖的条件有：<br>
     * 1 配置名与属性名相同 2 属性修饰符为public static 且 不是final<br>
     * 3 属性类型：boolean,int,String,long,float<br>
     * 如果不满足以上条件，不进行配置修改
     * 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static void overrideDefaultByConfigFile() {
        GLOBAL_CONFIG = PropertiesUtil.loadProperties("file:" + CONFIG_NAME);
        GlobalConfigHolder gc = null;
        try {
            gc = GlobalConfigHolder.class.newInstance();
        } catch (Exception e) {
        }
        Field[] fs = GlobalConfigHolder.class.getDeclaredFields();
        if (fs != null && fs.length > 0) {
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                // LOGGER.info(f.getName()+":"+Modifier.isStatic(f.getModifiers())+"|"+Modifier.isPublic(f.getModifiers())+"|"+Modifier.isFinal(f.getModifiers()));
                if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
                    if (!Modifier.isFinal(f.getModifiers())) {
                        String k = f.getName();
                        String v = GLOBAL_CONFIG.getProperty(k);
                        if (v != null) {
                            v = v.trim();
                            LOGGER.info("override proprety :" + k + "=" + v);
                            if (f.getType().equals(boolean.class)) {
                                try {
                                    f.setBoolean(gc, Boolean.parseBoolean(v));
                                } catch (Exception e) {
                                    LOGGER.error("覆盖配置信息发生异常：属性名 " + k + " | 属性值-" + v + " | 属性类型-"
                                            + f.getType().getName());
                                }
                            } else if (f.getType().equals(int.class)) {
                                try {
                                    f.setInt(gc, Integer.parseInt(v));
                                } catch (Exception e) {
                                    LOGGER.error("覆盖配置信息发生异常：属性名 " + k + " | 属性值-" + v + " | 属性类型-"
                                            + f.getType().getName());
                                }
                            } else if (f.getType().equals(String.class)) {
                                try {
                                    f.set(gc, v);
                                } catch (Exception e) {
                                    LOGGER.error("覆盖配置信息发生异常：属性名 " + k + " | 属性值-" + v + " | 属性类型-"
                                            + f.getType().getName());
                                }
                            } else if (f.getType().equals(long.class)) {
                                try {
                                    f.setLong(gc, Long.parseLong(v));
                                } catch (Exception e) {
                                    LOGGER.error("覆盖配置信息发生异常：属性名 " + k + " | 属性值-" + v + " | 属性类型-"
                                            + f.getType().getName());
                                }
                            } else if (f.getType().equals(float.class)) {
                                try {
                                    f.setFloat(gc, Float.parseFloat(v));
                                } catch (Exception e) {
                                    LOGGER.error("覆盖配置信息发生异常：属性名 " + k + " | 属性值-" + v + " | 属性类型-"
                                            + f.getType().getName());
                                }
                            } else if (f.getType().equals(double.class)) {
                                try {
                                    f.setDouble(gc, Double.parseDouble(v));
                                } catch (Exception e) {
                                    LOGGER.error("覆盖配置信息发生异常：属性名 " + k + " | 属性值-" + v + " | 属性类型-"
                                            + f.getType().getName());
                                }
                            } else {
                                LOGGER.warn("未支持的属性类型：属性名 " + k + " | 属性值-" + v + " | 属性类型-" + f.getType().getName());
                            }
                        }
                    }
                }
            }
        }
    }

    public static Map<String, Object> currentConfigs() {
        Map<String, Object> result = new HashMap<String, Object>();
        GlobalConfigHolder gc = null;
        try {
            gc = GlobalConfigHolder.class.newInstance();
        } catch (Exception e) {
        }
        Field[] fs = GlobalConfigHolder.class.getDeclaredFields();
        if (fs != null && fs.length > 0) {
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                // LOGGER.info(f.getName()+":"+Modifier.isStatic(f.getModifiers())+"|"+Modifier.isPublic(f.getModifiers())+"|"+Modifier.isFinal(f.getModifiers()));
                if (Modifier.isStatic(f.getModifiers()) && Modifier.isPublic(f.getModifiers())) {
                    if (!Modifier.isFinal(f.getModifiers())) {
                        String k = f.getName();

                        if (f.getType().equals(boolean.class)) {
                            try {
                                result.put(k, f.getBoolean(gc) + "");
                                LOGGER.info("read config :" + k + "=" + f.getBoolean(gc));
                            } catch (Exception e) {
                            }
                        } else if (f.getType().equals(int.class)) {
                            try {
                                result.put(k, f.getInt(gc) + "");
                                LOGGER.info("read config :" + k + "=" + f.getInt(gc));
                            } catch (Exception e) {

                            }
                        } else if (f.getType().equals(String.class)) {
                            try {
                                result.put(k, f.get(gc) + "");
                                LOGGER.info("read config :" + k + "=" + f.get(gc));
                            } catch (Exception e) {

                            }
                        } else if (f.getType().equals(long.class)) {
                            try {
                                result.put(k, f.getLong(gc) + "");
                                LOGGER.info("read config :" + k + "=" + f.getLong(gc));
                            } catch (Exception e) {

                            }
                        } else if (f.getType().equals(float.class)) {
                            try {
                                result.put(k, f.getFloat(gc) + "");
                                LOGGER.info("read config :" + k + "=" + f.getFloat(gc));
                            } catch (Exception e) {

                            }
                        } else if (f.getType().equals(double.class)) {
                            try {
                                result.put(k, f.getDouble(gc) + "");
                                LOGGER.info("read config :" + k + "=" + f.getDouble(gc));
                            } catch (Exception e) {

                            }
                        } else {
                            try {
                                LOGGER.warn("未支持的属性类型：属性名 " + k + " | 属性值-" + f.get(gc) + " | 属性类型-"
                                        + f.getType().getName());
                            } catch (Exception e) {
                            }
                        }

                    }
                }
            }
        }
        return result;
    }

    public static void updateConfigByTemplate(Map<?, ?> template, boolean persist) {
        if (template != null && !template.isEmpty()) {
            GLOBAL_CONFIG.putAll(template);
            if (persist) {
                PropertiesUtil.store(GLOBAL_CONFIG, CONFIG_NAME);
            }
            inital();
        }
    }

    public static void updateConfig(String key, String value, boolean persist) {
        GLOBAL_CONFIG.setProperty(key, value);
        if (persist) {
            PropertiesUtil.store(GLOBAL_CONFIG, CONFIG_NAME);
        }
        inital();
    }

    public static boolean moduleEnabled(String moduleName) {
        boolean result = true;
        result = "true".equals(GLOBAL_CONFIG.getProperty(moduleName + "_module_enabled", "true"));
        return result;
    }

    public static void setModuleEnabled(String moduleName, String enable) {
        GLOBAL_CONFIG.setProperty(moduleName + "_module_enabled", enable);
        PropertiesUtil.store(GLOBAL_CONFIG, CONFIG_NAME);
    }
}
