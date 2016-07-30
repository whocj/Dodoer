package com.summer.whm.service.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.search.SearchPostMapper;
import com.summer.whm.service.BaseService;

@Service
public class SearchPostService extends BaseService{
    
    @Autowired
    private SearchPostMapper searchPostMapper;
    
    @Override
    protected BaseMapper getMapper() {
        return searchPostMapper;
    }

}
