package com.summer.whm.web.controller.spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.entiry.spider.SpiderStoryTemplate;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.spider.SpiderConfigs;
import com.summer.whm.spider.service.CrawlService;
import com.summer.whm.spider.service.SpiderStoryJobService;
import com.summer.whm.spider.service.SpiderStoryTemplateService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.common.utils.WebConstants;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/spider/story/job")
public class SpiderStoryJobController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(SpiderStoryJobController.class);

    @Autowired
    private SpiderStoryJobService spiderStoryJobService;

    @Autowired
    private SpiderStoryTemplateService spiderStoryTemplateService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CrawlService crawlService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "") String title, ModelMap model) {
        LOG.info("list SpiderStoryJob currentPage={}", currentPage);
        
        PageModel<SpiderStoryJob> page = new PageModel<SpiderStoryJob>(currentPage, WebConstants.PAGE_SIZE);
        page.insertQuery("title", "%" + title + "%");
        spiderStoryJobService.list(page);
        
        model.put("page", page);
        model.put("title", title);
        return "spider/story/job/list.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id, ModelMap model) {
        SpiderStoryJob spiderStoryJob = spiderStoryJobService.loadById(id + "");
        model.put("spiderStoryJob", spiderStoryJob);
        PageModel<SpiderStoryTemplate> page = spiderStoryTemplateService.list(1, WebConstants.PAGE_SIZE
                * WebConstants.PAGE_SIZE);
        model.put("templateList", page.getContent());

        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY);
        model.put("categoryList", categoryList);

        LOG.info("edit SpiderStoryJob id={}", id);
        return "spider/story/job/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, ModelMap model) {
        model.put("spiderStoryJob", new SpiderStoryJob());
        PageModel<SpiderStoryTemplate> page = spiderStoryTemplateService.list(1, WebConstants.PAGE_SIZE
                * WebConstants.PAGE_SIZE);
        model.put("templateList", page.getContent());

        List<Category> categoryList = categoryService.queryBySite(Constants.SITE_ID_STORY);
        model.put("categoryList", categoryList);

        return "spider/story/job/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids) {
        LOG.info("delete SpiderStoryJob id={}", ids);
        spiderStoryJobService.deleteById(ids);
        return "redirect:list.htm";
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, SpiderStoryJob spiderStoryJob) {
        LOG.info("submit SpiderStoryJob {}", spiderStoryJob);
        if (spiderStoryJob != null && spiderStoryJob.isNew()) {
            buildExtCreatorPara(request, spiderStoryJob);
            User user = getSessionUser(request);
            spiderStoryJob.setUserId(user.getId());
            spiderStoryJob.setUsername(user.getUsername());
            spiderStoryJob.setStatus(SpiderConfigs.STORY_JOB_STATUS_INIT);
            spiderStoryJobService.insert(spiderStoryJob);
        } else {
            spiderStoryJob.setLastUpdate(new Date());
            spiderStoryJobService.update(spiderStoryJob);
        }

        return "redirect:list.htm";
    }

    @RequestMapping("/toUpload")
    public String toUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "spider/story/job/upload.ftl";
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, HttpServletResponse response,
            @RequestParam MultipartFile multipartFile) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "utf-8"));
        String temp = null;
        while ((temp = br.readLine()) != null) {
            String[] strs = temp.split("###");
            if (strs != null && strs.length >= 4) {

                SpiderStoryJob tempJob = spiderStoryJobService.queryByUrl(strs[3]);
                if (tempJob == null) {
                    SpiderStoryJob spiderStoryJob = new SpiderStoryJob();
                    spiderStoryJob.setTitle(strs[0]);
                    spiderStoryJob.setCategoryId(Integer.parseInt(strs[1]));
                    spiderStoryJob.setTemplateId(Integer.parseInt(strs[2]));
                    spiderStoryJob.setUrl(strs[3]);
                    spiderStoryJob.setStatus(SpiderConfigs.STORY_JOB_STATUS_INIT);

                    buildExtCreatorPara(request, spiderStoryJob);
                    User user = getSessionUser(request);
                    spiderStoryJob.setUserId(user.getId());
                    spiderStoryJob.setUsername(user.getUsername());
                    LOG.info("upload SpiderStoryJob {}", spiderStoryJob);

                    spiderStoryJobService.insert(spiderStoryJob);
                } else {
                    System.out.println(strs[0] + "数据重复。");
                }
            }
        }

        return "redirect:list.htm";
    }

    @RequestMapping("/start")
    public String start(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        crawlService.startStory(id, this.getSessionUser(request));
        return "redirect:list.htm";
    }

    @RequestMapping("/pause")
    public String pause(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        crawlService.pause(id, SpiderConfigs.DOMAIN_TYPE_STORY, this.getSessionUser(request));
        return "redirect:list.htm";
    }

    @RequestMapping("/stop")
    public String stop(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") int id,
            ModelMap model) {
        crawlService.stop(id, SpiderConfigs.DOMAIN_TYPE_STORY, this.getSessionUser(request));
        return "redirect:list.htm";
    }
}
