package com.summer.whm.web.controller.about;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.service.ask.AnswerService;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/about/contact")
public class ContactController extends BaseController {
    
    public static final Integer TOP_5 = 5;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AnswerService answerService;
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Answer> answerList = answerService.queryTopNum(TOP_5);

        model.put("categoryList", categoryList);
        model.put("answerList", answerList);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_MORE_CODE);
        return "about/contact/contact_index.ftl";
    }
    
    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Answer> answerList = answerService.queryTopNum(TOP_5);

        model.put("categoryList", categoryList);
        model.put("answerList", answerList);
        
        return "about/contact/contact_index.ftl";
    }
}
