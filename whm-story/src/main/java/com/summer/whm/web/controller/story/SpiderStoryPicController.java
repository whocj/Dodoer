package com.summer.whm.web.controller.story;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.spider.utils.Hex;
import com.summer.whm.spider.utils.img.Image;
import com.summer.whm.spider.utils.img.ImageService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/story/pic")
public class SpiderStoryPicController extends BaseController {

    @Autowired
    private StoryInfoService storyInfoService;
    
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response, String picPath, int id,
            ModelMap model) throws UnsupportedEncodingException {
        
        if(picPath != null && id != 0){
            String picUrl = Hex.hexStr2Str(picPath);
            //String picUrl = Hex.decodeHexString(picPath.getBytes("utf-8")).toUpperCase();
            
            Image image = ImageService.downloadImgByUrl(picUrl);
            if(image != null){
                StoryInfo storyInfo = new StoryInfo();
                storyInfo.setId(id);
                storyInfo.setPicPath(image.getUrl());
                storyInfoService.save(storyInfo);
                ajaxJson(response, "S");
            }else{
                ajaxJson(response, "F");
            }
        }else{
            ajaxJson(response, "F");    
        }
    }
    
    public static void main(String[] args) throws Exception{
        String picPath = "http://image.suning.cn/uimg/b2c/newcatentries/0070074453-000000000154210350_1_160x160.jpg";
        System.out.println(picPath);
        String hex = Hex.byte2HexStr(picPath.getBytes("utf-8"));
        System.out.println(hex);
        hex = Hex.hexStr2Str(hex);
        System.out.println(hex);
    }
}
