package com.summer.whm.web.common;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


/**
 * Spring容器取实例
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SpringContainer {

    public static Object getBean(String name) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        return wac.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        return wac.getBean(clazz);
    }
}
