package com.summer.whm.spider.distributed.utils;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public abstract class MethodIdUtils {

    public static String getMethodId(String methodName, Class[] argTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);
        if (argTypes != null && argTypes.length != 0) {
            sb.append(".");
            for (Class clz : argTypes) {
                sb.append(clz.getName());
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String getMethodId(String methodName, String[] argTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);
        if (argTypes != null && argTypes.length != 0) {
            sb.append(".");
            for (String clz : argTypes) {
                sb.append(clz);
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String getMethodId(String methodName, String argTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);
        if (argTypes != null) {
            sb.append(".");
            sb.append(argTypes);
        }
        return sb.toString();
    }

}
