package com.summer.whm.service.sys;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.JsoupUtils;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.mapper.ask.QuestionMapper;
import com.summer.whm.mapper.blog.BlogPostMapper;
import com.summer.whm.mapper.post.TopicMapper;
import com.summer.whm.service.sys.model.BaiduZhannei;

@Service
public class BaiduZhanneiSitemapService {
    private final Logger log = LoggerFactory.getLogger(BaiduZhanneiSitemapService.class);
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private BlogPostMapper blogPostMapper;
    
    private static SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    
//    private static SimpleDateFormat SDF_YYYY_MM_DD_HH_MM = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
    
    public Map<String, List<BaiduZhannei>> buildSitemap() {
        Map<String, List<BaiduZhannei>> map = new HashMap<String, List<BaiduZhannei>>();
        map.put(GlobalConfigHolder.DOMAIN_TYPE_QUESTION, buildQuestion());
        map.put(GlobalConfigHolder.DOMAIN_TYPE_TOPIC, buildTopic(GlobalConfigHolder.SITEMAP_COUNT));
        map.put(GlobalConfigHolder.DOMAIN_TYPE_BLOG, buildBlog());

        return map;
    }

    public List<BaiduZhannei> buildQuestion() {
        List<Question> list = questionMapper.queryLatestTopNum(GlobalConfigHolder.SITEMAP_COUNT);
        List<BaiduZhannei> questionList = new ArrayList<BaiduZhannei>();
        String tempExcerpt = null;
        String url = GlobalConfigHolder.DOMAIN_URL + "/q/detail/%d.html";
        String loc = null;
        if (list != null && list.size() > 0) {
            for (Question question : list) {
                tempExcerpt = JsoupUtils.plainText(question.getQuestionContent());
                if(tempExcerpt == null){
                    tempExcerpt = question.getQuestionContent();
                }
                loc = String.format(url, question.getId());
                
                BaiduZhannei zhannei = new BaiduZhannei(loc, SDF_YYYY_MM_DD.format(new Date()), 
                        question.getQuestionTitle(), tempExcerpt, question.getTagName());
                
                questionList.add(zhannei);
            }
        }
        
        return questionList;
    }

    public List<BaiduZhannei> buildTopic(int count, int size) {
        PageModel<Topic> page = new PageModel<Topic>(count, size);
        List<Topic> list = topicMapper.listAll(page);
        List<BaiduZhannei> topicList = new ArrayList<BaiduZhannei>();
        String url = GlobalConfigHolder.DOMAIN_URL + "/t/detail/%d.html";
        String tempExcerpt = null;
        String loc = null;
        if (list != null && list.size() > 0) {
            for (Topic topic : list) {
                if(topic.getContent() != null){
                    tempExcerpt = JsoupUtils.plainText(topic.getContent());
                    if(tempExcerpt == null){
                        tempExcerpt = topic.getContent();
                    }
                    loc = String.format(url, topic.getId());
                    
                    BaiduZhannei zhannei = new BaiduZhannei(loc, SDF_YYYY_MM_DD.format(new Date()), 
                            topic.getTitle(), tempExcerpt, topic.getKeywords());
                    
                    topicList.add(zhannei);
                }
            }
        }
        return topicList;
    }

    public List<BaiduZhannei> buildTopic(int count) {
        List<Topic> list = topicMapper.queryTopTopic(count);
        List<BaiduZhannei> topicList = new ArrayList<BaiduZhannei>();
        String url = GlobalConfigHolder.DOMAIN_URL + "/t/detail/%d.html";
        String tempExcerpt = null;
        String loc = null;
        if (list != null && list.size() > 0) {
            for (Topic topic : list) {
                tempExcerpt = JsoupUtils.plainText(topic.getContent());
                if(tempExcerpt == null){
                    tempExcerpt = topic.getContent();
                }
                loc = String.format(url, topic.getId());
                
                BaiduZhannei zhannei = new BaiduZhannei(loc, SDF_YYYY_MM_DD.format(new Date()), 
                        topic.getTitle(), tempExcerpt, topic.getKeywords());
                
                topicList.add(zhannei);
            }
        }
        return topicList;
    }
    
    public List<BaiduZhannei> buildBlog() {
        List<BlogPost> list = blogPostMapper.queryLatestTopNum(GlobalConfigHolder.SITEMAP_COUNT);
        List<BaiduZhannei> blogList = new ArrayList<BaiduZhannei>();
        String url = GlobalConfigHolder.DOMAIN_URL + "/b/detail/%d.html";
        String tempExcerpt = null;
        String loc = null;
        if (list != null && list.size() > 0) {
            for (BlogPost blog : list) {
                tempExcerpt = JsoupUtils.plainText(blog.getContent());
                if(tempExcerpt == null){
                    tempExcerpt = blog.getContent();
                }
                loc = String.format(url, blog.getId());
                
                BaiduZhannei zhannei = new BaiduZhannei(loc, SDF_YYYY_MM_DD.format(new Date()), 
                        blog.getTitle(), tempExcerpt, blog.getTagName());
                
                blogList.add(zhannei);
            }
        }
        return blogList;
    }

    public Document buildBaiduZNXMLDoc() {
        log.info("百度产内搜索的xml");
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("urlset");
        Map<String, List<BaiduZhannei>> map = buildSitemap();
        for (Map.Entry<String, List<BaiduZhannei>> entry : map.entrySet()) {
            for (BaiduZhannei zhannei : entry.getValue()) {
                writerElement(rootElement, zhannei);
            }
        }
        return document;
    }

    public Document buildBaiduZNTopicXMLDoc(int index) throws IOException {
        log.info("百度产内搜索的xml");
        XMLWriter xmlWriter = null;
        for(int i = 1; i < index; i++){
            Document document = DocumentHelper.createDocument();
            Element rootElement = document.addElement("urlset");
            List<BaiduZhannei> baiduZhanneiList =  buildTopic(i, 500);
            for (BaiduZhannei zhannei : baiduZhanneiList) {
                writerElement(rootElement, zhannei);
            }
            String file = GlobalConfigHolder.BASE_STATIC_PATH + "/baiduzn/" + i + ".xml";
            FileOutputStream fos = new FileOutputStream(file);
            xmlWriter = new XMLWriter(fos);
            xmlWriter.write(document);
            xmlWriter.close();
            xmlWriter = null;
        }
        return null;
    }

    private void writerElement(Element rootElement, BaiduZhannei zhannei) {
        Element titleElement = rootElement.addElement("url");
        Element element = titleElement.addElement("loc");
        element.setText(zhannei.getLoc());
        element = titleElement.addElement("lastmod");
        element.setText(zhannei.getLastmod());
        element = titleElement.addElement("changefreq");
        element.setText("always");
        element = titleElement.addElement("priority");
        element.setText("0.7");
        Element dataElement = titleElement.addElement("data");
        Element displayElement = dataElement.addElement("display");
        
        element = displayElement.addElement("title");
        element.setText(zhannei.getTitle());
        
        element = displayElement.addElement("content");
        element.setText(zhannei.getContent());
        int count = 0;
        if(zhannei.getTag() != null){
            String[] tags = zhannei.getTag().split(",");
            for(String tag : tags){
                element = displayElement.addElement("tag");
                element.setText(tag);
                count++;
                if(count > 5){
                    break;
                }
            }
        }
        
//        element = displayElement.addElement("pubTime");
//        element.setText(zhannei.getPubTime());
    }
    
    public static void main(String[] args){
    }
}
