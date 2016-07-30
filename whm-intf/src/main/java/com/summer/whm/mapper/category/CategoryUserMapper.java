package com.summer.whm.mapper.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.category.CategoryUser;
import com.summer.whm.mapper.BaseMapper;

public interface CategoryUserMapper extends BaseMapper {

    void cleanByCategoryId(@Param("categoryId") Integer categoryId);

    List<CategoryUser> queryByCategoryId(@Param("categoryId") Integer categoryId);
}
