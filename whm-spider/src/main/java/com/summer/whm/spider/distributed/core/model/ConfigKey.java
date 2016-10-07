package com.summer.whm.spider.distributed.core.model;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public interface ConfigKey {

    public static String LOW_PRIORITY_SERVICE_EXECUTION_POOL_SIZE = "lpServiceExecutionPoolSize";

    public static String LOW_PRIORITY_SERVICE_EXECUTION_QUEUE_SIZE = "lpServiceExecutionQueueSize";

    public static String MIDDLE_PRIORITY_SERVICE_EXECUTION_POOL_SIZE = "mpServiceExecutionPoolSize";

    public static String MIDDLE_PRIORITY_SERVICE_EXECUTION_QUEUE_SIZE = "mpServiceExecutionQueueSize";

    public static String HIGH_PRIORITY_SERVICE_EXECUTION_POOL_SIZE = "hpServiceExecutionPoolSize";

    public static String HIGH_PRIORITY_SERVICE_EXECUTION_QUEUE_SIZE = "hpServiceExecutionQueueSize";

    public static String CONSUMER_COMMON_EXECUTION_POOL_SIZE = "consumerCommonExecutionPoolSize";

}
