package com.summer.whm.spider.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIP {

    public static String getLocalIP() {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     */
    public static void main(String[] args) {
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(ip);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
