package com.summer.whm.spider.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.spider.CrawLog;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.post.PostService;
import com.summer.whm.service.post.PostTextService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.SearchPostService;
import com.summer.whm.spider.SpiderContext;
import com.summer.whm.spider.crawl.Crawl;
import com.summer.whm.spider.filter.StringFilter;

@Service
public class CrawlService {

    @Autowired
    private TopicService topicService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostTextService postTextService;

    @Autowired
    private CrawInfoService crawInfoService;

    @Autowired
    private CrawLogService crawLogService;

    @Autowired
    private CrawTemplateService crawTemplateService;
    
    @Autowired
    private SearchPostService searchPostService;

    private static final Map<Integer, Crawl> crawlMap = new HashMap<Integer, Crawl>();

    public boolean canStart(Integer crawTemplateId) {
        Crawl crawl = crawlMap.get(crawTemplateId);

        if (crawl != null) {
            SpiderContext spiderContext = crawl.getSpiderContext();
            if (spiderContext != null) {
                if (!spiderContext.isDone()) {
                    return false;
                }
            }
        }

        return true;
    }

    public void start(Integer crawTemplateId, User user) {
        CrawTemplate crawTemplate = crawTemplateService.queryById(crawTemplateId);

        CrawLog crawLog = new CrawLog();
        crawLog.setBeginTime(new Date());
        crawLog.setCreator(user.getUsername());
        crawLog.setStatus(CrawTemplateService.STATUS_RUN);
        crawLog.setCrawTemplateId(crawTemplateId);
        crawLogService.insert(crawLog);

        SpiderContext spiderContext = new SpiderContext();
        
        if(StringUtils.isNotEmpty(crawTemplate.getFilterWord())){
            StringFilter sf = new StringFilter(crawTemplate.getFilterWord());
            spiderContext.setStringFilter(sf);
        }
        
        spiderContext.setCrawLog(crawLog);
        spiderContext.setCrawTemplate(crawTemplate);
        spiderContext.setListTemplate(crawTemplate.getListTemplate());
        spiderContext.setDetailTemplate(crawTemplate.getDetailTemplate());
        
        Crawl crawl = new Crawl(spiderContext, topicService, postService, postTextService, crawInfoService, searchPostService);
        crawl.start();
        crawTemplate.setStatus(CrawTemplateService.STATUS_RUN);
        crawTemplateService.update(crawTemplate);
        
        crawlMap.put(crawTemplateId, crawl);
    }
    
    public void pause(Integer crawTemplateId, User user) {
        Crawl crawl  = crawlMap.get(crawTemplateId);
        if(crawl != null){
            crawl.pause();
        }
    }
    
    public void stop(Integer crawTemplateId, User user) {
        Crawl crawl  = crawlMap.get(crawTemplateId);
        if(crawl != null){
            crawl.stop();
        }
    }
    
    public void reStart(Integer crawTemplateId, User user) {
        Crawl crawl  = crawlMap.get(crawTemplateId);
        if(crawl != null){
            crawl.reStart();
        }
    }
}
