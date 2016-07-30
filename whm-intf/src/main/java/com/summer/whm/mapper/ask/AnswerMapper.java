package com.summer.whm.mapper.ask;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.summer.whm.entiry.ask.Answer;
import com.summer.whm.mapper.BaseMapper;

public interface AnswerMapper extends BaseMapper {
    List<Answer> queryTopNum(@Param("num") Integer num);

    List<Answer> queryByQuestionId(@Param("questionId") Integer questionId);
    
    void updateIsGood(@Param("id") Integer id);
}