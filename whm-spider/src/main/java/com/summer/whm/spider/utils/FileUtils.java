package com.summer.whm.spider.utils;

import java.io.File;
import java.util.Properties;

public class FileUtils {

    public static String getOSName() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        return os;
    }

    public static boolean isWindow() {
        String os = getOSName();
        if (os != null) {
            if (os.toLowerCase().indexOf("win") != -1) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    public static String getPath(String path, String file) {
        String tempPath = "";

        if (StringUtils.isEmpty(file)) {
            file = "";
        }

        // 如果是win系统，且有：，则进入根目录
        if (isWindow()) {
            if (file.indexOf(":") != -1) {
                path = "";
            }
        } else {
            if (file.startsWith("/")) {
                path = "";
            }

            if (file.startsWith("\\")) {
                path = "";
            }
        }

        if (!StringUtils.isEmpty(path)) {
            tempPath = path;
        } else {
            tempPath = "/";
        }

        if ("..".equals(file)) {
            File f = new File(tempPath);
            if (f.getParent() == null) {
                return tempPath;
            } else {
                return f.getParent();
            }
        }

        if (tempPath.endsWith("/") && file.startsWith("/")) {
            tempPath = tempPath + file.substring(1);
        } else if (tempPath.endsWith("/") && !file.startsWith("/")) {
            tempPath = tempPath + file;
        } else if (!tempPath.endsWith("/") && file.startsWith("/")) {
            tempPath = tempPath + file;
        } else if (!tempPath.endsWith("/") && !file.startsWith("/")) {
            tempPath = tempPath + "/" + file;
        }

        return tempPath;
    }
}
