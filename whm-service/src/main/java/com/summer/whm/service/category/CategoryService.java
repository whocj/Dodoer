package com.summer.whm.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.forum.Forum;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.category.CategoryMapper;
import com.summer.whm.mapper.forum.ForumMapper;
import com.summer.whm.service.BaseService;

@Service
public class CategoryService extends BaseService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ForumMapper forumMapper;

    @Cacheable(value = "webCache", key = "#siteId + 'CategoryService.queryBySite'")
    public List<Category> queryBySite(Integer siteId) {
        List<Category> categoryList = categoryMapper.queryBySite(siteId);
        if (categoryList != null && categoryList.size() > 0) {
            for (Category category : categoryList) {
                List<Forum> forumList = forumMapper.queryByCategoryId(category.getId());
                if(forumList != null && forumList.size() > 0){
                    category.setForumList(forumList);
                    category.setView(1);
                }
                
            }
        }

        return categoryList;
    }

    public PageModel<Category> list(int pageIndex, int pageSize) {
        PageModel<Category> page = new PageModel<Category>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.categoryMapper;
    }

}
