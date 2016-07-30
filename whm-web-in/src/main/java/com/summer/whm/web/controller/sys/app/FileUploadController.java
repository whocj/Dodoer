package com.summer.whm.web.controller.sys.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.summer.whm.web.common.utils.FileUtils;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/app/fileUpload")
public class FileUploadController extends BaseController {

    public static final String APP_PATH = "/home/default/war";

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
            @RequestParam MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        FileUtils.saveFileFromInputStream(multipartFile.getInputStream(), APP_PATH, filename);
        model.put("msg", "上传成功！");
        return "sys/app/main.ftl";
    }

}
