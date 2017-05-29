package com.summer.whm.web.controller.story;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.MD5;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryUserBookshelf;
import com.summer.whm.entiry.story.StoryUserRead;
import com.summer.whm.entiry.user.User;
import com.summer.whm.service.category.CategoryService;
import com.summer.whm.service.stroy.StoryDetailService;
import com.summer.whm.service.stroy.StoryInfoService;
import com.summer.whm.service.stroy.StoryRecommendService;
import com.summer.whm.service.stroy.StoryUserBookshelfService;
import com.summer.whm.service.stroy.StoryUserReadService;
import com.summer.whm.web.common.utils.WebConstants;
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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StoryRecommendService storyRecommendService;

    @RequestMapping("/list/{cid}/{cp}")
    public String list(HttpServletRequest request, HttpServletResponse response, @PathVariable("cp") int cp,
            @PathVariable("cid") int cid, ModelMap model) {
        if (cp < 1) {
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

        Category category = categoryService.loadById(cid + "");
        model.put("category", category);

        // 热门推荐的
        List<StoryInfo> hotList = storyInfoService.queryTopNByHot(cid, 3);
        model.put("hotList", hotList);

        // 阅读最多的
        List<StoryInfo> readStoryList = storyInfoService.queryTopHot(null, TOP_10);
        // 用户最喜欢
        List<StoryInfo> likeStoryList = storyInfoService.queryTopLike(null, TOP_10);

        model.put("readStoryList", readStoryList);
        model.put("likeStoryList", likeStoryList);

        return getForward(request, response, "story/list/list_index.ftl");
    }

    // 手机使用分页使用
    @RequestMapping("/more/{cid}/{cp}")
    public String more(HttpServletRequest request, HttpServletResponse response, @PathVariable("cp") int cp,
            @PathVariable("cid") int cid, ModelMap model) {
        if (cp < 1) {
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

        Category category = categoryService.loadById(cid + "");
        model.put("category", category);

        return "m/story/list/list_more.ftl";
    }

    @RequestMapping("/main/{id}")
    public String main(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {

        if (id != 0) {
            StoryInfo storyInfo = storyInfoService.queryById(id);

            List<StoryInfo> recHotStoryList = storyRecommendService.queryHotByCategoryId(null);
            List<StoryInfo> recNewStoryList = storyRecommendService.queryNewByCategoryId(null);

            model.put("recHotStoryList", recHotStoryList);
            model.put("recNewStoryList", recNewStoryList);
            if (storyInfo == null) {
                return ERROR;
            }
            model.put("token",  MD5.encode("Story_" + storyInfo.getReadCount()));
            model.put("storyInfo", storyInfo);
        } else {
            return ERROR;
        }

        return getForward(request, response, "story/main/story_info_index.ftl");
    }

    @RequestMapping("/detail/{id}")
    public String detail(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        if (id != 0) {
            return detail(request, response, 0, id, model);
        } else {
            return ERROR;
        }

        // return getForward(request, response, "story/detail/story_detail_index.ftl");
    }

    @RequestMapping("/detail/{storyId}/{id}")
    public String detail(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("storyId") int storyId, @PathVariable("id") int id, ModelMap model) {
        if (id != 0) {
            StoryDetail storyDetail = storyDetailService.queryById(id, storyId);
            if (storyDetail == null) {
                return ERROR;
            }
            StoryInfo storyInfo = storyInfoService.queryById(storyDetail.getStoryId());

            StoryDetail nextStoryDetail = storyDetailService.queryNextByStoryAndId(storyDetail.getStoryId(), id);
            StoryDetail prevStoryDetail = storyDetailService.queryPrevByStoryAndId(storyDetail.getStoryId(), id);

            List<StoryInfo> recHotStoryList = storyRecommendService.queryHotByCategoryId(null);
            List<StoryInfo> recNewStoryList = storyRecommendService.queryNewByCategoryId(null);

            model.put("recHotStoryList", recHotStoryList);
            model.put("recNewStoryList", recNewStoryList);

            model.put("nextStoryDetail", nextStoryDetail);
            model.put("prevStoryDetail", prevStoryDetail);
            model.put("storyInfo", storyInfo);
            model.put("storyDetail", storyDetail);

            storyInfoService.addRead(storyInfo.getId());

            // 处理阅读记录
            User user = this.getSessionUser(request);
            if (user != null) {
                StoryUserRead userRead = storyUserReadService.queryByUserIdAndStoryId(user.getId(), storyInfo.getId());
                if (userRead != null) {
                    StoryUserRead storyUserRead = new StoryUserRead();
                    storyUserRead.setId(userRead.getId());
                    storyUserRead.setReadDetailId(storyDetail.getId());
                    storyUserRead.setReadDetailTitle(storyDetail.getTitle());
                    storyUserReadService.save(storyUserRead);
                } else {
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

                StoryUserBookshelf bookshelf = storyUserBookshelfService.queryByUserIdAndStoryId(user.getId(),
                        storyInfo.getId());
                if (bookshelf != null) {
                    StoryUserBookshelf storyUserBookshelf = new StoryUserBookshelf();
                    storyUserBookshelf.setId(bookshelf.getId());
                    storyUserBookshelf.setReadDetailId(storyDetail.getId());
                    storyUserBookshelf.setReadDetailTitle(storyDetail.getTitle());
                    storyUserBookshelfService.save(storyUserBookshelf);
                }
            }
        } else {
            return ERROR;
        }

        return getForward(request, response, "story/detail/story_detail_index.ftl");
    }

    @RequestMapping("/addLike/{id}")
    public void addLike(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        if (id != 0) {
            storyInfoService.addLike(id);
            AjaxModel ajaxModel = new AjaxModel(WebConstants.RESP_STATUS_SUCC, "推荐成功。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
        } else {
            AjaxModel ajaxModel = new AjaxModel(WebConstants.RESP_STATUS_ERR_PARM, "传入参数有误。");
            this.ajaxJson(response, JSONObject.toJSONString(ajaxModel));
        }
    }

    @RequestMapping("/finish/{cid}/{cp}")
    public String finish(HttpServletRequest request, HttpServletResponse response, @PathVariable("cp") int cp,
            @PathVariable("cid") int cid, ModelMap model) {
        if (cp < 1) {
            cp = 1;
        }
        PageModel<StoryInfo> page = new PageModel<StoryInfo>(cp, WebConstants.PAGE_SIZE * 2);
        if (cid != 0) {
            page.insertQuery("categoryId", cid);
        } else {
            page.insertQuery("categoryId", null);
        }
        page.insertQuery("status", "3");
        storyInfoService.list(page);
        model.put("page", page);

        // 热门推荐的
        List<StoryInfo> hotList = storyInfoService.queryTopNByHot(null, 3);
        model.put("hotList", hotList);

        // 阅读最多的
        List<StoryInfo> readStoryList = storyInfoService.queryTopHot(null, TOP_10);

        // 用户最喜欢
        List<StoryInfo> likeStoryList = storyInfoService.queryTopLike(null, TOP_10);

        model.put("readStoryList", readStoryList);
        model.put("likeStoryList", likeStoryList);

        return getForward(request, response, "story/finish/list_index.ftl");
    }

    @RequestMapping("/gotoDownload/{id}")
    public void gotoDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) {
        // 未登录用户，跳转至登录页面
        User user = this.getSessionUser(request);
        if (user == null) {
            return;
        }
    }

    @RequestMapping("/download/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") int id,
            ModelMap model) throws IOException {
        // 未登录用户，跳转至登录页面
        User user = this.getSessionUser(request);
        int topN = Integer.MAX_VALUE;
        if (user == null) {
            topN = 10;
        }
        StoryInfo storyInfo = storyInfoService.loadById(id + "");
        String tempToken = MD5.encode("Story_" + storyInfo.getReadCount());
        String token = request.getParameter("token");
        
        if (storyInfo != null) {
            if(!tempToken.equals(token)){
//                response.setCharacterEncoding("utf-8");
                String downloadFilename = URLEncoder.encode("操作过期，请刷新重试，谢谢", "UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename + ".txt");// 设置在下载框默认显示的文件名
                response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
                response.getOutputStream().print("操作过期，请刷新重试，谢谢\n");
                response.getOutputStream().print("详细信息请查看http://www.dodoer.com/main/" + id + ".html\n");
                response.getOutputStream().flush();
                response.getOutputStream().close();
                return;
            }
            
            File file = storyInfoService.exportStory(id, topN);
            if (file != null) {
                String downloadFilename = URLEncoder.encode(storyInfo.getTitle(), "UTF-8");
                if (file.exists()) {
                    // 写明要下载的文件的大小
                    response.setContentLength((int) file.length());
                    response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename + ".txt");// 设置在下载框默认显示的文件名
                    response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
                    // 读出文件到response
                    // 这里是先需要把要把文件内容先读到缓冲区
                    // 再把缓冲区的内容写到response的输出流供用户下载
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    byte[] b = new byte[bufferedInputStream.available()];
                    bufferedInputStream.read(b);
                    OutputStream outputStream = response.getOutputStream();
                    outputStream.write(b);
                    // 人走带门
                    bufferedInputStream.close();
                    outputStream.flush();
                    outputStream.close();
                }

            }
        }

    }
}
