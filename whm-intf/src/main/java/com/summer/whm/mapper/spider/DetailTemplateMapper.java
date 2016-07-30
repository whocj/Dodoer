package com.summer.whm.mapper.spider;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.spider.DetailTemplate;
import com.summer.whm.mapper.BaseMapper;

public interface DetailTemplateMapper extends BaseMapper{
    DetailTemplate queryByCrawTemplateId(@Param("crawTemplateId") Integer crawTemplateId);
}
