package com.summer.whm.service.sys.sitemap.story;

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
import com.summer.whm.mapper.story.StoryInfoMapper;

@Service
public class StorySitemapService {
    private final Logger log = LoggerFactory.getLogger(StorySitemapService.class);

    @Autowired
    private StoryInfoMapper storyInfoMapper;

    public Map<String, List<String>> buildSitemap(int count, boolean isMobile) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put(GlobalConfigHolder.DOMAIN_TYPE_STORY, buildStoryUrl(count, isMobile));
        return map;
    }

    public List<String> buildStoryUrl(int count,boolean isMobile) {
        List<Integer> list = storyInfoMapper.queryIdByCount(count);
        List<String> urlList = new ArrayList<String>();
        
        String url = null;
        if(isMobile){
            url = GlobalConfigHolder.DODOER_M_DOMAIN_URL + "/main/%d.html";
        }else{
            url = GlobalConfigHolder.DODOER_DOMAIN_URL + "/main/%d.html";
        }
        
        if (list != null && list.size() > 0) {
            for (Integer id : list) {
                urlList.add(String.format(url, id));
            }
        }
        return urlList;
    }

    public Document buildXMLDoc(int count, boolean isMobile) {
        log.info("生产列表页面sitemap的xml");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("urlset", "http://www.sitemaps.org/schemas/sitemap/0.9");
        Map<String, List<String>> map = buildSitemap(count, isMobile);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String url : entry.getValue()) {
                try{
                    writerElement(rootElement, url, date);    
                }catch(Exception e){
                    log.error("sitemap生成错误，", e);
                }
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
