package com.summer.whm.spider.distributed.utils;

import java.lang.management.ManagementFactory;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */

public class ProcessUtils {

    private static volatile String PROCESS_ID = null;

    static {
        initProcessId();
    }

    private static void initProcessId() {
        try {
            PROCESS_ID = ManagementFactory.getRuntimeMXBean().getName();
        } catch (Throwable ex) {
            //do nothing
        }
        if (StringUtils.isEmpty(PROCESS_ID)) {
            int end = 100000;
            int start = 1000;
            PROCESS_ID = String.valueOf((Math.random() * (end - start + 1)) + start);
        }
    }

    public static String getProcessId() {
        if (StringUtils.isEmpty(PROCESS_ID)) {
            initProcessId();
        }
        return PROCESS_ID;
    }

}
