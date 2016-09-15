package com.summer.whm.web.controller.story;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.Constants;
import com.summer.whm.entiry.author.Author;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.service.author.AuthorService;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/author")
public class AuthorController extends BaseController {

    @Autowired
    public AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list/{cid}")
    public String list(HttpServletRequest request, HttpServletResponse response, @PathVariable("cid") int cid,
            ModelMap model) {
        Map<String, List<Author>> authorMap = null;
        Category category = new Category();
        if (cid == 0) {
            authorMap =  authorService.queryAllByGroupNamePrefix(null);
        } else {
             category = categoryService.loadById(cid + "");
            if(category == null){
                authorMap =  authorService.queryAllByGroupNamePrefix(null);
            }else{
                authorMap = authorService.queryAllByGroupNamePrefix(cid);
            }
        }
        String[] abcArr = Constants.ABC_ARRAY;
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        
        model.put("category", category);
        model.put("abcArr", abcArr);
        model.put("categoryList", categoryList);
        model.put("authorMap", authorMap);
        categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        return getForward(request, response, "story/author/list/author_list_index.ftl");
    }

    @RequestMapping("/info/{id}")
    public String info(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        Author author = authorService.queryById(id);
        if(author == null){
            return ERROR;
        }
        model.put("author", author);
        return getForward(request, response, "story/author/info/author_info_index.ftl");
    }
    
}
