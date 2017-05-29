package com.summer.whm.web.controller.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/download")
public class DownloadController {

    @RequestMapping("/file")
    public void file(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter("path");

        File file = new File(path);

        byte[] buf = new byte[1024 * 8];
        int length = 0;
        
        if (file != null && file.isFile()) {
            
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());// 设置在下载框默认显示的文件名
            response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
            
            FileInputStream fis = new FileInputStream(file);
            ServletOutputStream os = response.getOutputStream();
            while ((length = fis.read(buf)) > 0) {
                os.write(buf, 0, length);
            }
            os.flush();
            fis.close();
            fis = null;
        }
    }
}
