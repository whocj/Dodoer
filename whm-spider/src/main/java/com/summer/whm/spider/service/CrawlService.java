package com.summer.whm.spider.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.entiry.spider.CrawLog;
import com.summer.whm.entiry.spider.CrawTemplate;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.post.PostService;
import com.summer.whm.service.post.PostTextService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.SearchPostService;
import com.summer.whm.service.stroy.StoryDetailService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryPartService;
import com.summer.whm.spider.SpiderConfigs;
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

    @Autowired
    private StoryInfoService storyInfoService;

    @Autowired
    private StoryPartService storyPartService;

    @Autowired
    private StoryDetailService storyDetailService;

    @Autowired
    private SpiderStoryJobService spiderStoryJobService;

    @Autowired
    private SpiderStoryTemplateService spiderStoryTemplateService;

    private static final Map<String, Crawl> crawlMap = new HashMap<String, Crawl>();

    public boolean canStart(Integer crawTemplateId, String type) {
        Crawl crawl = crawlMap.get(type + "@" + crawTemplateId);

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
        crawLog.setStatus(SpiderConfigs.STATUS_RUN);
        crawLog.setCrawTemplateId(crawTemplateId);
        crawLog.setType(SpiderConfigs.DOMAIN_TYPE_TOPIC);
        crawLogService.insert(crawLog);

        SpiderContext spiderContext = new SpiderContext(SpiderConfigs.DOMAIN_TYPE_TOPIC);

        if (StringUtils.isNotEmpty(crawTemplate.getFilterWord())) {
            StringFilter sf = new StringFilter(crawTemplate.getFilterWord());
            spiderContext.setStringFilter(sf);
        }

        spiderContext.setCrawLog(crawLog);
        spiderContext.setCrawTemplate(crawTemplate);
        spiderContext.setListTemplate(crawTemplate.getListTemplate());
        spiderContext.setDetailTemplate(crawTemplate.getDetailTemplate());

        Crawl crawl = new Crawl(spiderContext, topicService, postService, postTextService, crawInfoService,
                searchPostService);
        crawl.start();
        crawTemplate.setStatus(SpiderConfigs.STATUS_RUN);
        crawTemplateService.update(crawTemplate);

        crawlMap.put(SpiderConfigs.DOMAIN_TYPE_TOPIC + "@" + crawTemplateId, crawl);
    }

    public void startStory(Integer jobId, User user) {

        SpiderStoryJob spiderStoryJob = spiderStoryJobService.loadById(jobId + "");

        SpiderStoryTemplate spiderStoryTemplate = spiderStoryTemplateService.loadById(spiderStoryJob.getTemplateId() + "");

        CrawLog crawLog = new CrawLog();
        crawLog.setBeginTime(new Date());
        crawLog.setCreator(user.getUsername());
        crawLog.setStatus(SpiderConfigs.STATUS_RUN);
        crawLog.setCrawTemplateId(jobId);
        crawLog.setType(SpiderConfigs.DOMAIN_TYPE_STORY);
        crawLogService.insert(crawLog);

        SpiderContext spiderContext = new SpiderContext(SpiderConfigs.DOMAIN_TYPE_STORY);

        if (StringUtils.isNotEmpty(spiderStoryTemplate.getFilterWord())) {
            StringFilter sf = new StringFilter(spiderStoryTemplate.getFilterWord());
            spiderContext.setStringFilter(sf);
        }

        spiderContext.setCrawLog(crawLog);
        spiderContext.setSpiderStoryJob(spiderStoryJob);
        spiderContext.setSpiderStoryTemplate(spiderStoryTemplate);

        Crawl crawl = new Crawl(spiderContext, crawInfoService, searchPostService, storyInfoService, storyPartService,
                storyDetailService, spiderStoryJobService);
        crawl.startStory();
        
        SpiderStoryJob tempSpiderStoryJob = new SpiderStoryJob();
        
        tempSpiderStoryJob.setSpiderStatus(SpiderConfigs.STATUS_RUN);
        tempSpiderStoryJob.setId(spiderStoryJob.getId());
        spiderStoryJobService.update(tempSpiderStoryJob);
        
        crawlMap.put(SpiderConfigs.DOMAIN_TYPE_STORY + "@" + jobId, crawl);
    }

    //启动批量小说抓取任务
    public void startStoryTemplate(Integer templateId, User user) {
        
        System.out.println("#抓取小说模版任务启动templateId=" + templateId);
        SpiderStoryTemplate spiderStoryTemplate = null;
        List<SpiderStoryJob> spiderStoryJobList = spiderStoryJobService.queryByTempateIdAndStatus(templateId, SpiderConfigs.STORY_JOB_STATUS_INIT);
        System.out.println("#准备小说任务 " + spiderStoryJobList.size() + " 条");
        boolean isFristCrawl = false;
        for(SpiderStoryJob spiderStoryJob : spiderStoryJobList){
            
            spiderStoryTemplate = spiderStoryTemplateService.loadById(templateId + "");
            
            System.out.println("#开始处理小说Job" + spiderStoryJob.getTitle());
            isFristCrawl = spiderStoryJob.getStoryId() == null;
            
            CrawLog crawLog = new CrawLog();
            crawLog.setBeginTime(new Date());
            crawLog.setCreator(user.getUsername());
            crawLog.setStatus(SpiderConfigs.STATUS_RUN);
            crawLog.setCrawTemplateId(templateId);
            crawLog.setType(SpiderConfigs.DOMAIN_TYPE_STORY_TEMPLATE);
            crawLogService.insert(crawLog);

            SpiderContext spiderContext = new SpiderContext(SpiderConfigs.DOMAIN_TYPE_STORY);

            if (StringUtils.isNotEmpty(spiderStoryTemplate.getFilterWord())) {
                StringFilter sf = new StringFilter(spiderStoryTemplate.getFilterWord());
                spiderContext.setStringFilter(sf);
            }
            
            spiderContext.setCrawLog(crawLog);
            spiderContext.setSpiderStoryJob(spiderStoryJob);
            spiderContext.setSpiderStoryTemplate(spiderStoryTemplate);

            Crawl crawl = new Crawl(spiderContext, crawInfoService, searchPostService, storyInfoService, storyPartService,
                    storyDetailService, spiderStoryJobService);
            crawl.startStory();
            
            SpiderStoryJob tempSpiderStoryJob = new SpiderStoryJob();
            tempSpiderStoryJob.setSpiderStatus(SpiderConfigs.STATUS_RUN);
            tempSpiderStoryJob.setId(spiderStoryJob.getId());
            spiderStoryJobService.update(tempSpiderStoryJob);
            
            try {
                if(isFristCrawl){
                  //等特50分钟处理下一个任务
                  Thread.sleep(GlobalConfigHolder.SPIDER_CRAWL_SLEEP_TIME * 8);
                }else{
                  //等特30秒处理下一个任务
                  Thread.sleep(GlobalConfigHolder.SPIDER_CRAWL_SLEEP_TIME / 6);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //crawlMap.put(SpiderConfigs.DOMAIN_TYPE_STORY + "@" + spiderStoryJob.getId(), crawl);
        }
        System.out.println("#结束小说任务,共处理  " + spiderStoryJobList.size() + " 条");
        try {
            //等特180秒处理下一个任务
            System.out.println("#等30分钟继续抓取数据。");
            Thread.sleep(1800000);
            startStoryTemplate(templateId, user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void pause(Integer crawTemplateId, String type, User user) {
        Crawl crawl = crawlMap.get(type + "@" + crawTemplateId);
        if (crawl != null) {
            crawl.pause();
        }
    }

    public void stop(Integer crawTemplateId, String type, User user) {
        Crawl crawl = crawlMap.get(type + "@" + crawTemplateId);
        if (crawl != null) {
            crawl.stop();
        }
    }

    public void reStart(Integer crawTemplateId, String type, User user) {
        Crawl crawl = crawlMap.get(type + "@" + crawTemplateId);
        if (crawl != null) {
            crawl.reStart();
        }
    }
}
