package com.summer.whm.web.controller.story;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.summer.whm.WebConstants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryUserBookshelf;
import com.summer.whm.entiry.story.StoryUserRead;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.stroy.StoryDetailService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryUserBookshelfService;
import com.summer.whm.service.stroy.StoryUserReadService;
import com.summer.whm.web.common.utils.Constants;
import com.summer.whm.web.controller.BaseController;
import com.summer.whm.web.controller.model.AjaxModel;

@Controller
@RequestMapping("/")
public class StoryController extends BaseController {

    public static final Integer TOP_10 = 10;
    
    @Autowired
    private StoryInfoService storyInfoService;

    @Autowired
    private StoryDetailService storyDetailService;

    @Autowired
    private StoryUserReadService storyUserReadService;

    @Autowired
    private StoryUserBookshelfService storyUserBookshelfService;

    @RequestMapping("/list/{cid}/{cp}")
    public String list(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("cp") int cp, @PathVariable("cid") int cid, ModelMap model) {
        if(cp < 1){
            cp = 1;
        }
        
        PageModel<StoryInfo> page = new PageModel<StoryInfo>(cp, WebConstants.PAGE_SIZE);
        if (cid != 0) {
            page.insertQuery("categoryId", cid);
        } else {
            page.insertQuery("categoryId", null);
        }
        storyInfoService.list(page);
        model.put("page", page);
        
        //热门推荐的
        List<StoryInfo> hotList = storyInfoService.queryTopNByHot(cid, 3);
        model.put("hotList", hotList);
        
        //阅读最多的
        List<StoryInfo> readStoryList = storyInfoService.queryTopHot(null, TOP_10);
        //用户最喜欢
        List<StoryInfo> likeStoryList =  storyInfoService.queryTopLike(null, TOP_10);
        
        model.put("readStoryList", readStoryList);
        model.put("likeStoryList", likeStoryList);
        
        return "story/list/list_index.ftl";
    }

    @RequestMapping("/main/{id}")
    public String main(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {

        if (id != 0) {
            StoryInfo storyInfo = storyInfoService.queryById(id);
            model.put("storyInfo", storyInfo);
        }

        return "story/main/story_info_index.ftl";
    }

    @RequestMapping("/detail/{id}")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {

        if (id != 0) {
            StoryDetail storyDetail = storyDetailService.loadById(id + "");
            StoryInfo storyInfo = storyInfoService.queryById(storyDetail.getStoryId());

            StoryDetail nextStoryDetail = storyDetailService.queryNextByStoryAndId(storyDetail.getStoryId(), id);
            StoryDetail prevStoryDetail = storyDetailService.queryPrevByStoryAndId(storyDetail.getStoryId(), id);

            model.put("nextStoryDetail", nextStoryDetail);
            model.put("prevStoryDetail", prevStoryDetail);
            model.put("storyInfo", storyInfo);
            model.put("storyDetail", storyDetail);

            // 处理阅读记录
            User user = this.getSessionUser(request);
            if (user != null) {
                StoryUserRead userRead = storyUserReadService.queryByUserIdAndStoryId(user.getId(), storyInfo.getId());
                if(userRead != null){
                    StoryUserRead storyUserRead = new StoryUserRead();
                    storyUserRead.setId(userRead.getId());
                    storyUserRead.setReadDetailId(storyDetail.getId());
                    storyUserRead.setReadDetailTitle(storyDetail.getTitle());
                    storyUserReadService.save(storyUserRead);
                }else{
                    StoryUserRead storyUserRead = new StoryUserRead();
                    storyUserRead.setUserId(user.getId());
                    storyUserRead.setUsername(user.getUsername());
                    storyUserRead.setCreator(user.getUsername());
                    storyUserRead.setCreateTime(new Date());
                    storyUserRead.setStoryId(storyInfo.getId());
                    storyUserRead.setTitle(storyInfo.getTitle());
                    storyUserRead.setReadDetailId(storyDetail.getId());
                    storyUserRead.setReadDetailTitle(storyDetail.getTitle());
                    storyUserReadService.save(storyUserRead);
                }
                
                StoryUserBookshelf bookshelf = storyUserBookshelfService.queryByUserIdAndStoryId(user.getId(), storyInfo.getId());
                if(bookshelf != null){
                    StoryUserBookshelf storyUserBookshelf = new StoryUserBookshelf();
                    storyUserBookshelf.setId(bookshelf.getId());
                    storyUserBookshelf.setReadDetailId(storyDetail.getId());
                    storyUserBookshelf.setReadDetailTitle(storyDetail.getTitle());
                    storyUserBookshelfService.save(storyUserBookshelf);
                }
            }
        }

        return "story/detail/story_detail_index.ftl";
    }

    @RequestMapping("/addLike/{id}")
    public void addLike(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        if (id != 0) {
            storyInfoService.addLike(id);
            AjaxModel ajaxModel = new AjaxModel(Constants.RESP_STATUS_SUCC, "添加成功。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
        } else {
            AjaxModel ajaxModel = new AjaxModel(Constants.RESP_STATUS_ERR_PARM, "传入参数有误。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
        }
    }

}
