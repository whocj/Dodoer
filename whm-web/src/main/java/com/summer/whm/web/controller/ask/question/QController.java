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
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.MapContainer;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.ask.AnswerService;
import com.summer.whm.service.ask.QuestionService;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.SimilarSearchService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Configs;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/q")
public class QController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(QController.class);
    public static final Integer TOP_5 = 5;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private TagService tagService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SimilarSearchService similarSearchService;
    
    @Autowired
    private TopicService topicService; 

    @Autowired
    private BlogPostService blogPostService;
    
    
    @RequestMapping("/listIndex/{currentPage}")
    public String listIndex(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("currentPage") int currentPage, ModelMap model) {
        return list(request, response, currentPage, 0, "new", model);
    }

    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(defaultValue = "1") int cp, @RequestParam(defaultValue = "0") int cid, 
            @RequestParam(defaultValue = "new") String type, ModelMap model) {

        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        PageModel<Question> page = null;
        try {
            if (cid != 0) {
                model.put("listUrl", "/q/list.html?cid=" + cid + "&cp=");
                page = questionService.list(cid, cp, WebConstants.PAGE_SIZE);
            } else {
                if("hot".equals(type)){
                    page = questionService.listOrderByHot(cp, WebConstants.PAGE_SIZE);
                    model.put("listUrl", "/q/list.html?type=" + type + "&cp=");
                }else if("no".equals(type)){
                    page = questionService.listByNotAnswer(cp, WebConstants.PAGE_SIZE);
                    model.put("listUrl", "/q/list.html?type=" + type + "&cp=");
                }else{
                    page = questionService.list(cp, WebConstants.PAGE_SIZE);
                    model.put("listUrl", "/q/list.html?cp=");
                }
            }
        } catch (Exception e) {
            LOG.error("目录为空.", cid, e);
        }

        if(Configs.SITE_BBS_ENABLE){
            List<Topic> topicList = topicService.queryTopTopic(TOP_5);
            model.put("topicList", topicList);
        }

        if(Configs.SITE_BLOG_ENABLE){
            List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(TOP_5);
            model.put("blogPostList", blogPostList);
        }
        
        model.put("type", type);
        model.put("page", page);
        model.put("tagList", tagList);

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
        List<Question> questionList = questionService.queryLatestTopNum(TOP_5);
        
        model.put("categoryList", categoryList);
        if(Configs.SITE_BBS_ENABLE){
            List<Topic> topicList = topicService.queryTopTopic(TOP_5);
            model.put("topicList", topicList);
        }

        if(Configs.SITE_BLOG_ENABLE){
            List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(TOP_5);
            model.put("blogPostList", blogPostList);
        }
        model.put("tagList", tagList);
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

        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        Question question = questionService.queryById(id);

        List<MapContainer> likeList = similarSearchService.searchQuestion(question);
        List<Question> questionList = questionService.queryLatestTopNum(TOP_5);
        
        model.put("questionList", questionList);
        if(Configs.SITE_BBS_ENABLE){
            List<Topic> topicList = topicService.queryTopTopic(TOP_5);
            model.put("topicList", topicList);
        }

        if(Configs.SITE_BLOG_ENABLE){
            List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(TOP_5);
            model.put("blogPostList", blogPostList);
        }

        model.put("likeList", likeList);
        model.put("tagList", tagList);
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

    @RequestMapping("/submitGoodAnswer")
    public String submitGoodAnswer(HttpServletRequest request, HttpServletResponse response,@RequestParam(defaultValue = "0") int qid , 
            @RequestParam(defaultValue = "0") int aid , ModelMap model){
        LOG.info("goodAnswer qid {}, aid {}", qid, aid);
        User user = this.getSessionUser(request);
        // 未登录用户，跳转至登录页面
        if (user == null) {
            LOG.info("操作异常#" + user);
            return LOGIN_URL;
        }
        Question question = questionService.loadById(qid + "");
        if(question != null){
            if(question.getUsername().equals(user.getUsername())){
                questionService.updateQuestionHasGood(qid);
                answerService.updateIsGood(aid);
            }
        }
        return "redirect:detail/" + qid + ".html";
    }
}
