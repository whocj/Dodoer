package com.summer.whm.web.controller.ask.question;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.ask.AnswerService;
import com.summer.whm.service.ask.QuestionService;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/ask/question")
public class QuestionController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
    public static final Integer TOP_5 = 5;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/listIndex/{currentPage}")
    public String listIndex(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("currentPage") int currentPage, ModelMap model) {
        return list(request, response, currentPage, model);
    }

    @RequestMapping("/list/{currentPage}")
    public String list(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("currentPage") int currentPage, ModelMap model) {
        if (currentPage < 1) {
            currentPage = 1;
        }
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        String categoryId = request.getParameter("categoryId");
        PageModel<Question> page = null;
        try {
            if (categoryId != null) {
                page = questionService.list(Integer.parseInt(categoryId), currentPage, WebConstants.PAGE_SIZE);
            }
        } catch (Exception e) {
            categoryId = null;
            LOG.error("目录为空.", e);
        }

        if (categoryId == null) {
            page = questionService.list(currentPage, WebConstants.PAGE_SIZE);
        }

        List<Answer> answerList = answerService.queryTopNum(TOP_5);

        model.put("page", page);
        model.put("tagList", tagList);
        model.put("categoryList", categoryList);
        model.put("answerList", answerList);
        model.put("listUrl", "/ask/question/list/");
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_QUESTION_CODE);
        return "ask/question/list/question_index.ftl";
    }

    @RequestMapping("/listIndex/category/{categoryId}")
    public String category(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("categoryId") int categoryId, ModelMap model) {
        return listCategory(request, response, categoryId, model);
    }

    @RequestMapping("/list/category/{categoryId}")
    public String listCategory(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("categoryId") int categoryId, ModelMap model) {
        int currentPage = 1;
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        PageModel<Question> page = null;
        try {
            page = questionService.list(categoryId, currentPage, WebConstants.PAGE_SIZE);
        } catch (Exception e) {
            LOG.error("目录为空.", e);
        }

        List<Answer> answerList = answerService.queryTopNum(TOP_5);

        model.put("page", page);
        model.put("tagList", tagList);
        model.put("categoryList", categoryList);
        model.put("answerList", answerList);
        model.put("listUrl", "/ask/question/list/");
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_QUESTION_CODE);
        return "ask/question/list/question_index.ftl";
    }

    @RequestMapping("/ask")
    public String ask(HttpServletRequest request, HttpServletResponse response, Integer id, Question question,
            ModelMap model) {
        
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }
        
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        List<Answer> answerList = answerService.queryTopNum(TOP_5);
        List<Question> questionList = questionService.queryLatestTopNum(TOP_5);

        model.put("tagList", tagList);
        model.put("categoryList", categoryList);
        model.put("answerList", answerList);
        model.put("questionList", questionList);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_ASK_CODE);
        return "ask/question/ask/ask_index.ftl";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, Integer id, Question question) {
        LOG.info("submit Question {}", question);
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }
        question.setUserId(user.getId());
        question.setUsername(user.getUsername());

        buildExtCreatorPara(request, question);
        questionService.save(question);

        return "redirect:detail/" + question.getId() + ".html";
    }

   
    
    @RequestMapping(value = "/singleIndex/{id}", method = RequestMethod.GET)
    public String singleIndex(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id,
            ModelMap model) {
        return detail(request, response, id, model);
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Integer id,
            ModelMap model) {

        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        List<Answer> answerList = answerService.queryTopNum(TOP_5);
        Question question = questionService.queryById(id);

        model.put("tagList", tagList);
        model.put("categoryList", categoryList);
        model.put("answerList", answerList);
        model.put("question", question);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_QUESTION_CODE);
        return "ask/question/single/question_index.ftl";
    }

    @RequestMapping("/singleReply")
    public String singleReply(HttpServletRequest request, HttpServletResponse response, Answer answer, ModelMap model) {
        return reply(request, response, answer, model);
    }

    @RequestMapping("/reply")
    public String reply(HttpServletRequest request, HttpServletResponse response, Answer answer, ModelMap model) {
        LOG.info("submit Answer {}", answer);
        
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }
        answer.setUserId(user.getId());
        answer.setAskName(user.getUsername());
        answer.setAskEmail(user.getEmail());

        buildExtCreatorPara(request, answer);
        answerService.save(answer);
        model.put(Constants.CURRENT_MENU_CODE, Constants.MENU_QUESTION_CODE);
        return "redirect:detail/" + answer.getQuestionId() + ".html";
    }
}
