package com.summer.whm.web.controller.story;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.story.StoryTopicDetail;
import com.summer.whm.service.stroy.StoryTopicDetailService;
import com.summer.whm.web.controller.BaseController;

@Controller
@RequestMapping("/story/topic/detail")
public class StoryTopicDetailController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(StoryTopicDetailController.class);

    @Autowired
    private StoryTopicDetailService storyTopicDetailService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, @RequestParam(value = "topicId") int topicId, ModelMap model) {
        List<StoryTopicDetail> detailList = storyTopicDetailService.queryByTopicId(topicId);
        model.put("detailList", detailList);
        model.put("topicId", topicId);
        return "story/topic/detailList.ftl";
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = "id") int id,
            @RequestParam(value = "topicId") int topicId, ModelMap model) {
        StoryTopicDetail storyTopicDetail = storyTopicDetailService.loadById(id + "");
        model.put("topicDetail", storyTopicDetail);
        model.put("topicId", topicId);
        LOG.info("edit storyTopicDetail id={}", id);
        return "story/topic/detail/edit.ftl";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, @RequestParam(value = "topicId") int topicId, ModelMap model) {
        model.put("topicDetail", new StoryTopicDetail());
        model.put("topicId", topicId);
        return "story/topic/detail/edit.ftl";
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "ids") String ids, @RequestParam(value = "topicId") String topicId) {
        LOG.info("delete StoryTopicDetail id={}", ids);
        storyTopicDetailService.deleteById(ids);
        return "redirect:list.htm?topicId=" + topicId;
    }

    @RequestMapping("/submit")
    public String submit(HttpServletRequest request, HttpServletResponse response, StoryTopicDetail storyTopicDetail) {
        LOG.info("submit storyTopicDetail {}", storyTopicDetail);
        if (storyTopicDetail != null && storyTopicDetail.isNew()) {
            this.buildExtCreatorPara(request, storyTopicDetail);
            storyTopicDetailService.insert(storyTopicDetail);
        } else {
            storyTopicDetailService.update(storyTopicDetail);
        }

        return "redirect:list.htm?topicId=" + storyTopicDetail.getTopicId();
    }
}
