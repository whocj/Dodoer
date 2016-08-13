package com.summer.whm.web.controller.tdf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.JsoupUtils;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.post.PostText;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.service.analyzer.tf.IKAnalyzerService;
import com.summer.whm.service.ask.QuestionService;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.post.PostTextService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.model.PostType;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.search.AssociateWordController;

@Controller
@RequestMapping("/tf")
public class TFController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(AssociateWordController.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostTextService postTextService;
    
    @Autowired
    private BlogPostService blogPostService;
    
    @RequestMapping("/exec")
    public void exec(HttpServletRequest request, HttpServletResponse response, String type) {
        LOG.info("关键词提取");
        try {
            String keywords = null;
            String description = null;
            if (PostType.POST_TYPE_QUESTION.equals(type)) {
                PageModel<Question> page = questionService.list(1, 99999);
                if (page != null && page.getContent() != null && page.getContent().size() > 0) {
                    for (Question q : page.getContent()) {
                        String tempExcerpt = JsoupUtils.plainText(q.getQuestionContent());

                        if (tempExcerpt != null) {
                            if (tempExcerpt.length() > 100) {
                                keywords = IKAnalyzerService.getTopTerm(tempExcerpt);
                                if (keywords == null) {
                                    keywords = q.getQuestionTitle();
                                }
                                keywords = keywords + "," + q.getTagName();
                            } else {
                                keywords = q.getTagName() + "," + q.getQuestionTitle();
                            }

                            if (tempExcerpt.length() > 200) {
                                description = tempExcerpt.substring(0, 180) + "...";
                            } else {
                                description = tempExcerpt;
                            }
                        } else {
                            keywords = q.getTagName() + q.getQuestionTitle();
                            description = q.getQuestionTitle() + q.getQuestionContent();
                        }
                        q.setKeywords(keywords);
                        q.setDescription(description);
                        questionService.save(q);
                    }
                }
            } else if (PostType.POST_TYPE_BLOG.equals(type)) {
                PageModel<BlogPost> page = new PageModel<BlogPost>(1, 99999);
                page = blogPostService.queryHotBlogPost(page);
                if (page != null && page.getContent() != null && page.getContent().size() > 0) {
                    for (BlogPost p : page.getContent()) {
                        String tempExcerpt = p.getContentText();
                        if(tempExcerpt == null){
                            tempExcerpt = JsoupUtils.plainText(p.getContent());
                        }
                        
                        if (tempExcerpt != null) {
                            if (tempExcerpt.length() > 100) {
                                keywords = IKAnalyzerService.getTopTerm(tempExcerpt);
                                if (keywords == null) {
                                    keywords = p.getTitle();
                                }
                                keywords = keywords + "," + p.getTagName();
                            } else {
                                keywords = p.getTagName() + "," + p.getTitle();
                            }

                            if (tempExcerpt.length() > 200) {
                                description = tempExcerpt.substring(0, 180) + "...";
                            } else {
                                description = tempExcerpt;
                            }
                        } else {
                            keywords = p.getTagName() + p.getTitle();
                            if(p.getContent() != null && p.getContent().length() > 180){
                                description = p.getContent().substring(0, 180);
                            }else{
                                description = p.getContent();
                            }
                        }
                        p.setKeywords(keywords);
                        p.setDescription(description);
                        blogPostService.save(p);
                    }
                }
            } else if (PostType.POST_TYPE_TOPIC.equals(type)) {
                PageModel<Topic> page = new PageModel<Topic>(1, 99999);
                page = topicService.queryAll(page);
                if (page != null && page.getContent() != null && page.getContent().size() > 0) {
                    for (Topic t : page.getContent()) {
                        PostText postText = postTextService.loadById(t.getFirstPostId() + "");
                        if(postText != null){
                            String tempExcerpt = JsoupUtils.plainText( postText.getPostContent());
                            
                            if (tempExcerpt.length() > 100) {
                                keywords = IKAnalyzerService.getTopTerm(tempExcerpt);
                                if (keywords == null) {
                                    keywords = t.getTitle();
                                }
                            } else {
                                keywords = t.getTitle();
                            }

                            if (tempExcerpt.length() > 200) {
                                description = tempExcerpt.substring(0, 180) + "...";
                            } else {
                                description = tempExcerpt;
                            }
                            
                        }else{
                            keywords = t.getTitle();
                            description = t.getTitle();
                        }
                        t.setKeywords(keywords);
                        t.setDescription(description);
                        topicService.save(t);
                    }
                }
            }
            ajaxHtml(response, SUCCESS_STR);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }
}
