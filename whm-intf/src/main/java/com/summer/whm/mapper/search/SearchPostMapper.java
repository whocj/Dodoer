package com.summer.whm.mapper.search;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.mapper.BaseMapper;

public interface SearchPostMapper extends BaseMapper {
    List<SearchPost> queryByIsIndexTop100(@Param("isIndex") Integer isIndex);

    void updateIsIndex(Integer id);
    
    List<SearchPost> queryByDocIdAndType(@Param("docId") String docId, @Param("type") String type);
}
