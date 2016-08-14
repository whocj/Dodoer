package com.summer.whm.service.stroy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.entiry.story.StoryDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.entiry.story.StoryPart;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.story.StoryDetailMapper;
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
    private StoryDetailMapper storyDetailMapper;

    @Autowired
    private SearchPostService searchPostService;

    public static final String FIELD_CREATETIME = "createTime";

    public static final String FIELD_LASTUPDATE = "lastUpdate";

    public static final String FIELD_SORTINDEX = "sortIndex";

    public static final String FIELD_REPLYCOUNT = "replyCount";

    public static final String FIELD_READCOUNT = "readCount";

    public static final String FIELD_LIKECOUNT = "likeCount";
    
    public  void addLike(Integer id){
        storyInfoMapper.addLike(id);
    }
    
    public  void addRead(Integer id){
        storyInfoMapper.addRead(id);
    }
    
    public  void addReply(Integer id){
        storyInfoMapper.addReply(id);
    }
    
    public List<StoryInfo> queryTopNByHot(Integer categoryId, Integer topN){
        return storyInfoMapper.queryTopNByHot(categoryId, topN);
    }
    
    public List<StoryInfo> queryStoryInfoOrderCreateTimeTop(Integer categoryId, Integer topN) {
        if (categoryId == null) {
            return storyInfoMapper.queryTopNByOrder(FIELD_CREATETIME, topN);
        } else {
            return storyInfoMapper.queryTopNByOrderAndCategoryId(categoryId, FIELD_CREATETIME, topN);
        }
    }

    public List<StoryInfo> queryStoryInfoOrderlastUpdateTop(Integer categoryId, Integer topN) {
        if (categoryId == null) {
            return storyInfoMapper.queryTopNByOrder(FIELD_LASTUPDATE , topN);
        } else {
            return storyInfoMapper.queryTopNByOrderAndCategoryId(categoryId, FIELD_LASTUPDATE, topN);
        }
    }

    public List<StoryInfo> queryStoryInfoOrderSortIndexTop(Integer categoryId, Integer topN) {
        if (categoryId == null) {
            return storyInfoMapper.queryTopNByOrder(FIELD_LASTUPDATE, topN);
        } else {
            return storyInfoMapper.queryTopNByOrderAndCategoryId(categoryId, FIELD_LASTUPDATE, topN);
        }
    }

    public List<StoryInfo> queryTopReply(Integer categoryId, Integer topN) {
        if (categoryId == null) {
            return storyInfoMapper.queryTopNByOrder(FIELD_REPLYCOUNT , topN);
        } else {
            return storyInfoMapper.queryTopNByOrderAndCategoryId(categoryId, FIELD_REPLYCOUNT, topN);
        }
    }

    public List<StoryInfo> queryTopHot(Integer categoryId, Integer topN) {
        if (categoryId == null) {
            return storyInfoMapper.queryTopNByOrder(FIELD_READCOUNT, topN);
        } else {
            return storyInfoMapper.queryTopNByOrderAndCategoryId(categoryId, FIELD_READCOUNT, topN);
        }
    }

    public List<StoryInfo> queryTopLike(Integer categoryId, Integer topN) {
        if (categoryId == null) {
            return storyInfoMapper.queryTopNByOrder(FIELD_LIKECOUNT , topN);
        } else {
            return storyInfoMapper.queryTopNByOrderAndCategoryId(categoryId, FIELD_LIKECOUNT , topN);
        }
    }

    public StoryInfo queryById(Integer storyId) {
        StoryInfo storyInfo = storyInfoMapper.loadById(storyId + "");
        if (storyInfo != null) {
            List<StoryPart> storyPartList = storyPartMapper.queryByStoryId(storyId);
            if (storyPartList != null && storyPartList.size() > 0) {
                for (StoryPart storyPart : storyPartList) {
                    List<StoryDetail> storyDetailList = storyDetailMapper.queryByPartId(storyPart.getId());
                    storyPart.setStoryDetailList(storyDetailList);
                }
                storyInfo.setStoryPartList(storyPartList);
                storyInfo.setPart(true);
            } else {
                List<StoryDetail> storyDetailList = storyDetailMapper.queryByStoryId(storyId);
                storyInfo.setStoryDetailList(storyDetailList);
            }
        }

        return storyInfo;
    }

    public Integer save(StoryInfo storyInfo) {
        if (storyInfo.isNew()) {
            storyInfoMapper.insert(storyInfo);
            searchPostService.insert(new SearchPost(storyInfo.getId() + "", PostType.POST_TYPE_STORY, storyInfo
                    .getTitle(), storyInfo.getOutline(), storyInfo.getAuthor(), storyInfo.getTagName()));
        } else {
            storyInfoMapper.update(storyInfo);
        }

        return storyInfo.getId();
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
