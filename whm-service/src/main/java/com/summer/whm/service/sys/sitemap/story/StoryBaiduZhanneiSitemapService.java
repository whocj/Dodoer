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
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.mapper.story.StoryInfoMapper;
import com.summer.whm.service.sys.model.StoryBaiduZhannei;

@Service
public class StoryBaiduZhanneiSitemapService {
    private final Logger log = LoggerFactory.getLogger(StoryBaiduZhanneiSitemapService.class);

    @Autowired
    private StoryInfoMapper storyInfoMapper;

    private static SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public Map<String, List<StoryBaiduZhannei>> buildSitemap(boolean isMobile) {
        Map<String, List<StoryBaiduZhannei>> map = new HashMap<String, List<StoryBaiduZhannei>>();
        map.put(GlobalConfigHolder.DOMAIN_TYPE_STORY, buildStoryUrl(isMobile));
        return map;
    }

    public List<StoryBaiduZhannei> buildStoryUrl(boolean isMobile) {
        List<StoryInfo> list = storyInfoMapper.queryLatestTopN(GlobalConfigHolder.SITEMAP_COUNT);
        List<StoryBaiduZhannei> storyList = new ArrayList<StoryBaiduZhannei>();
        String tempExcerpt = null;

        String url = GlobalConfigHolder.DODOER_DOMAIN_URL + "/main/%d.html";
        String detailUrl = GlobalConfigHolder.DODOER_DOMAIN_URL + "/detail/%d.html";

        if (isMobile) {
            url = GlobalConfigHolder.DODOER_M_DOMAIN_URL + "/main/%d.html";
            detailUrl = GlobalConfigHolder.DODOER_M_DOMAIN_URL + "/detail/%d.html";
        }

        String loc = null;
        String newestChapterUrll = null;
        if (list != null && list.size() > 0) {
            Date currentDate = new Date();
            for (StoryInfo storyInfo : list) {
                //如果没有值则不需要返回
                if(storyInfo.getLastDetailId() == null){
                    continue;
                }
                tempExcerpt = storyInfo.getOutline();
                if (tempExcerpt == null) {
                    tempExcerpt = storyInfo.getTitle() + "|" + storyInfo.getAuthor() + "|"
                            + storyInfo.getCategoryName();
                }
                loc = String.format(url, storyInfo.getId());
                newestChapterUrll = String.format(detailUrl, storyInfo.getLastDetailId());
                // public StoryBaiduZhannei(String loc, String lastmod, String name, String authorName,
                // String image, String description, String genre, String updateStatus,
                // String keywords, String totalClick, String newestChapterHeadline, String newestChapterUrl,
                // String newestChapterDateModified)

                StoryBaiduZhannei zhannei = new StoryBaiduZhannei(loc, SDF_YYYY_MM_DD.format(currentDate),
                        storyInfo.getTitle(), storyInfo.getAuthor() != null ? storyInfo.getAuthor() : "佚名",
                        storyInfo.getPicPath() != null ? storyInfo.getPicPath()
                                : "http://www.dodoer.com/RES/images/default_120_150.jpg",
                        storyInfo.getOutline() != null ? storyInfo.getOutline() : storyInfo.getTitle(),
                        storyInfo.getCategoryName(), "3".equals(storyInfo.getStatus()) ? "已完结" : "更新中",
                        storyInfo.getAuthor() != null ? storyInfo.getAuthor() : storyInfo.getTitle(),
                        storyInfo.getReadCount() + storyInfo.getLikeCount() + "", storyInfo.getLastDetailTitle(),
                        newestChapterUrll, SDF_YYYY_MM_DD.format(storyInfo.getLastUpdateDetail() == null ? currentDate
                                : storyInfo.getLastUpdateDetail()));
                storyList.add(zhannei);
            }
        }

        return storyList;
    }

    public Document buildBaiduZNXMLDoc(boolean isMobile) {
        log.info("百度产内搜索的xml");
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("urlset");
        Map<String, List<StoryBaiduZhannei>> map = buildSitemap(isMobile);
        for (Map.Entry<String, List<StoryBaiduZhannei>> entry : map.entrySet()) {
            for (StoryBaiduZhannei zhannei : entry.getValue()) {
                try {
                    writerElement(rootElement, zhannei);
                } catch (Exception e) {
                    log.error("sitemap生成错误，", e);
                }
            }
        }
        return document;
    }

    private void writerElement(Element rootElement, StoryBaiduZhannei zhannei) {
        Element urlElement = rootElement.addElement("url");
        Element element = urlElement.addElement("loc");
        element.addCDATA(zhannei.getLoc());
        element = urlElement.addElement("lastmod");
        element.setText(zhannei.getLastmod());

        Element dataElement = urlElement.addElement("data");
        Element tempElement = dataElement.addElement("name");
        tempElement.setText(zhannei.getName());

        Element authorElement = dataElement.addElement("author");
        tempElement = authorElement.addElement("name");
        tempElement.setText(zhannei.getAuthorName());

        tempElement = dataElement.addElement("image");
        tempElement.addCDATA(zhannei.getImage());

        tempElement = dataElement.addElement("description");
        tempElement.addCDATA(zhannei.getDescription());

        tempElement = dataElement.addElement("genre");
        tempElement.setText(zhannei.getGenre());

        tempElement = dataElement.addElement("url");
        tempElement.addCDATA(zhannei.getLoc());

        tempElement = dataElement.addElement("updateStatus");
        tempElement.setText(zhannei.getUpdateStatus());

        tempElement = dataElement.addElement("trialStatus");
        tempElement.setText(zhannei.getTrialStatus());

        tempElement = dataElement.addElement("keywords");
        tempElement.setText(zhannei.getKeywords());

        tempElement = dataElement.addElement("totalClick");
        tempElement.setText(zhannei.getTotalClick());
        if (zhannei.getNewestChapterHeadline() != null) {
            Element newestChapter = dataElement.addElement("newestChapter");
            newestChapter.addElement("headline").setText(zhannei.getNewestChapterHeadline());
            newestChapter.addElement("url").addCDATA(zhannei.getNewestChapterUrl());
        }
        dataElement.addElement("dateModified").setText(zhannei.getDateModified());
    }

    public static void main(String[] args) {
    }
}
