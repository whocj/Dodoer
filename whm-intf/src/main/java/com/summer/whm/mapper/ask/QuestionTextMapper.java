package com.summer.whm.mapper.ask;

import java.util.List;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.mapper.BaseMapper;

public interface QuestionTextMapper extends BaseMapper {
    List<String> queryTitle(PageModel<String> model);
}
