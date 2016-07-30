package com.summer.whm.web.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
    
    public  static Logger log = LoggerFactory.getLogger(FileUtils.class);
    
    // 将MultipartFile 转换为File
    public static void saveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + "/" + savefile);
        log.info("saveFileFromInputStream#" + path + "/" + savefile);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        log.info("saveFileFromInputStream#File Size :" + bytesum);
        fs.close();
        stream.close();
    }
}
