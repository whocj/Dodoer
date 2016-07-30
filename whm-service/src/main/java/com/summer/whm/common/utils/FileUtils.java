package com.summer.whm.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * 
 * @author zhou
 * 
 */
public class FileUtils {
    private final static Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    /**
     * 获取文件名后缀(不含".")
     * 
     * @param filename
     * @return
     */
    public static String getFileExt(String filename) {
        int point = filename.lastIndexOf(".");
        return filename.substring(point + 1);
    }

    /**
     * 获取文件名(不包含后缀)
     * 
     * @param filename
     * @return
     */
    public static String getFileName(String filename) {
        filename = getFileNameWithExt(filename);
        int point = filename.lastIndexOf(".");
        return filename.substring(0, point);
    }

    /**
     * 获取文件名(包含后缀)
     * 
     * @param filename
     * @return
     */
    public static String getFileNameWithExt(String filename) {
        int slash = filename.lastIndexOf("/");
        return filename.substring(slash + 1);
    }

    /**
     * 判断指定格式是否为图片
     * 
     * @param ext
     * @return
     */
    public static boolean isImageExt(String ext) {
        return ext != null && Arrays.asList("jpg", "jpeg", "png", "bmp", "gif").contains(ext.toLowerCase());
    }

    /**
     * 生成文件存储名
     * 
     * @param parent
     * @param fileName
     * @return
     */
    public static File determineFile(File parent, String fileName) {
        String name = getFileName(fileName);
        String ext = getFileExt(fileName);
        File temp = new File(parent, fileName);
        for (int i = 1; temp.exists(); i++) {
            temp = new File(parent, name + i + "." + ext);
        }

        return temp;
    }

    /**
     * @param 该类是公共的文件操作类里面有文件遍历，文件复制等功能
     */
    /* 复制文件 */
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        /* 把源文件读入内存 */
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);
        /* 创建输出流 */
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);
        /* 缓冲数组 */
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        outBuff.flush();
        /* 关闭流 */
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /* 文件夹排序，按照文件夹的创建时间倒排 */
    public static File[] sortByTime(File[] files) {
        for (int i = 0; i < files.length; i++) {
            for (int j = i + 1; j < files.length; j++) {
                if (files[i].lastModified() < files[j].lastModified()) {
                    File fileTmp = files[i];
                    files[i] = files[j];
                    files[j] = fileTmp;
                }
            }
        }
        return files;
    }

    /* 删除某路径下文件方法，该方法是指定了删除数量 */
    public static void getFileListForDelete(String fileDir) {
        File file = new File(fileDir);
        File[] files = file.listFiles();
        File[] lastFiles = sortByTime(files);
        for (int i = 0; i < lastFiles.length; i++) {
            if (i > 1) {
                deleteFile(lastFiles[i]);
            }
        }
    }

    /* 删除文件夹以及文件夹子文件方法 */
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                boolean isSucess = file.delete();
                if (isSucess == false) {
                    LOG.error("在删除文件" + file.getName() + "时候失败 !在类FileUtil的deleteFile方法中");
                }
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            boolean isSucess = true;
            if (file.isDirectory()) {
                isSucess = file.delete();
                if (isSucess == false) {
                    LOG.error("在删除文件" + file.getName()
                            + "时候删除文件夹失败 !在类FileUtil的deleteFile方法中================================");
                }
            }
        } else {
            LOG.info("所删除的文件不存在！" + '\n');
        }
    }

    // add by wangting 20130830
    public static void backUpFile(String txtBackUpUrl, String txt_url, String time) {
        // 复制train.txt文件
        String targetDir = txtBackUpUrl + time;
        /* 创建目标文件夹 */
        new File(targetDir).mkdirs();
        File[] file = (new File(txt_url)).listFiles();
        File sourceFile = new File(txt_url + "train.txt");
        File targetFile = new File(targetDir + "/train.txt");
        LOG.info("-----备份train.txt开始-----");
        try {
            copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            LOG.error("备份上传原始数据train.txt的时候出现了异常" + e);
        }
        LOG.info("-----备份train.txt结束-----");
        LOG.info("-----备份bin文件开始-----");
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile() && file[i].getName().endsWith(".bin")) {
                sourceFile = new File(txt_url + file[i].getName());
                targetFile = new File(targetDir + "/" + file[i].getName());
                try {
                    copyFile(sourceFile, targetFile);
                } catch (IOException e) {
                    LOG.error("备份上传原始数据" + file[i].getName() + "的时候出现了异常" + e);
                }
            }
        }
        LOG.info("-----备份bin文件结束-----");
    }

    /**
     * 指定时间存储路径
     * 
     * @return
     */
    public static String getTime() {
        SimpleDateFormat dateF = new SimpleDateFormat("yyyyMMddHHmmss");
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");
        dateF.setTimeZone(timeZoneChina);
        Date td = new Date();
        return dateF.format(td);
    }
}
