package com.summer.whm.service.ask;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.common.utils.JsoupUtils;
import com.summer.whm.common.utils.TDKModel;
import com.summer.whm.common.utils.TDKTools;
import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.ask.QuestionText;
import com.summer.whm.entiry.category.Category;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.ask.AnswerMapper;
import com.summer.whm.mapper.ask.QuestionMapper;
import com.summer.whm.mapper.ask.QuestionTextMapper;
import com.summer.whm.mapper.category.CategoryMapper;
import com.summer.whm.mapper.search.SearchPostMapper;
import com.summer.whm.service.BaseService;
import com.summer.whm.service.search.model.PostType;

@Service
public class QuestionService extends BaseService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionTextMapper questionTextMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private SearchPostMapper searchPostMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    
    @Autowired
    private HtmlUtils htmlUtils;
    
    @Cacheable(value = "webCache", key = "#pageIndex + '@' + #pageSize + 'QuestionService.list'")
    public PageModel<Question> list(int pageIndex, int pageSize) {
        PageModel<Question> page = new PageModel<Question>(pageIndex, pageSize);
        super.list(page);
        return page;
    }

    @Cacheable(value = "webCache", key = "#pageIndex + '@' + #pageSize + 'QuestionService.listOrderByHot'")
    public PageModel<Question> listOrderByHot(int pageIndex, int pageSize) {
        PageModel<Question> page = new PageModel<Question>(pageIndex, pageSize);
        List<Question> content = questionMapper.listOrderByHot(page);
        page.setContent(content);
        return page;
    }
    
    @Cacheable(value = "webCache", key = "#pageIndex + '@' + #pageSize + 'QuestionService.listByNotAnswer'")
    public PageModel<Question> listByNotAnswer(int pageIndex, int pageSize) {
        PageModel<Question> page = new PageModel<Question>(pageIndex, pageSize);
        List<Question> content  = questionMapper.listByNotAnswer(page);
        page.setContent(content);
        return page;
    }
    
    @Cacheable(value = "webCache", key = "#categoryId + '@' + #pageIndex + '@' + #pageSize + 'QuestionService.list'")
    public PageModel<Question> list(Integer categoryId, int pageIndex, int pageSize) {
        PageModel<Question> page = new PageModel<Question>(pageIndex, pageSize);
        page.insertQuery("categoryId", categoryId);
        List<Question> questionList = queryByCategoryId(page);
        page.setContent(questionList);
        return page;
    }

    public Question save(Question question) {
        question.setQuestionTitle(htmlUtils.replace(question.getQuestionTitle()));
        question.setQuestionContent(htmlUtils.replace(question.getQuestionContent()));
        question.setTagName(htmlUtils.replace(question.getTagName()));
        if(question.isNew()){
            TDKModel model = TDKTools.getTDK(new TDKModel(question.getQuestionTitle(), question.getQuestionContent(), question.getTagName()));
            question.setKeywords(model.getKeywords());
            question.setDescription(model.getDescription());
            question.setReplyCount(0);
            question.setViewCount(0);
            question.setEditCount(0);
            question.setHasGood("0");
            question.setEditTime(new Date());
            if(question.getCategoryId() != null){
                Category category = categoryMapper.loadById(question.getCategoryId() + "");
                if(category != null){
                    question.setTagName(question.getTagName() + "," + category.getTitle());
                }
            }
            questionMapper.insert(question);
            QuestionText questionText = new QuestionText();
            questionText.setQuestionId(question.getId());
            questionText.setQuestionContent(question.getQuestionContent());
            questionText.setQuestionTitle(question.getQuestionTitle());
            questionTextMapper.insert(questionText);

            String tempExcerpt = JsoupUtils.plainText(questionText.getQuestionContent());
            if(tempExcerpt == null){
                tempExcerpt = question.getQuestionContent();
            }
            searchPostMapper.insert(new SearchPost(question.getId() + "", PostType.POST_TYPE_QUESTION, question
                    .getQuestionTitle(), tempExcerpt, question.getUsername(), question.getTagName()));
        }else{
            question.setQuestionContent(htmlUtils.replace(question.getQuestionContent()));
            question.setQuestionTitle(htmlUtils.replace(question.getQuestionTitle()));
            
            question.setEditTime(new Date());
            questionMapper.update(question);
        }

        return question;
    }
    
//    @Cacheable(value = "webCache", key = "#id + 'QuestionService.queryById'")
    public Question queryById(Integer id) {
        Question question = questionMapper.loadById(id + "");
        
        List<Answer> answerList = answerMapper.queryByQuestionId(id);
        if (answerList == null) {
            question.setReplyCount(0);
        } else {
            question.setAnswerList(answerList);
            question.setReplyCount(answerList.size());
        }
        
        Question temp = new Question();
        temp.setId(id);
        
        questionMapper.update(temp);
        
        return question;
    }

    @Cacheable(value = "webCache", key = "#num + 'QuestionService.queryLatestTopNum'")
    public List<Question> queryLatestTopNum(Integer num) {
        List<Question> questionList = questionMapper.queryLatestTopNum(num);
        return questionList;
    }

    @Cacheable(value = "webCache", key = "#num + 'QuestionService.queryFeaturedTopNum'")
    public List<Question> queryFeaturedTopNum(Integer num) {
        List<Question> questionList = questionMapper.queryFeaturedTopNum(num);
        return questionList;
    }
    
    private List<Question> queryByCategoryId(PageModel<Question> page) {
        List<Question> questionList = questionMapper.queryByCategoryId(page);
        return questionList;
    }

    public void updateQuestionHasGood(Integer id){
        questionMapper.updateQuestionHasGood(id);
    }
    
    @Override
    protected BaseMapper getMapper() {
        return this.questionMapper;
    }

}
