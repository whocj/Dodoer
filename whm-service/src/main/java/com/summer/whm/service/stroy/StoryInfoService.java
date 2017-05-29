package com.summer.whm.service.stroy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.Constants;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryPart;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryInfoMapper;
import com.summer.whm.mapper.story.StoryPartMapper;
import com.summer.whm.service.BaseService;
import com.summer.whm.service.search.SearchPostService;
import com.summer.whm.service.search.model.PostType;

/**
 * 小说基本信息
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class StoryInfoService extends BaseService {

    @Autowired
    private StoryInfoMapper storyInfoMapper;

    @Autowired
    private StoryPartMapper storyPartMapper;

    @Autowired
    private StoryDetailService storyDetailService;

    @Autowired
    private SearchPostService searchPostService;

    public void addLike(Integer id) {
        storyInfoMapper.addLike(id);
    }

    public void addRead(Integer id) {
        storyInfoMapper.addRead(id);
    }

    public void addReply(Integer id) {
        storyInfoMapper.addReply(id);
    }

    // 根据小说标题和作者判断是否存在，存在返回true,否则返回false
    public boolean exists(String title, String author) {
        List<StoryInfo> storyInfoList = storyInfoMapper.queryByTitleAndAuthor(title, author);
        if (storyInfoList != null && storyInfoList.size() > 0) {
            return true;
        }
        return false;
    }

    public List<StoryInfo> queryByTitleAndAuthor(String title, String author) {
        return storyInfoMapper.queryByTitleAndAuthor(title, author);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryTopNByHot'")
    public List<StoryInfo> queryTopNByHot(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryTopNByHot(categoryId, topN);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryStoryInfoOrderCreateTimeTop'")
    public List<StoryInfo> queryStoryInfoOrderCreateTimeTop(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryStoryInfoOrderCreateTimeTop(categoryId, topN);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryStoryInfoOrderlastUpdateTop'")
    public List<StoryInfo> queryStoryInfoOrderlastUpdateTop(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryStoryInfoOrderlastUpdateTop(categoryId, topN);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryStoryInfoOrderSortIndexTop'")
    public List<StoryInfo> queryStoryInfoOrderSortIndexTop(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryStoryInfoOrderSortIndexTop(categoryId, topN);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryTopReply'")
    public List<StoryInfo> queryTopReply(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryTopReply(categoryId, topN);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryTopHot'")
    public List<StoryInfo> queryTopHot(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryTopHot(categoryId, topN);
    }

    @Cacheable(value = "webCache", key = "#categoryId + '@' + #topN + 'StoryInfoService.queryTopLike'")
    public List<StoryInfo> queryTopLike(Integer categoryId, Integer topN) {
        return storyInfoMapper.queryTopLike(categoryId, topN);
    }

    public StoryInfo queryById(Integer storyId) {
        StoryInfo storyInfo = storyInfoMapper.loadById(storyId + "");
        if (storyInfo != null) {
            List<StoryPart> storyPartList = storyPartMapper.queryByStoryId(storyId);
            if (storyPartList != null && storyPartList.size() > 0) {
                for (StoryPart storyPart : storyPartList) {
                    List<StoryDetail> storyDetailList = storyDetailService.queryByPartId(storyPart.getId(),
                            storyPart.getStoryId());
                    storyPart.setStoryDetailList(storyDetailList);
                }
                storyInfo.setStoryPartList(storyPartList);
                storyInfo.setPart(true);
            } else {
                List<StoryDetail> storyDetailList = storyDetailService.queryByStoryId(storyId);
                storyInfo.setStoryDetailList(storyDetailList);
            }
        }

        return storyInfo;
    }

    public Integer save(StoryInfo storyInfo) {
        if (storyInfo.isNew()) {
            storyInfoMapper.insert(storyInfo);
        } else {
            storyInfoMapper.update(storyInfo);
        }

        return storyInfo.getId();
    }

    synchronized public File exportStory(Integer id, int topN) throws IOException{
        File file = null;
        if(topN == Integer.MAX_VALUE){
            file = new File(Constants.BASE_STORY_TEMP + "/" + id + "_all.txt");
        }else{
            file = new File(Constants.BASE_STORY_TEMP + "/" + id + "_topN.txt");
        }
        
        if(file != null && file.exists()){
            if(System.currentTimeMillis() - file.lastModified() < 86400000){//文件超过1天的，重先生成新的文件
                return file;
            }
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
            StoryInfo storyInfo = queryById(id);
            if(storyInfo != null){
                String temp = null;
                int index = 0;
                if(storyInfo.getStoryDetailList().size() > 0){
                    bw.write("#多多儿小说网(Dodoer)http://www.dodoer.com\n");
                    bw.write("#多多儿小说网(Dodoer)移动版http://m.dodoer.com\n");
                    bw.write("#本站收录大量经典好看的小说，在线阅读，TXT小说下载，一切尽在多多儿小说网\n");
                    bw.write("#本站下载的小说TXT文本只供测试，不作商业用途，下载后请二十四小时后请删除\n");
                    bw.write("#详细信息请查看http://www.dodoer.com/main/" + id + ".html\n");
                    
                    bw.write("\n");
                    bw.write( "《"+ storyInfo.getTitle() + "》\n");
                    bw.write("作者:" + storyInfo.getAuthor() + "\n");
                    if(storyInfo.getOutline() != null){
                        bw.write("简介:" + storyInfo.getOutline() + "\n");
                    }
                    bw.write("\r\n");
                    
                    for(StoryDetail detail : storyInfo.getStoryDetailList()){
                        StoryDetail storyDetail = storyDetailService.queryById(detail.getId(), id);
                        temp = storyDetail.getContent();
                        if(temp != null){
                            temp = temp.replace("<br><br>", "\n");
                            temp = temp.replace("<br/><br/>", "\n");
                            temp = temp.replace("<br>", "\n");
                            temp = temp.replace("<br/>", "\n");
                            temp = temp.replace("&nbsp;", "");
                            bw.write("\n");    
                            bw.write(storyDetail.getTitle() + "\n");
                            bw.write(temp);
                            index++;
                            if(index > topN){
                                bw.write("\n");    
                                bw.write("欢迎注册会员，下载完整版本，谢谢");
                                bw.write("#多多儿小说网(Dodoer)http://www.dodoer.com\n");
                                bw.write("\n");    
                                break;
                            }
                        }
                    }
                    bw.write("\n");    
                    bw.write("@END\n");    
                    bw.write("\n");    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(bw != null){
                bw.flush();
                bw.close();
                bw = null;
            }
        }
        
        return file;
    }
    
    //查询大于此ID的所有作者名
    public List<String> queryAllAuthorByGTId(Integer id) {
        return storyInfoMapper.queryAllAuthorByGTId(id);
    }

    public List<StoryInfo> queryByAuthorName(String author) {
        return storyInfoMapper.queryByAuthorName(author);
    }

    @Override
    protected BaseMapper getMapper() {
        return storyInfoMapper;
    }

    public PageModel<StoryInfo> list(int pageIndex, int pageSize) {
        PageModel<StoryInfo> page = new PageModel<StoryInfo>(pageIndex, pageSize);
        super.list(page);
        return page;
    }
}
