package com.summer.whm.service.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.mapper.ask.QuestionMapper;
import com.summer.whm.mapper.blog.BlogPostMapper;
import com.summer.whm.mapper.post.TopicMapper;

@Service
public class SitemapService {
    private final Logger log = LoggerFactory.getLogger(SitemapService.class);
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private BlogPostMapper blogPostMapper;

    public Map<String, List<String>> buildSitemap(int count) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put(GlobalConfigHolder.DOMAIN_TYPE_QUESTION, buildQuestionUrl(count));
        map.put(GlobalConfigHolder.DOMAIN_TYPE_TOPIC, buildTopicUrl(count));
        map.put(GlobalConfigHolder.DOMAIN_TYPE_BLOG, buildBlogUrl(count));

        return map;
    }

    public List<String> buildQuestionUrl(int count) {
        List<Integer> list = questionMapper.queryIdByCount(count);
        List<String> urlList = new ArrayList<String>();
        String url = GlobalConfigHolder.DOMAIN_URL + "/q/detail/%d.html";
        if (list != null && list.size() > 0) {
            for (Integer id : list) {
                urlList.add(String.format(url, id));
            }
        }
        return urlList;
    }

    public List<String> buildTopicUrl(int count) {
        List<Integer> list = topicMapper.queryIdByCount(count);
        List<String> urlList = new ArrayList<String>();
        String url = GlobalConfigHolder.DOMAIN_URL + "/t/detail/%d.html";
        if (list != null && list.size() > 0) {
            for (Integer id : list) {
                urlList.add(String.format(url, id));
            }
        }
        return urlList;
    }

    public List<String> buildBlogUrl(int count) {
        List<Integer> list = blogPostMapper.queryIdByCount(count);
        List<String> urlList = new ArrayList<String>();
        String url = GlobalConfigHolder.DOMAIN_URL + "/b/detail/%d.html";
        if (list != null && list.size() > 0) {
            for (Integer id : list) {
                urlList.add(String.format(url, id));
            }
        }
        return urlList;
    }

    public Document buildXMLDoc(int count) {
        log.info("生产列表页面sitemap的xml");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("urlset", "http://www.sitemaps.org/schemas/sitemap/0.9");
        Map<String, List<String>> map = buildSitemap(count);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String url : entry.getValue()) {
                writerElement(rootElement, url, date);
            }
        }
        return document;
    }

    private void writerElement(Element rootElement, String url, String date) {
        Element titleElement = rootElement.addElement("url");
        Element element = titleElement.addElement("loc");
        element.setText(url);
        element = titleElement.addElement("lastmod");
        element.setText(date);
        element = titleElement.addElement("changefreq");
        element.setText("daily");
        element = titleElement.addElement("priority");
        element.setText("0.7");
    }
}
