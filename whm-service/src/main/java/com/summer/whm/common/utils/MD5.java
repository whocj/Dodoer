/*
 * Copyright (C), 2002-2012, 苏宁易购电子商务有限公司
 * FileName: MD5.java
 * Author:   郭华亮
 * Date:     2012-6-28 下午02:13:37
 * Description: MD5加密工具 
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */

package com.summer.whm.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5加密工具
 */
public class MD5 {
    /**
     * LOG
     */
    private static final Logger LOG = LoggerFactory.getLogger(MD5.class);

    /**
     * 16进制数组
     */
    private static final String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
        "e", "f" };

    /**
     * 256
     */
    private static final int NUMBER_256 = 256;

    /**
     * 16
     */
    private static final int NUMBER_16 = 16;

    /**
     * 转换字节数组为16进制字串
     * 
     * @param b 字节数组
     * @return 16进制字串
     */

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = NUMBER_256 + n;
        }
        int d1 = n / NUMBER_16;
        int d2 = n % NUMBER_16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String encode(String origin) {
        MessageDigest md;
        String md5String = "";
        try {
            md = MessageDigest.getInstance("MD5");
            md5String = byteArrayToHexString(md.digest(origin.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            LOG.debug("NoSuchAlgorithmException :{}" + e);
        }
        return md5String;
    }
    
    public static void main(String[] args){
        //b31e7824f03fdbb6fbcfb58bc152e041
        //b31e7824f03fdbb6fbcfb58bc152e041
        System.out.println(MD5.encode("6114629934+query"));
    }
}