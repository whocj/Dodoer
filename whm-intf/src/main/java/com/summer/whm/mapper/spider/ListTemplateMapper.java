package com.summer.whm.mapper.spider;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.spider.ListTemplate;
import com.summer.whm.mapper.BaseMapper;

public interface ListTemplateMapper extends BaseMapper{
    ListTemplate queryByCrawTemplateId(@Param("crawTemplateId") Integer crawTemplateId);
}
