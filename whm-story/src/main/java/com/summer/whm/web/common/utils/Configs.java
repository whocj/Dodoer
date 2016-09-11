package com.summer.whm.web.common.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.common.utils.PropertiesUtil;

public class Configs {
    static final Logger log = LoggerFactory.getLogger(Configs.class);
    public static final String CONFIG_FILE = "/opt/dodoer/configs/dodoer_configs.properties";

    public static Properties p = new Properties();

    public static final String APP_CONFIGS = "configs";
    
    public static final String CATEGORY_LIST = "categoryList";
    
    // 论坛开关
    public static boolean SITE_BBS_ENABLE = false;

    public static boolean SITE_BLOG_ENABLE = false;

    public static boolean SITE_ASK_ENABLE = false;

    public static boolean SITE_CHART_ENABLE = false;
    
    public static boolean BAIDU_JIASHU = true;
    
    public static String CACHE_CONTROL = "max-age=1800";
    
    public static String TEST = "1";

    public static void updateConfig(String key, String value) {
        p.setProperty(key, value);
        PropertiesUtil.store(p, CONFIG_FILE);
        initial();
    }
    
    static {
        initial();
    }

    public static void initial() {
        try {
            p = PropertiesUtil.loadProperties("file:" + CONFIG_FILE);
        } catch (Exception e) {
            log.error("Configs 初始化失败.", e);
        }

        try {
            if(p == null){
                p = new Properties();
            }
            ConfigUpdater.overrideDefaultByConfigFile(p, Configs.class.newInstance());
        } catch (Exception e) {
            log.error("Configs 持久化失败.", e);
        }
    }
}
