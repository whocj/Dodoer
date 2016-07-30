package com.summer.whm.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.sys.Notice;
import com.summer.whm.mapper.BaseMapper;

public interface NoticeMapper extends BaseMapper {
    List<Notice> queryBySiteId(@Param("siteId") Integer siteId);
}
