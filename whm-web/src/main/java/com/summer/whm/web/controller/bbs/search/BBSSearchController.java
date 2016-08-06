package com.summer.whm.web.controller.bbs.search;

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
import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.sys.Tag;
import com.summer.whm.service.ask.AnswerService;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.search.SearchService;
import com.summer.whm.service.sys.TagService;
import com.summer.whm.web.common.utils.Constants;

@Controller
@RequestMapping("/bbs")
public class BBSSearchController {
    private static final Logger LOG = LoggerFactory.getLogger(BBSSearchController.class);
    public static final Integer TOP_5 = 5;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SearchService searchService;

    @RequestMapping("/searchIndex")
    public String listIndex(HttpServletRequest request, HttpServletResponse response, String keyword,
            @RequestParam(defaultValue = "1") int cp, ModelMap model) {
        return index(request, response, keyword, cp, model);
    }

    @RequestMapping("/search")
    public String index(HttpServletRequest request, HttpServletResponse response, String keyword,
            @RequestParam(defaultValue = "1") int cp, ModelMap model) {
        LOG.info(keyword);
        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_ASK);
        List<Tag> tagList = tagService.queryByIsSysAndSite(Constants.IS_STR_TRUE, Constants.SITE_ID_ASK);
        List<Answer> answerList = answerService.queryTopNum(TOP_5);
        PageModel<MapContainer> page = null;
        if (StringUtils.isNotEmpty(keyword)) {
            page = searchService.search(keyword, cp);
        }
        model.put("page", page);
        model.put("tagList", tagList);
        model.put("categoryList", categoryList);
        model.put("answerList", answerList);

        model.put("keyword", keyword);
        model.put("listUrl", "/search.html?keyword=" + keyword + "&cp=");
        return "search/search_index.ftl";
    }
}
