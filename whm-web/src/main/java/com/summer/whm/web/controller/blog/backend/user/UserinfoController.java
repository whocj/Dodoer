package com.summer.whm.web.controller.blog.backend.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.configs.GlobalSystemConfig;
import com.summer.whm.common.utils.MD5;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.user.UserService;
import com.summer.whm.web.common.utils.ImageUtil;
import com.summer.whm.web.common.utils.IntegerUtil;
import com.summer.whm.web.controller.blog.BlogBaseController;

@Controller
@RequestMapping("/blog/backend/userinfo")
public class UserinfoController extends BlogBaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UserinfoController.class);

    public static final String IMG_PATH = GlobalConfigHolder.BASE_STATIC_PATH + GlobalConfigHolder.IMG_PATH  + GlobalConfigHolder.TEMP_STATIC_PATH;
    
    public static final String IMG_PATH_USER = GlobalConfigHolder.BASE_STATIC_PATH + GlobalConfigHolder.IMG_PATH  + "/" + GlobalConfigHolder.IMAGE_PATH_USER;
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private GlobalSystemConfig globalSystemConfig;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return LOGIN_URL;
        }
        addParam(request, response, model, user.getUsername());
        user = userService.loadById(user.getId() + "");
        model.put("user", user);
        setSessionUser(request, user);
        String type = request.getParameter("type");
        if(StringUtils.isNotEmpty(type)){
            request.setAttribute("msg", "第三方认证用户，完善用户信息，拥有自己的博客。");
        }
        return "blog/web/userinfo/userinfo_update.ftl";
    }

    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, ModelMap model, User user) {
        User sessionUser = this.getSessionUser(request);
        user.setId(sessionUser.getId());
        User tempUser = userService.loadById(user.getId() + "");
        
        if(StringUtils.isEmpty(tempUser.getPassword())){
            String p = MD5.encode(System.currentTimeMillis()+"");
            user.setPassword(p);
        }
        userService.saveOrUpdate(user);

        return "redirect:index.html";
    }

    @RequestMapping("/passwordupdate")
    public String passwordupdate(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
     // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }
        
        addParam(request, response, model, user.getUsername());
        return "blog/web/userinfo/password_update.ftl";
    }

    @RequestMapping("/passwordcommit")
    public void passwordcommit(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        // 未登录用户，跳转至登录页面
        User user = this.getSessionUser(request);
        if (user == null) {
            LOG.info("操作异常#" + user);
            return ;
        }
        
        User tempUser = new User();
        tempUser.setId(user.getId());
        String newpassword = request.getParameter("newpassword");
        String newpassword2 = request.getParameter("newpassword2");
        if(newpassword != null && newpassword2 != null && newpassword.equals(newpassword2)){
            String p = MD5.encode(newpassword);
            tempUser.setPassword(p);
            userService.saveOrUpdate(tempUser);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("succ", "S");
        response.getOutputStream().println(JSON.toJSONString(map));
    }

    public boolean checkUser(HttpServletRequest request, User user) {
        User sessionUser = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            return false;
        }

        if (!(user.getId().equals(sessionUser.getId()) && user.getUsername().equals(sessionUser.getUsername()))) {
            return false;
        }
        return true;
    }

    
    
    @RequestMapping("/uploadPic")
    public String uploadPic(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws IOException {
//        ServletOutputStream out = response.getOutputStream();
//        Map<String, String> map = new HashMap<String, String>();
        
        User sessionUser = this.getSessionUser(request);
        if (sessionUser == null) {
            LOG.info("操作异常#" + sessionUser);
            return LOGIN_URL;
        }
        
        String endName = checkFilename(upload.getOriginalFilename());
        
        if (endName == null) {
//            map.put("SUCC", "F");
//            map.put("msg", "文件格式不正确（必须为.jpg/.gif/.bmp/.png/.icon文件)");
            request.setAttribute("SUCC", "F");
            request.setAttribute("msg", "文件格式不正确（必须为.jpg/.gif/.bmp/.png/.icon文件)");
            return "blog/web/userinfo/pic_cropper.ftl"; 
        }
        
        if(upload.getSize() > 20480000){
            request.setAttribute("SUCC", "F");
            request.setAttribute("msg", "头像文件太大。");
            return "blog/web/userinfo/pic_cropper.ftl"; 
        }
        
        String path = createFilePath(IMG_PATH);
        String allPath = IMG_PATH + path;
        
        String name = System.currentTimeMillis() + endName;
        saveFileFromInputStream(upload.getInputStream(), allPath, name);
        
        String name2 = System.currentTimeMillis() + endName;
        
        //头像等比缩放
        ImageUtil.compressImage(allPath + "/" + name, allPath + "/" + name2, GlobalConfigHolder.PIC_MAX_LENGTH);
        
        String picPath = globalSystemConfig.getImgDomain() + GlobalConfigHolder.IMG_PATH + GlobalConfigHolder.TEMP_STATIC_PATH + path + "/" + name2;
//        map.put("SUCC", "S");
//        map.put("msg", picPath);
        request.setAttribute("SUCC", "S");
        request.setAttribute("msg", picPath);
        response.setContentType("text/html;charset=UTF-8");
        //out.println(JSON.toJSONString(map));
        return "blog/web/userinfo/pic_cropper.ftl"; 
    }
    
    public String createFilePath(String typePath) {

        String date = sdf.format(new Date());

        String path = "/" + date;
        File pathFile = new File(typePath + path);
        if (pathFile != null) {
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
        }
        return path;
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
    
    @RequestMapping("/gotoUploadPic")
    public String gotoUploadPic(HttpServletRequest request, HttpServletResponse response) {
        return "blog/web/userinfo/upload_pic.ftl";
    }

    @RequestMapping("/picCropper")
    public void picCropper(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User sessionUser = this.getSessionUser(request);
        if (sessionUser == null) {
            LOG.info("操作异常#" + sessionUser);
            return ;
        }
        
        String left = request.getParameter("left");
        String top = request.getParameter("top");
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        Map<String, String> map = new HashMap<String, String>();
        try{
            //http://localhost/images/temp/20160421/1461209513005.jpg
            String tempFilename = request.getParameter("filename");
            String filename = null;
            if(tempFilename != null){
                if(tempFilename.indexOf("images") != -1){
                    filename =  tempFilename.substring(tempFilename.indexOf("images"));
                }
            }

            if(filename != null){
                String endName = filename.substring(filename.lastIndexOf("."));
                
                String path = GlobalConfigHolder.BASE_STATIC_PATH + "/" + filename;
                ImageUtil.abscut(path, IntegerUtil.parseInt(left), IntegerUtil.parseInt(top), IntegerUtil.parseInt(width), IntegerUtil.parseInt(height));
                
                String userPath = createFilePath(IMG_PATH_USER);
                long times = System.currentTimeMillis();
                String filename30 = IMG_PATH_USER + userPath + "/" + times + "_30" + endName;
                String filename50 = IMG_PATH_USER + userPath + "/" + times + "_50" + endName;
                String filename100 = IMG_PATH_USER + userPath + "/" + times + "_100" + endName;
                
                ImageUtil.compressImage(path, filename30, 30);
                ImageUtil.compressImage(path, filename50, 50);
                ImageUtil.compressImage(path, filename100, 100);
                
                String webPicPath = globalSystemConfig.getImgDomain() + GlobalConfigHolder.IMG_PATH 
                        + "/" + GlobalConfigHolder.IMAGE_PATH_USER +  userPath + "/" + times + "_100" + endName;
                
                map.put("succ", "S");
                map.put("path", webPicPath);
                
                User tempUser = new User();
                tempUser.setId(sessionUser.getId());
                tempUser.setUserLogo(globalSystemConfig.getImgDomain() + GlobalConfigHolder.IMG_PATH 
                        + "/" + GlobalConfigHolder.IMAGE_PATH_USER +  userPath + "/" + times + "_30" + endName);
                tempUser.setUserLogo50(globalSystemConfig.getImgDomain() + GlobalConfigHolder.IMG_PATH 
                        + "/" + GlobalConfigHolder.IMAGE_PATH_USER +  userPath + "/" + times + "_30" + endName);
                tempUser.setUserLogo100(globalSystemConfig.getImgDomain() + GlobalConfigHolder.IMG_PATH 
                        + "/" + GlobalConfigHolder.IMAGE_PATH_USER +  userPath + "/" + times + "_100" + endName);
                userService.saveOrUpdate(tempUser);

            }
        }catch(Exception e){
            map.put("succ", "F");
            map.put("path", "头像裁剪失败.");
        }
        response.getOutputStream().println(JSON.toJSONString(map));
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
