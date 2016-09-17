package com.summer.whm.web.controller.story;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.summer.whm.Constants;
import com.summer.whm.common.utils.PinUtil;
import com.summer.whm.entiry.author.Author;
import com.summer.whm.entiry.author.AuthorDetail;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.user.User;
import com.summer.whm.entiry.user.UserConstants;
import com.summer.whm.service.author.AuthorDetailService;
import com.summer.whm.service.author.AuthorService;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/author/edit")
public class AuthorEditController extends BaseController {
    public static Logger log = LoggerFactory.getLogger(AuthorEditController.class);
    @Autowired
    public AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorDetailService authorDetailService;

    @Autowired
    private StoryInfoService storyInfoService;

    private boolean checkRole(User user) {
        if (user.getRole() == null || UserConstants.USER_ROLE_CONTRIBUTOR.equals(user.getRole())) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping("/info/{id}")
    public String info(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        User user = this.getSessionUser(request);
        if (user == null) {
            return LOGIN_URL;
        }

        if (!checkRole(user)) {
            return ERROR_ROLE;
        }

        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        Author author = authorService.queryById(id);

        model.put("author", author);
        model.put("categoryList", categoryList);
        return getForward(request, response, "story/author/edit/author_info_edit_index.ftl");
    }

    @RequestMapping("/info/add")
    public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = this.getSessionUser(request);
        if (user == null) {
            return LOGIN_URL;
        }

        if (!checkRole(user)) {
            return ERROR_ROLE;
        }

        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        Author author = new Author();

        model.put("author", author);
        model.put("categoryList", categoryList);
        return getForward(request, response, "story/author/edit/author_info_edit_index.ftl");
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public void commit(HttpServletRequest request, HttpServletResponse response, Author author, String storyIds,
            ModelMap model) {
        Map<String, String> map = new HashMap<String, String>();
        User user = this.getSessionUser(request);
        if (user == null) {
            return;
        }
        if (!checkRole(user)) {
            return;
        }

        if (author != null) {
            if (StringUtils.isEmpty(author.getNameen())) {
                author.setNameen(PinUtil.getPyByCn(author.getName()));
            }
            
            if(StringUtils.isEmpty(author.getNamezh())){
                author.setNamezh(author.getName());
            }
            
            if(StringUtils.isEmpty(author.getCountry())){
                author.setCountry("中国");
            }
            
            author.setStatus("0");
            author.setOutline(author.getOutline().trim());
            author.setDescription(author.getDescription().trim());
        }
        Integer authorId = null;
        try{
            authorId = authorService.save(author);    
        }catch(Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
        }
        
        String msg = "";
        
        if (authorId != null && StringUtils.isNotEmpty(storyIds)) {
            String[] storyIdArr = storyIds.split(",");
            if (storyIdArr != null && storyIdArr.length > 0) {
                Integer storyId = null;
                for (String str : storyIdArr) {
                    if (str.startsWith("+")) {

                        StoryInfo storyInfo = storyInfoService.loadById(str.substring(1));
                        if (storyInfo == null) {
                            msg += str.substring(1) + " 小说不存在,";
                        } else {
                            storyId = Integer.parseInt(str.substring(1));

                            AuthorDetail authorDetail = authorDetailService
                                    .queryByAuthorIdAndStoryId(authorId, storyId);
                            if (authorDetail == null) {
                                AuthorDetail tempAuthorDetail = new AuthorDetail();
                                this.buildExtCreatorPara(request, tempAuthorDetail);
                                tempAuthorDetail.setAuthorId(authorId);
                                tempAuthorDetail.setStoryId(storyId);

                                tempAuthorDetail.setTitle(storyInfo.getTitle());
                                tempAuthorDetail.setOutline(storyInfo.getOutline());

                                authorDetailService.insert(tempAuthorDetail);
                            } else {
                                msg += storyId + " 已经存在,";
                            }
                        }

                    } else if (str.startsWith("-")) {
                        storyId = Integer.parseInt(str.substring(1));
                        authorDetailService.deleteByAuthorIdAndStoryId(authorId, storyId);
                    } else {
                        msg += str + " 数据格式不对,";
                    }
                }
            }
        }
        if(authorId == null){
            map.put("status", "0");
            map.put("msg", "保存失败,请稍后再试.");
        }else{
            map.put("status", "1");
            map.put("authorId", authorId + "");
            map.put("msg", "基本信息保存成功." + msg);
        }

        String json = JSONObject.toJSONString(map);
        
        this.ajaxJson(response, json);
    }
}
