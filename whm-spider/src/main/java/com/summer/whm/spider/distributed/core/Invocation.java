package com.summer.whm.spider.distributed.core;

import java.util.Map;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */

public interface Invocation {

    String getId();

    void setIdempotent(boolean idempotent);

    boolean isIdempotent();

    void setRequestTimeout(long timeout);

    long getRequestTimeout();

    void setRetryTimes(int times);

    int getRetryTimes();

    String getContract();

    String getImplCode();

    /**
     * get method name.
     *
     * @return method name.
     */
    String getMethodName();

    /**
     * get parameter types.
     *
     * @return parameter types.
     */
    Class<?>[] makeParameterTypes();

    String[] getParameterTypes();

    /**
     * get arguments.
     *
     * @return arguments.
     */
    Object[] getArguments();

    /**
     * get attachments.
     *
     * @return attachments.
     */
    Map<String, String> getAttachments();

    /**
     * get attachment by key.
     *
     * @return attachment value.
     */
    String getAttachment(String key);

    /**
     * get attachment by key with default value.
     *
     * @return attachment value.
     */
    String getAttachment(String key, String defaultValue);

    void setAttachment(String key, String value);

}