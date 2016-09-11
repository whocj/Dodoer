package com.summer.whm.web.controller.story;

import java.util.List;

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
        List<Author> authorList = null;
        if (cid == 0) {
            authorList = authorService.queryAll(null);
        } else {
            authorList = authorService.queryAll(cid);
        }
        String[] abcArr = Constants.ABC_ARRAY;
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        
        model.put("abcArr", abcArr);
        model.put("categoryList", categoryList);
        model.put("authorList", authorList);
        categoryService.queryBySite(Constants.SITE_ID_STORY_AUTHOR);
        return getForward(request, response, "story/author/list/author_list_index.ftl");
    }

    @RequestMapping("/info/{id}")
    public String info(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        Author author = authorService.queryById(id);
        model.put("author", author);
        return getForward(request, response, "story/author/info/author_info_index.ftl");
    }
}
