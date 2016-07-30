package com.summer.whm.service.ask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.ask.AnswerText;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.ask.AnswerMapper;
import com.summer.whm.mapper.ask.AnswerTextMapper;
import com.summer.whm.mapper.ask.QuestionMapper;
import com.summer.whm.service.BaseService;

@Service
public class AnswerService extends BaseService {

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private AnswerTextMapper answerTextMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HtmlUtils htmlUtils;
    
    @CacheEvict(value = "webCache", key = "#answer.questionId + 'QuestionService.queryById'")
    public Answer save(Answer answer) {
        answerMapper.insert(answer);

        answer.setAnswerContent(htmlUtils.replace(answer.getAnswerContent()));
        answer.setAnswerTitle(htmlUtils.replace(answer.getAnswerTitle()));
        
        AnswerText answerText = new AnswerText();
        answerText.setAnswerId(answer.getId());
        answerText.setAnswerContent(answer.getAnswerContent());
        answerText.setAnswerTitle(answer.getAnswerTitle());
        answerTextMapper.insert(answerText);
        questionMapper.updateQuestionReplyCount(answer.getQuestionId());
        return answer;
    }

    public PageModel<Answer> list(int pageIndex, int pageSize) {
        PageModel<Answer> page = new PageModel<Answer>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    public List<Answer> queryTopNum(Integer num) {
        List<Answer> answerList = answerMapper.queryTopNum(num);
        return answerList;
    }

    public void updateIsGood(Integer id){
        answerMapper.updateIsGood(id);
    }
    
    @Override
    protected BaseMapper getMapper() {
        return this.answerMapper;
    }

}
