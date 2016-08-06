package com.summer.whm.web.common.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegerUtil {

    private static final Logger LOG = LoggerFactory.getLogger(IntegerUtil.class);

    // 有小数取整，异常返回0
    public static int parseInt(String str) {
        int ret = 0;
        try {
            String temp = null;
            if (StringUtils.isNotEmpty(str)) {
                if (str.indexOf(".") != -1) {
                    temp = str.substring(0, str.indexOf("."));
                    ret = Integer.parseInt(temp);
                }else{
                    ret = Integer.parseInt(str);
                }
            }
        } catch (Exception e) {
            LOG.info("ParseInt Exception str#" + str, e);
            ret = 0;
        }

        return ret;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        System.out.println(parseInt(""));
        System.out.println(parseInt("1.1"));
        System.out.println(parseInt("10.1.1"));
        System.out.println(parseInt("101.101"));
        System.out.println(parseInt("101101"));
        System.out.println(parseInt("000101101"));
        System.out.println(parseInt("00000101101"));
    }

}
