package com.summer.whm.spider.utils.img;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.spider.utils.CustomerHttpClient;
import com.summer.whm.spider.utils.HttpResp;
import com.summer.whm.spider.utils.CustomerHttpClient.ClientName;

public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);

    public static final String IMAGE_CONTENT_TYPE = "image/jpeg";

    public static String DODOER_STATIC_PATH = "/opt/dodoer/static";

    public static String DODOER_STATIC_STORY_PATH = "/images/story/";
    
    public static String imgDomain = "http://img.dodoer.com";
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

    public static Image downloadImgByUrl(String url) {
        String mm = sdf.format(new Date());
        Image image = null;
        String endName = getEndName(url);
        String name = System.currentTimeMillis() + endName;
        String path = DODOER_STATIC_PATH + DODOER_STATIC_STORY_PATH + mm + "/";
        String urlImage = imgDomain + DODOER_STATIC_STORY_PATH + mm + "/" + name;
        
        HttpResp resp = CustomerHttpClient.getResponse(url, ClientName.DEFAULT_CLIENT);
        if ("200".equals(resp.getCode()) && IMAGE_CONTENT_TYPE.equals(resp.getContentType().getValue())) {
            InputStream is = resp.getIs();
            FileOutputStream fos = null;
            byte[] buf = new byte[1024 * 8];
            int len = -1;
            try {
                File file = new File(path);
                if (file != null && !file.exists()) {
                    file.mkdirs();
                }

                fos = new FileOutputStream(path  + name);
                while ((len = is.read(buf, 0, 1024 * 8)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.close();
                is.close();
                image = new Image();
                image.setAllName(path + name);
                image.setName(name);
                image.setEndName(endName);
                image.setUrl(urlImage);
            } catch (IOException e) {
                log.error("下载图片失败", e);
            }
        }else{
            log.error("下载图片失败", resp);
        }

        return image;
    }

    public static Image downloadImgByInputStream(InputStream is, String filename) {
        String mm = sdf.format(new Date());
        Image image = null;
        String endName = getEndName(filename);
        String name = System.currentTimeMillis() + endName;
        String path = DODOER_STATIC_PATH + DODOER_STATIC_STORY_PATH + mm + "/";
        String urlImage = imgDomain + DODOER_STATIC_STORY_PATH + mm + "/" + name;

        FileOutputStream fos = null;
        byte[] buf = new byte[1024 * 8];
        int len = -1;
        try {
            File file = new File(path);
            if (file != null && !file.exists()) {
                file.mkdirs();
            }

            fos = new FileOutputStream(path  + name);
            while ((len = is.read(buf, 0, 1024 * 8)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();
            image = new Image();
            image.setAllName(path + name);
            image.setName(name);
            image.setEndName(endName);
            image.setUrl(urlImage);
        } catch (IOException e) {
            log.error("下载图片失败", e);
        }

        return image;
    }

    
    public static String getEndName(String url) {
        if (url != null) {
            if (url.lastIndexOf(".") != -1) {
                return url.substring(url.lastIndexOf("."));
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "http://image.suning.cn/uimg/b2c/newcatentries/0070074453-000000000154210350_1_160x160.jpg";
        Image img = downloadImgByUrl(url);
        System.out.println(img);
    }
}
