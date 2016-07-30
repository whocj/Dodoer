package com.summer.whm.service.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.category.CategoryUser;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.category.CategoryUserMapper;
import com.summer.whm.service.BaseService;

@Service
public class CategoryUserService extends BaseService {

    @Autowired
    private CategoryUserMapper categoryUserMapper;

    @Override
    protected BaseMapper getMapper() {
        return this.categoryUserMapper;
    }

    public void cleanByCategoryId(Integer categoryId) {
        categoryUserMapper.cleanByCategoryId(categoryId);
    }

    public List<CategoryUser> queryByCategoryId(Integer categoryId) {
        return categoryUserMapper.queryByCategoryId(categoryId);
    }
}
