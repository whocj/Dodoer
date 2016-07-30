package com.summer.whm.spider.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.summer.whm.common.configs.GlobalConfigHolder;
import com.summer.whm.entiry.post.Post;
import com.summer.whm.entiry.post.PostText;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.service.post.PostService;
import com.summer.whm.service.post.PostTextService;
import com.summer.whm.service.post.TopicService;
import com.summer.whm.service.search.SearchPostService;
import com.summer.whm.service.search.model.PostType;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.spider.SpiderContext;
import com.summer.whm.spider.store.CrawlStore;
import com.summer.whm.spider.utils.CustomerHttpClient;
import com.summer.whm.spider.utils.LocalIP;

public class ParseDetailTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ParseDetailTask.class);

    private SpiderContext spiderContext;

    private PostService postService;

    private PostTextService postTextService;

    private TopicService topicService;

    private SearchPostService searchPostService;
    
    public ParseDetailTask(SpiderContext spiderContext, TopicService topicService, PostService postService,
            PostTextService postTextService,SearchPostService searchPostService) {
        this.spiderContext = spiderContext;
        this.topicService = topicService;
        this.postService = postService;
        this.postTextService = postTextService;
        this.searchPostService = searchPostService;
    }

    @Override
    public void run() {
        try {
            boolean done = false;
            BlockingQueue<ParseDetailElement> parseDetailQueue = spiderContext.getParseDetailQueue();
            ParseDetailElement parseDetailElement = null;
            while (!done) {
                // 取出队首元素，如果队列为空，则阻塞
                parseDetailElement = parseDetailQueue.take();
                try {
                    synchronized (this) {
                        if (parseDetailElement.isEnd()) {
                            System.out.println(Thread.currentThread().getName() + " Stop.");
                            return;
                        }

                        parse(parseDetailElement, 1);
                        parseDetailElement.getHtmlPage().getWebClient().closeAllWindows();
                    }
                } catch (Exception e) {
                    System.out.println("Error:"
                            + parseDetailElement.getHtmlPage().getWebResponse().getRequestSettings().getUrl());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void parse(ParseDetailElement parseDetailElement, int current) throws InterruptedException {
        DetailTemplate template = spiderContext.getDetailTemplate();

        HtmlPage htmlPage = parseDetailElement.getHtmlPage();
        CrawlStore crawlStore = new CrawlStore();

        crawlStore.setUrl(htmlPage.getWebResponse().getRequestSettings().getUrl().toString());

        List<HtmlElement> titleElementList = ((List<HtmlElement>) htmlPage.getByXPath(template.getTitleXPath()));
        if (titleElementList != null && titleElementList.size() > 0) {
            List<HtmlElement> tempElementList = null;
            HtmlElement titleHtmlElement = titleElementList.get(0);
            List<String> titleExcludeXPathList = template.getTitleExcludeXPathList();
            //排除不需要数据
            if(titleExcludeXPathList != null && titleExcludeXPathList.size() > 0){
                for(String str : titleExcludeXPathList){
                    tempElementList = (List<HtmlElement>)titleHtmlElement.getByXPath(str);
                    if(tempElementList != null && tempElementList.size() > 0){
                        for(HtmlElement temp : tempElementList){
                            temp.remove();
                        }
                    }
                }
            }
            
            crawlStore.setTitle(titleHtmlElement.asText());
        } else {
            crawlStore.setTitle(htmlPage.getTitleText());
        }

        List<HtmlElement> contentElementList = ((List<HtmlElement>) htmlPage.getByXPath(template.getContentXPath()));
        List<String> contentExcludeXPathList = template.getContentExcludeXPathList();
        if (contentElementList != null && contentElementList.size() > 0) {
            List<HtmlElement> tempElementList = null;
            List<String> contentList = new ArrayList<String>();
            List<String> contentTextList = new ArrayList<String>();
            for (HtmlElement htmlElement : contentElementList) {
                
              //排除不需要数据
                if(contentExcludeXPathList != null && contentExcludeXPathList.size() > 0){
                    for(String str : contentExcludeXPathList){
                        tempElementList = (List<HtmlElement>)htmlElement.getByXPath(str);
                        if(tempElementList != null && tempElementList.size() > 0){
                            for(HtmlElement temp : tempElementList){
                                temp.remove();
                            }
                        }
                    }
                }
                
                
                contentList.add(htmlElement.asXml());
                contentTextList.add(htmlElement.asText());
            }
            crawlStore.addContentList(contentList);
            crawlStore.addContentTextList(contentTextList);
        }

        if (StringUtils.isNotEmpty(template.getCommentsXPath())) {
            List<HtmlElement> commentsElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                    .getCommentsXPath()));
            if (commentsElementList != null && commentsElementList.size() > 0) {
                List<String> commentsList = new ArrayList<String>();
                List<String> commentsTextList = new ArrayList<String>();

                for (HtmlElement htmlElement : commentsElementList) {
                    commentsList.add(htmlElement.asXml());
                    commentsTextList.add(htmlElement.asText());
                }
                crawlStore.addContentList(commentsList);
                crawlStore.addContentTextList(commentsTextList);
            }
        }

        if (StringUtils.isNotEmpty(template.getContentNextXPath())) {
            processNextContent(crawlStore, htmlPage, template);
        }

        // writeFile(crawlStore, template);
        insertDBExt(crawlStore, template);
        
        //sendForm2BBS(crawlStore, template);
    }

    @SuppressWarnings("unchecked")
    public void processNextContent(CrawlStore crawlStore, HtmlPage htmlPage, DetailTemplate template) {
        HtmlPage nextHtmlPage = nextContent(htmlPage, template.getContentNextXPath());
        if (nextHtmlPage != null) {
            List<HtmlElement> contentElementList = ((List<HtmlElement>) htmlPage.getByXPath(template.getContentXPath()));
            if (contentElementList != null && contentElementList.size() > 0) {
                List<String> contentList = new ArrayList<String>();
                List<String> contentTextList = new ArrayList<String>();
                for (HtmlElement htmlElement : contentElementList) {
                    contentList.add(htmlElement.asXml());
                    contentTextList.add(htmlElement.asText());
                }
                crawlStore.addContentList(contentList);
                crawlStore.addContentTextList(contentTextList);
            }

            if (template.getCommentsXPath() != null) {
                List<HtmlElement> commentsElementList = ((List<HtmlElement>) htmlPage.getByXPath(template
                        .getCommentsXPath()));
                if (commentsElementList != null && commentsElementList.size() > 0) {
                    List<String> commentsList = new ArrayList<String>();
                    List<String> commentsTextList = new ArrayList<String>();
                    for (HtmlElement htmlElement : commentsElementList) {
                        commentsList.add(htmlElement.asXml());
                        commentsTextList.add(htmlElement.asXml());
                    }
                    crawlStore.addCommentsList(commentsList);
                    crawlStore.addCommentsTextList(commentsTextList);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public HtmlPage nextContent(HtmlPage htmlPage, String contentNextXPath) {
        HtmlPage nextHtmlPage = null;
        try {
            List<HtmlAnchor> detailAnchorList = (List<HtmlAnchor>) htmlPage.getByXPath(contentNextXPath);
            if (detailAnchorList != null && detailAnchorList.size() > 0) {
                nextHtmlPage = detailAnchorList.get(0).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextHtmlPage;
    }

    public void writeFile(CrawlStore crawlStore, DetailTemplate template) {
        String basePath = "D:/stackoverflow/";
        File file = new File(basePath + System.currentTimeMillis() + ".html");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String str : crawlStore.getContentList()) {
            pw.println(str);
            pw.println("======================");
        }
        pw.flush();
        pw.close();
    }

    public void sendForm2BBS(CrawlStore crawlStore, DetailTemplate template) {
        Integer postTypeId = 1;
        String title = crawlStore.getTitle();
        String content = null;
        Integer forumId = template.getForumId();
        Integer topicId = null;

        if (crawlStore.getContentList() != null && crawlStore.getContentList().size() > 0) {
            content = crawlStore.getContentList().get(0);
            
            HttpPost httppost = new HttpPost(GlobalConfigHolder.BBS_TOPIC_URL);
            // 创建参数队列
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            formParams.add(new BasicNameValuePair("forumId", forumId + ""));
            formParams.add(new BasicNameValuePair("typeId", postTypeId + ""));
            formParams.add(new BasicNameValuePair("outerUrl", crawlStore.getUrl()));
            formParams.add(new BasicNameValuePair("title", title));
            formParams.add(new BasicNameValuePair("content", content));
            
            UrlEncodedFormEntity uefEntity = null;

            try {
                uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                httppost.setEntity(uefEntity);
                HttpResponse response = null;
                response = CustomerHttpClient.getHttpClient().execute(httppost);
                HttpEntity entity = response.getEntity();
                String tempRet = null;
                if (entity != null) {
                    tempRet = EntityUtils.toString(entity, "UTF-8");
                    System.out.println("Response content: " + tempRet);
                    topicId = Integer.parseInt(tempRet.trim());
                }
            } catch (UnsupportedEncodingException e) {
                log.error("发送Topic失败", e);
            } catch (ClientProtocolException e) {
                log.error("发送Topic失败", e);
            } catch (IOException e) {
                log.error("发送Topic失败", e);
            }catch(Exception e){
                log.error("发送Topic失败", e);
            }
            
            if (crawlStore.getContentList().size() > 1 && topicId != null) {
                for (int i = 1; i < crawlStore.getContentList().size(); i++) {
                    formParams = new ArrayList<NameValuePair>();
                    formParams.add(new BasicNameValuePair("forumId", forumId + ""));
                    formParams.add(new BasicNameValuePair("topicId", topicId+""));
                    formParams.add(new BasicNameValuePair("title", title));
                    formParams.add(new BasicNameValuePair("status", SpiderConfigs.DEFAULT_POST_STATUS + ""));
                    formParams.add(new BasicNameValuePair("content", crawlStore.getContentList().get(i)));
                    
                    httppost = new HttpPost(GlobalConfigHolder.BBS_POST_URL);

                    try {
                        uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
                        httppost.setEntity(uefEntity);
                        System.out.println("executing request " + httppost.getURI());
                        HttpResponse response = null;
                        response = CustomerHttpClient.getHttpClient().execute(httppost);
                        HttpEntity entity = response.getEntity();
                        String tempRet = null;
                        if (entity != null) {
                            tempRet = EntityUtils.toString(entity, "UTF-8");
                            System.out.println("Response content: " + tempRet);
                            topicId = Integer.parseInt(tempRet.trim());
                        }

                    } catch (UnsupportedEncodingException e) {
                        log.error("发送Post失败", e);
                    } catch (ClientProtocolException e) {
                        log.error("发送Post失败", e);
                    } catch (IOException e) {
                        log.error("发送Post失败", e);
                    }catch(Exception e){
                        log.error("发送Post失败", e);
                    }
                }
            }
        }
    }

    public void insertDBExt(CrawlStore crawlStore, DetailTemplate template) {
        Integer postTypeId = 1;
        String title = crawlStore.getTitle();
        
        if(this.spiderContext.getStringFilter() != null){
            title = this.spiderContext.getStringFilter().filter(title);
        }
        
        String content = null;
        //String contentText = null;
        Integer userId = SpiderConfigs.SPIDER_USERID;
        Integer forumId = template.getForumId();
        Topic topic = null;
        Post post = null;
        Integer topicId = null;
        SearchPost searchPost = null;
        
        if (crawlStore.getContentList() != null && crawlStore.getContentList().size() > 0) {
            content = crawlStore.getContentList().get(0);
           // contentText = crawlStore.getContentTextList().get(0);
            topic = new Topic();
            topic.setCreaterId(userId);
            topic.setCreator(SpiderConfigs.SPIDER);
            topic.setUsername(SpiderConfigs.SPIDER);
            topic.setForumId(forumId);
            topic.setTypeId(postTypeId);
            topic.setOuterUrl(crawlStore.getUrl());
            topic.setTitle(title);
            topic.setContent(content);
            topic.setCreateTime(new Date());
            topicService.newTopic(topic);
            
            searchPost = new SearchPost(topic.getId()+ "", PostType.POST_TYPE_TOPIC, title, content, SpiderConfigs.SPIDER, "");
            searchPost.setUrl(crawlStore.getUrl());
            searchPost.setIsIndex(0);
            searchPostService.insert(searchPost);
            
            topicId = topic.getId();
            if (crawlStore.getContentList().size() > 1) {
                for (int i = 1; i < crawlStore.getContentList().size(); i++) {
                    post = new Post();
                    post.setCreaterId(userId);
                    post.setCreator(SpiderConfigs.SPIDER);
                    post.setUsername(SpiderConfigs.SPIDER);
                    post.setTopicId(topicId);
                    post.setStatus(SpiderConfigs.DEFAULT_POST_STATUS);
                    post.setTitle(title);
                    post.setContent(crawlStore.getContentList().get(i));
                    postService.newPost(post);
                }
            }
        }
    }

    public void insertDB(CrawlStore crawlStore, DetailTemplate template) {
        Integer postTypeId = 1;
        String title = crawlStore.getTitle();
        String content = null;
        String contentText = null;
        Integer userId = template.getUserId();
        Integer forumId = template.getForumId();
        Topic topic = null;
        Post post = null;
        Integer topicId = null;
        Integer postId = null;
        PostText postText = null;
        if (crawlStore.getContentList() != null && crawlStore.getContentList().size() > 0) {
            content = crawlStore.getContentList().get(0);
            contentText = crawlStore.getContentTextList().get(0);
            topic = new Topic();
            topic.setCreaterId(userId);
            topic.setCreator(SpiderConfigs.SPIDER);
            topic.setForumId(forumId);
            topic.setTypeId(postTypeId);
            topic.setTitle(title);
            topic.setOuterUrl(crawlStore.getUrl());
            topic.setCreateTime(new Date());
            topicService.insert(topic);
            topicId = topic.getId();

            post = new Post();
            post.setCreaterId(userId);
            post.setCreator(SpiderConfigs.SPIDER);
            post.setEditerIp(LocalIP.getLocalIP());
            post.setPosterIp(LocalIP.getLocalIP());
            post.setCreateTime(new Date());
            post.setTopicId(topicId);
            post.setStatus(SpiderConfigs.DEFAULT_POST_STATUS);
            postService.insert(post);
            postId = post.getId();

            topic = new Topic();
            topic.setId(topicId);
            topic.setFirstPostId(postId);
            topicService.update(topic);

            postText = new PostText();
            postText.setCreateTime(new Date());
            postText.setCreator(SpiderConfigs.SPIDER);
            postText.setPostId(postId);
            postText.setPostTitle(title);
            postText.setPostContent(content);
            postText.setPostContentText(contentText);
            postTextService.insert(postText);

            if (crawlStore.getContentList().size() > 1) {
                for (int i = 1; i < crawlStore.getContentList().size(); i++) {

                    post = new Post();
                    post.setCreaterId(userId);
                    post.setCreator(SpiderConfigs.SPIDER);
                    post.setEditerIp(LocalIP.getLocalIP());
                    post.setPosterIp(LocalIP.getLocalIP());
                    post.setCreateTime(new Date());
                    post.setTopicId(topicId);
                    post.setStatus(SpiderConfigs.DEFAULT_POST_STATUS);
                    postService.insert(post);
                    postId = post.getId();

                    postText = new PostText();
                    postText.setCreateTime(new Date());
                    postText.setCreator(SpiderConfigs.SPIDER);
                    postText.setPostId(postId);
                    postText.setPostTitle(title);
                    postText.setPostContent(crawlStore.getContentList().get(i));
                    postText.setPostContentText(crawlStore.getContentList().get(i));
                    postTextService.insert(postText);
                }
            }
        }

        if (crawlStore.getCommentsList() != null && crawlStore.getCommentsList().size() > 0) {
            for (int i = 0; i < crawlStore.getCommentsList().size(); i++) {
                post = new Post();
                post.setCreaterId(userId);
                post.setCreator(SpiderConfigs.SPIDER);
                post.setEditerIp(LocalIP.getLocalIP());
                post.setPosterIp(LocalIP.getLocalIP());
                post.setCreateTime(new Date());
                post.setTopicId(topicId);
                post.setStatus(SpiderConfigs.DEFAULT_POST_STATUS);
                postService.insert(post);
                postId = post.getId();

                postText = new PostText();
                postText.setCreateTime(new Date());
                postText.setCreator(SpiderConfigs.SPIDER);
                postText.setPostId(postId);
                postText.setPostTitle(title);
                postText.setPostContent(crawlStore.getCommentsList().get(i));
                postText.setPostContentText(crawlStore.getCommentsTextList().get(i));
                postTextService.insert(postText);

            }
        }
    }
}
