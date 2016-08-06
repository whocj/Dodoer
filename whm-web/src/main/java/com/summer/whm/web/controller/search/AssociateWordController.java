package com.summer.whm.web.controller.search;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.summer.whm.service.search.keyword.associate.AssociateWord;
import com.summer.whm.service.search.keyword.associate.AssociateWordFileService;
import com.summer.whm.service.search.keyword.associate.AssociateWordService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/search/ass")
public class AssociateWordController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(AssociateWordController.class);

    @Autowired
    private AssociateWordFileService associateWordFileService;

    @Autowired
    private AssociateWordService associateWordService;

    @RequestMapping("/iniAssWordFile")
    public void iniAssWordFile(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("initFile");
        try {
            associateWordFileService.initFile();
            ajaxHtml(response, SUCCESS_STR);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }

    @RequestMapping("/initAssWordIndex")
    public void initAssWordIndex(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("initAssWord");
        try {
            associateWordService.reCreateIndex();
            ajaxHtml(response, SUCCESS_STR);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxHtml(response, e.getMessage());
        }
    }

    @RequestMapping("/search")
    public void search(HttpServletRequest request, HttpServletResponse response, String keyword, String callback){
        List<AssociateWord> assList = null;
        try {
            assList = associateWordService.search(keyword);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        
        String str = JSONObject.toJSONString(assList);
        if(StringUtils.isNotEmpty(callback)){
            str = callback + "(" + str + ")";
        }
        
        ajaxJson(response, str);
    }
}
