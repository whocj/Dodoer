package com.summer.whm.web.common.sys;

import javax.servlet.ServletContext;

import com.summer.whm.web.common.support.WebContextFactory;

/**
 * 系统信息
 * 
 * @author zhou
 * 
 */
public class OSInfo {
    private String osName;
    private String osVersion;
    private String javaVersion;
    private String serverInfo;
    private long totalMemory;

    private OSInfo() {
    }

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    /**
     * 获取服务器信息
     * 
     * @return
     */
    public String getServerInfo() {
        return serverInfo;
    }

    /**
     * 获取java进程内存大小，以M为单位
     * 
     * @return
     */
    public long getTotalMemory() {
        return totalMemory;
    }

    public static OSInfo getCurrentOSInfo() {
        OSInfo instance = new OSInfo();
        instance.osName = System.getProperty("os.name");
        instance.osVersion = System.getProperty("os.version");
        instance.javaVersion = System.getProperty("java.version");
        ServletContext sc = WebContextFactory.get().getRequest().getSession().getServletContext();
        instance.serverInfo = sc.getServerInfo();
        instance.totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;

        return instance;
    }

    @Override
    public String toString() {
        return "OSInfo [osName=" + osName + ", osVersion=" + osVersion + ", javaVersion=" + javaVersion
                + ", serverInfo=" + serverInfo + ", totalMemory=" + totalMemory + "]";
    }

    public static void main(String[] args){
        System.out.println(getCurrentOSInfo());
    }
}
