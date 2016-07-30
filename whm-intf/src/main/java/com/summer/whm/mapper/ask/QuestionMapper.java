package com.summer.whm.mapper.ask;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.mapper.BaseMapper;

public interface QuestionMapper extends BaseMapper {
    List<Question> queryLatestTopNum(@Param("num") Integer num);

    List<Question> queryFeaturedTopNum(@Param("num") Integer num);
    
    List<Question> queryByCategoryId(PageModel<Question> model);
    
    List<Question> listOrderByHot(PageModel<Question> model);
    
    List<Question> listByNotAnswer(PageModel<Question> model);
    
    void updateQuestionReplyCount(@Param("id") Integer id);
    
    void updateQuestionHasGood(@Param("id") Integer id);
    
    List<Integer> queryIdByCount(@Param("count") Integer count);
}
