package com.summer.whm.mapper.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.category.Category;
import com.summer.whm.mapper.BaseMapper;

public interface CategoryMapper extends BaseMapper {
    List<Category> queryBySite(@Param("siteId") Integer siteId);
}
