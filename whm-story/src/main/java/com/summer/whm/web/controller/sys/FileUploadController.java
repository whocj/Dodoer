package com.summer.whm.web.controller.sys;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.summer.whm.common.utils.FileUtils;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/sys/fileUpload")
public class FileUploadController extends BaseController {

    public static final String APP_PATH = "/mnt/data/upload";

    @RequestMapping("/toUpload")
    public String toUpload(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        return "admin/upload/upload.ftl";
    }
    
    @RequestMapping("/submit")
    public void submit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
            @RequestParam MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        FileUtils.saveFileFromInputStream(multipartFile.getInputStream(), APP_PATH, filename);
        response.getOutputStream().println("S");
    }

}