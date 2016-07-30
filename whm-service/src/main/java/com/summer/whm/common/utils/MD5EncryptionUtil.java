/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: MD5EncryptionUtil.java
 * Author:   14073316
 * Date:     2014-12-10 下午3:58:30
 * Description: MD5加密工具      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.summer.whm.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

/**
 * 〈一句话功能简述〉<br>
 * 〈MD5加密工具〉
 * 
 * @author 14073316
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MD5EncryptionUtil {

    /**
     * 
     * 功能描述：加密
     * 
     * @param rawPass:密钥与字符串
     * @param algorithm:加密规则
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    private static String encodePassword(String rawPass, String algorithm) {
        MessageDigest messageDigest = getMessageDigest(algorithm);
        byte[] digest;
        try {
            digest = messageDigest.digest(rawPass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported!");
        }
        return new String(Hex.encodeHex(digest));
    }

    /**
     * 
     * 功能描述:根据密钥与字符串对其进行加密
     * 
     * @param sourceString：要加密的资源
     * @param key：密钥
     * @return String:加密后的字符串
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static String encrypt(String sourceString, String key) {
        return encodePassword(sourceString + key, "MD5");
    }

    /**
     * 
     * 功能描述： 初始化加密规则
     * 
     * @param algorithm :加密规则
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    private static MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }
}
