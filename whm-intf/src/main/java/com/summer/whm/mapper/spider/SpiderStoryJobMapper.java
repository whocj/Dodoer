package com.summer.whm.mapper.spider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.spider.SpiderStoryJob;
import com.summer.whm.mapper.BaseMapper;

public interface SpiderStoryJobMapper extends BaseMapper {
    List<SpiderStoryJob> queryByTempateIdAndStatus(@Param("templateId") Integer templateId,
            @Param("status") String status);

    SpiderStoryJob queryByUrl(@Param("url") String url);
}
