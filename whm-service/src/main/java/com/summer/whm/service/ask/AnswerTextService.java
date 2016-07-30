package com.summer.whm.service.ask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.ask.AnswerText;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.ask.AnswerTextMapper;
import com.summer.whm.service.BaseService;

@Service
public class AnswerTextService extends BaseService {

    @Autowired
    private AnswerTextMapper answerTextMapper;

    public PageModel<AnswerText> list(int pageIndex, int pageSize) {
        PageModel<AnswerText> page = new PageModel<AnswerText>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Override
    protected BaseMapper getMapper() {
        return this.answerTextMapper;
    }

}
