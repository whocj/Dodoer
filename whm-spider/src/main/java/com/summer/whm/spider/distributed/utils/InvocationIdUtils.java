/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: RandomGenerator.java
 * Author:   L.J.W
 * Date:     2013-8-7 下午4:53:58
 * Description: Passport Client
 * History: 
 * <author>      <time>      <version>    <desc>
 * L.J.W         20130807        1.0.0      Passport Client
 */
package com.summer.whm.spider.distributed.utils;

import java.lang.management.ManagementFactory;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */

public class InvocationIdUtils {

    private static String BASE = null;

    private static AtomicPositiveInteger COUNTER = new AtomicPositiveInteger();

    static {
        try {
            BASE = NetUtils.getLocalHost() + "-" + ManagementFactory.getRuntimeMXBean().getName();
        } catch (Throwable ex) {
            int end = 100000;
            int start = 1000;
            BASE = NetUtils.getLocalHost() + "-" + String.valueOf((Math.random() * (end - start + 1)) + start);
        }
    }

    public static String nextId() {
        return BASE + "-" + COUNTER.incrementAndGet() + "-" + System.currentTimeMillis();
    }

    public static Long getCreateTime(String id) {
        return Long.valueOf(StringUtils.substringAfterLast(id, "-"));
    }
}
