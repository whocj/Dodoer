package com.summer.whm.mapper.spider;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.spider.CrawInfo;
import com.summer.whm.mapper.BaseMapper;

public interface CrawInfoMapper extends BaseMapper {
    CrawInfo queryByUrl(@Param("url") String url);
}
