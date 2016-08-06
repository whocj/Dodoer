package com.summer.whm.spider.crawl;

import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.service.post.PostService;
import com.summer.whm.service.post.PostTextService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.SearchPostService;
import com.summer.whm.service.stroy.StoryDetailService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryPartService;
import com.summer.whm.spider.SpiderContext;
import com.summer.whm.spider.parse.ParseDetailTask;
import com.summer.whm.spider.parse.ParseListTask;
import com.summer.whm.spider.parse.ParseStoryTask;
import com.summer.whm.spider.service.CrawInfoService;

public class Crawl {

    private TopicService topicService;

    private PostService postService;

    private PostTextService postTextService;

    private CrawInfoService crawInfoService;

    private SpiderContext spiderContext;

    private SearchPostService searchPostService;

    private StoryInfoService storyInfoService;

    private StoryPartService storyPartService;

    private StoryDetailService storyDetailService;

    public Crawl() {

    }

    public Crawl(SpiderContext spiderContext, CrawInfoService crawInfoService, SearchPostService searchPostService) {
        super();
        this.spiderContext = spiderContext;
        this.crawInfoService = crawInfoService;
        this.searchPostService = searchPostService;
    }

    public Crawl(SpiderContext spiderContext, CrawInfoService crawInfoService, StoryInfoService storyInfoService,
            StoryPartService storyPartService, StoryDetailService storyDetailService) {
        super();
        this.spiderContext = spiderContext;
        this.storyInfoService = storyInfoService;
        this.storyPartService = storyPartService;
        this.storyDetailService = storyDetailService;
    }

    public Crawl(SpiderContext spiderContext, TopicService topicService, PostService postService,
            PostTextService postTextService, CrawInfoService crawInfoService, SearchPostService searchPostService) {
        super();
        this.spiderContext = spiderContext;
        this.topicService = topicService;
        this.postService = postService;
        this.postTextService = postTextService;
        this.crawInfoService = crawInfoService;
        this.searchPostService = searchPostService;
    }

    public SpiderContext initStackoverflowSpiderContext() {
        SpiderContext spiderContext = new SpiderContext();
        ListTemplate template = new ListTemplate();
        DetailTemplate detailTemplate = new DetailTemplate();
        template.setUrl("http://stackoverflow.com/questions?page=4578&sort=votes");
        template.setNextXPath("//a[@rel='next']");
        template.setDetailXPath("//a[@class='question-hyperlink']");
        template.setTotalPageXPath("//span[class='page-numbers']");
        template.setListXPath("//div[class='question-summary']");

        detailTemplate.setContentXPath("//div[@class='post-text']");
        detailTemplate.setTitleXPath("//a[@class='question-hyperlink']");

        detailTemplate.setUserId(5);
        detailTemplate.setUsername("admin");
        detailTemplate.setForumId(14);

        spiderContext.setDetailTemplate(detailTemplate);
        spiderContext.setListTemplate(template);

        return spiderContext;
    }

    public SpiderContext initCSDNBlongpiderContext() {
        SpiderContext spiderContext = new SpiderContext();
        ListTemplate template = new ListTemplate();
        DetailTemplate detailTemplate = new DetailTemplate();
        template.setUrl("http://blog.csdn.net/?&page=1");
        template.setNextXPath("//div[@class='page_nav']/a[last()-1]");
        template.setDetailXPath("//div[@class='blog_list']/h1/a[last()]");
        template.setTotalPageXPath("//div[@class='page_nav']/span");
        template.setListXPath("//div[@class='blog_list']");

        detailTemplate.setContentXPath("//div[@class='article_content']");
        detailTemplate.setTitleXPath("//div[@class='article_title']");
        detailTemplate.setUserId(5);
        detailTemplate.setUsername("admin");
        detailTemplate.setForumId(15);

        spiderContext.setDetailTemplate(detailTemplate);
        spiderContext.setListTemplate(template);

        return spiderContext;
    }

    public SpiderContext initCSDNNewsSpiderContext() {
        SpiderContext spiderContext = new SpiderContext();
        ListTemplate template = new ListTemplate();
        DetailTemplate detailTemplate = new DetailTemplate();
        template.setUrl("http://news.csdn.net/news/1");
        template.setNextXPath("//div[@class='page_nav']/a[last()-1]");
        template.setDetailXPath("//div[@class='unit']/h1/a");
        template.setListXPath("//div[@class='unit']");

        detailTemplate.setContentXPath("//div[@class='detail']/div[@class='con news_content']");
        detailTemplate.setTitleXPath("//div[@class='detail']/h1[@class='title']");
        detailTemplate.setUserId(5);
        detailTemplate.setUsername("admin");
        detailTemplate.setForumId(15);

        spiderContext.setDetailTemplate(detailTemplate);
        spiderContext.setListTemplate(template);

        return spiderContext;
    }

    public SpiderContext initCNBlogspiderContext() {
        SpiderContext spiderContext = new SpiderContext();
        ListTemplate template = new ListTemplate();
        DetailTemplate detailTemplate = new DetailTemplate();
        template.setUrl("http://www.cnblogs.com/pick/");
        template.setNextXPath("//div[@class='pager']/a[last()]");
        template.setDetailXPath("//a[@class='titlelnk']");
        template.setTotalPageXPath("//div[@class='pager']/a[last()-1]");
        template.setListXPath("//div[class='post_item']");

        detailTemplate.setContentXPath("//div[@id='cnblogs_post_body']");
        detailTemplate.setTitleXPath("//div[@class='post']/h1");

        detailTemplate.setUserId(5);
        detailTemplate.setUsername("admin");
        detailTemplate.setForumId(16);

        spiderContext.setDetailTemplate(detailTemplate);
        spiderContext.setListTemplate(template);

        return spiderContext;
    }

    public SpiderContext initITEyepiderContext() {
        SpiderContext spiderContext = new SpiderContext();
        ListTemplate template = new ListTemplate();
        DetailTemplate detailTemplate = new DetailTemplate();
        template.setUrl("http://www.cnblogs.com/pick/");
        template.setNextXPath("//div[@class='pager']/a[last()]");
        template.setDetailXPath("//a[@class='titlelnk']");
        template.setTotalPageXPath("//div[@class='pager']/a[last()-1]");
        template.setListXPath("//div[class='post_item']");

        detailTemplate.setContentXPath("//div[@id='cnblogs_post_body']");
        detailTemplate.setTitleXPath("//div[@class='post']/h1");

        detailTemplate.setUserId(5);
        detailTemplate.setUsername("admin");
        detailTemplate.setForumId(7);

        spiderContext.setDetailTemplate(detailTemplate);
        spiderContext.setListTemplate(template);

        return spiderContext;
    }

    public SpiderContext initShuyuewuContext() {
        SpiderContext spiderContext = new SpiderContext();
        
        SpiderStoryTemplate spiderStoryTemplate = new SpiderStoryTemplate();
        spiderStoryTemplate.setTitleXPath("//div[@id='info']/h1");
        spiderStoryTemplate.setAuthorXPath("//div[@id='info']/p");
        spiderStoryTemplate.setOutlineXPath("//div[@id='intro']/p");
        spiderStoryTemplate.setDetailXPath("//div[@id='list']/dl/dd/a");
        spiderStoryTemplate.setPicPathXPath("//div[@id='fmimg']/img");
        
        spiderStoryTemplate.setDetailTitleXPath("//div[@class='bookname']/h1");
        spiderStoryTemplate.setDetailContentXPath("//div[@id='content']");
        
        spiderContext.setSpiderStoryTemplate(spiderStoryTemplate);
        
        SpiderStoryJob spiderStoryJob = new SpiderStoryJob();
        spiderStoryJob.setTemplateId(1);
        spiderStoryJob.setCategoryId(10);
        spiderStoryJob.setTitle("超强导航仪");
        spiderStoryJob.setUrl("http://www.shuyuewu.com/kan_75582/");
        spiderContext.setSpiderStoryJob(spiderStoryJob);
        
        return spiderContext;
    }
    
    public void start() {

        crawlTask = new CrawlTask(spiderContext, crawInfoService);
        // for (int i = 0; i < Configs.CRAWL_THREAD_SIZE; i++) {
        Thread thread = new Thread(crawlTask);
        thread.start();
        // }
        parseListTask = new ParseListTask(spiderContext);
        new Thread(parseListTask).start();
        // for (int i = 0; i < Configs.PARSE_THREAD_SIZE; i++) {
        parseDetailTask = new ParseDetailTask(spiderContext, topicService, postService, postTextService,
                searchPostService);
        new Thread(parseDetailTask).start();
        // }

        try {
            spiderContext.getUrlQueue().put(new CrawlElement(spiderContext.getListTemplate().getUrl(), CrawlType.List));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startStory() {

        crawlTask = new CrawlTask(spiderContext, crawInfoService);
        Thread thread = new Thread(crawlTask);
        thread.start();

        ParseStoryTask parseStoryTask = new ParseStoryTask(spiderContext, storyInfoService, storyPartService,
                storyDetailService);
        new Thread(parseStoryTask).start();

        try {
            spiderContext.getUrlQueue().put(
                    new CrawlElement(spiderContext.getSpiderStoryJob().getUrl(), CrawlType.StoryInfo));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CrawlTask crawlTask = null;
    private ParseListTask parseListTask = null;
    private ParseDetailTask parseDetailTask = null;

    public void stop() {
        if (crawlTask != null) {
            crawlTask.stop();
        }
    }

    public void pause() {
        if (crawlTask != null) {
            crawlTask.pause();
        }
    }

    public void reStart() {
        if (crawlTask != null) {
            crawlTask.reStart();
        }
    }

    public SpiderContext getSpiderContext() {
        return spiderContext;
    }

    public void setSpiderContext(SpiderContext spiderContext) {
        this.spiderContext = spiderContext;
    }

    public static void main(String[] args) throws InterruptedException {
        Crawl crawl = new Crawl();
        crawl.setSpiderContext(crawl.initShuyuewuContext());
        crawl.startStory();
    }

}
