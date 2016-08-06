package com.summer.whm.web.controller.search;

import java.util.List;

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

import com.summer.whm.common.model.MapContainer;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.blog.BlogPostService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.SearchService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Configs;
import com.summer.whm.web.common.utils.Constants;

@Controller
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);
    public static final Integer TOP_5 = 5;

    @Autowired
    private TagService tagService;

    @Autowired
    private SearchService searchService;

    @Autowired
    private TopicService topicService; 
    
    @Autowired
    private BlogPostService blogPostService;
    
    @RequestMapping("/search")
    public String listIndex(HttpServletRequest request, HttpServletResponse response, String keyword,
            @RequestParam(defaultValue = "") String category, @RequestParam(defaultValue = "1") int currentPage,
            ModelMap model) {
        LOG.info(keyword);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        PageModel<MapContainer> page = null;
        if (StringUtils.isNotEmpty(keyword)) {
            if(StringUtils.isNotEmpty(category)){
                page = searchService.searchByCategory(keyword, category, currentPage);
            }else{
                page = searchService.search(keyword, currentPage);
            }
        }else{
            page = new PageModel<MapContainer>(1, 15);
        }

        if(Configs.SITE_BBS_ENABLE){
            List<Topic> topicList = topicService.queryTopTopic(TOP_5);
            model.put("topicList", topicList);
        }

        if(Configs.SITE_BLOG_ENABLE){
            List<BlogPost> blogPostList = blogPostService.queryLatestTopNum(TOP_5);
            model.put("blogPostList", blogPostList);
        }
        
        model.put("page", page);
        model.put("tagList", tagList);

        model.put("keyword", keyword);
        model.put("listUrl", "/search.html?keyword=" + keyword + "&currentPage=");
        return "search/search_index.ftl";
    }
}
