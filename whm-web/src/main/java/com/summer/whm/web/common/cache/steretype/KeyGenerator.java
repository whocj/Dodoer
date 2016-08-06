package com.summer.whm.web.common.cache.steretype;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Summer
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class KeyGenerator {
    public static final int NO_PARAM_KEY = 0;
    public static final int NULL_PARAM_KEY = 53;

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param target
     * @param key
     * @param params
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Object generate(Object target, String key, Object... params) {
        int hashCode = 0;

        if (StringUtils.isNotBlank(key)) {
            hashCode = hashCode + key.hashCode();
        } else {
            hashCode = hashCode + target.hashCode();
        }
        if (params.length > 0) {
            for (Object object : params) {
                hashCode = NULL_PARAM_KEY * hashCode + (object == null ? NULL_PARAM_KEY : object.hashCode());
            }
        }

        return Integer.valueOf(hashCode);
    }
    
    public static void main(String[] args){
        
    }
}
