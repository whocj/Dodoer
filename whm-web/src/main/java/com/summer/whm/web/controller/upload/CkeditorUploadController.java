package com.summer.whm.web.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.configs.GlobalSystemConfig;
import com.summer.whm.web.common.utils.ImageUtil;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/ckeditor")
public class CkeditorUploadController extends BaseController {

    public static final String IMG_PATH = GlobalConfigHolder.BASE_STATIC_PATH  + GlobalConfigHolder.IMG_PATH;

    public static Logger log = LoggerFactory.getLogger(CkeditorUploadController.class);

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private GlobalSystemConfig globalSystemConfig;
    
    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response,
            @RequestParam MultipartFile upload) {
        String filename = upload.getOriginalFilename();
        try {
            ServletOutputStream out = response.getOutputStream();

            String callback = request.getParameter("CKEditorFuncNum");
            String endName = checkFilename(filename);
            if (endName == null) {
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'',"
                        + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png/.icon文件）');");
                out.println("</script>");
            }
            
            String path = createFilePath(request);
            String allPath = IMG_PATH + path;
            
            String name = System.currentTimeMillis() + endName;
            saveFileFromInputStream(upload.getInputStream(), allPath, name);
            //图片行比缩放
            if(!(name.endsWith("gif") || name.endsWith("GIF"))){//GIF不裁剪
                ImageUtil.compressImage(allPath + "/" + name, allPath + "/" + name, GlobalConfigHolder.PIC_MAX_LENGTH);
            }
            
            out.println("<script type=\"text/javascript\">");  
            out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" + globalSystemConfig.getImgDomain() + GlobalConfigHolder.IMG_PATH + path + "/" + name + "','')");   
            out.println("</script>");
        } catch (IOException e) {
            log.error("上传文件失败.", e);
        }
    }

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        out.println(1);
    }

    public String checkFilename(String filename) {
        if (filename.endsWith(".jpg") || filename.endsWith(".JPG") || filename.endsWith(".jpeg")
                || filename.endsWith(".JPEG") || filename.endsWith(".gif") || filename.endsWith(".GIF")
                || filename.endsWith(".bmp") || filename.endsWith(".BMP") || filename.endsWith(".png")
                || filename.endsWith(".PNG") || filename.endsWith(".icon") || filename.endsWith(".ICON")) {

            String endName = filename.substring(filename.lastIndexOf("."));
            return endName;
        }
        return null;
    }

    public String createFilePath(HttpServletRequest request) {
        String CKEditor = request.getParameter("CKEditor");
        String type = GlobalConfigHolder.IMAGE_TYPE_MAP.get(CKEditor);
        if (type == null) {
            type = GlobalConfigHolder.IMAGE_PATH_DEFAULT;
        }

        String date = sdf.format(new Date());

        String path = "/" + type + "/" + date;
        File pathFile = new File(IMG_PATH + path);
        if (pathFile != null) {
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
        }

        return path;
    }

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
