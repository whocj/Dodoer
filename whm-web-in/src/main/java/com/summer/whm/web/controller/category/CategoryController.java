package com.summer.whm.web.controller.category;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.category.CategoryUser;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.category.CategoryUserService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryUserService categoryUserService;
    
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response) {
        return "frame/category_main.ftl";
    }

    @RequestMapping("/left")
    public String left(HttpServletRequest request, HttpServletResponse response) {
        return "frame/category_left.ftl";
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage, ModelMap model) {
        PageModel<Category> page = categoryService.list(currentPage, WebConstants.PAGE_SIZE);
        model.put("page", page);
        return "category/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        Category category = categoryService.loadById(id + "");
        model.put("category", category);
        LOG.info("edit Category id={}", id);
        return "category/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("category", new Category());
        return "category/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete Category id={}", ids);
        categoryService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Category category) {
        LOG.info("submit Category {}", category);
        if (category != null && category.isNew()) {
            category.setLastUpdate(new Date());
            category.setCreateTime(new Date());
            categoryService.insert(category);
        } else {
            List<CategoryUser> categoryUserList = categoryUserService.queryByCategoryId(category.getId());
            StringBuffer sb = new StringBuffer();
            for(CategoryUser categoryUser : categoryUserList){
                sb.append(categoryUser.getUsername()).append(",");
            }
            String str = sb.toString();
            if(str.endsWith(",")){
                str = str.substring(0, str.length() - 1);
                category.setModerators(str);
            }
            category.setLastUpdate(new Date());
            categoryService.update(category);
        }

        return "redirect:list.htm";
    }

    @RequestMapping("/addUser")
    public String addUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        LOG.info("addUser Category's moderators categoryId={}", id);
        Category category = categoryService.loadById(id + "");

        List<CategoryUser> categoryUserList = categoryUserService.queryByCategoryId(id);

        if (categoryUserList != null) {
            category.setCategoryUserList(categoryUserList);
        }
        model.put("showUser", "1");
        model.put("category", category);
        return "category/edit.ftl";
    }

    @RequestMapping("/submitUser")
    public String submitUser(HttpServletRequest request, HttpServletResponse response, CategoryUser categoryUser ,
            ModelMap model) {
        LOG.info("addUser CategoryUser CategoryUser={}", categoryUser);
        
        buildExtCreatorPara(request, categoryUser);
        categoryUserService.insert(categoryUser);
        
        Category category = categoryService.loadById(categoryUser.getCategoryId() + "");
        List<CategoryUser> categoryUserList = categoryUserService.queryByCategoryId(categoryUser.getCategoryId());
        
        if (categoryUserList != null) {
            category.setCategoryUserList(categoryUserList);
        }
        model.put("showUser", "1");
        model.put("category", category);
        return "category/edit.ftl";
    }
    
    @RequestMapping("/cleanUser")
    public String cleanUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id) {
        LOG.info("cleanUser Category's moderators categoryId={}", id);
        categoryUserService.cleanByCategoryId(id);
        return "redirect:list.htm";
    }

    @RequestMapping("/deleteCategoryUserById")
    public String deleteCategoryUserById(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "categoryUserId") int categoryUserId,
            @RequestParam(value = "categoryId") int categoryId) {
        LOG.info("deleteCategoryUserById Category's moderators categoryUserId={}", categoryUserId);
        categoryUserService.deleteById(categoryUserId + "");
        return "redirect:addUser.htm?id=" + categoryId;
    }
}
