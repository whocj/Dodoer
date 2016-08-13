package com.summer.whm.web.servlet.image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.web.servlet.AbstractBaseServlet;

public class ImageServlet extends AbstractBaseServlet {

    private static final long serialVersionUID = -7394929048383966463L;

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String path = GlobalConfigHolder.BASE_STATIC_PATH + uri;
        File file = new File(path);
        if (file.exists()) {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buff = new byte[1024 * 8];
            int len = -1;
            OutputStream os = resp.getOutputStream();
            while ((len = bis.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
            bis.close();
        }
    }

}
