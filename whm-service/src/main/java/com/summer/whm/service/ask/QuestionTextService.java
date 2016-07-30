package com.summer.whm.service.ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.ask.QuestionText;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.ask.QuestionTextMapper;
import com.summer.whm.service.BaseService;

@Service
public class QuestionTextService extends BaseService {

    @Autowired
    private QuestionTextMapper questionTextMapper;

    public PageModel<QuestionText> list(int pageIndex, int pageSize) {
        PageModel<QuestionText> page = new PageModel<QuestionText>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.questionTextMapper;
    }

}
