package com.summer.whm.service.author;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.author.AuthorDetail;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.author.AuthorDetailMapper;
import com.summer.whm.service.BaseService;

/**
 * 
 * 作者小说关系<br>
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class AuthorDetailService extends BaseService {

    @Autowired
    private AuthorDetailMapper authorDetailMapper;

    @Override
    protected BaseMapper getMapper() {
        return authorDetailMapper;
    }

    public List<AuthorDetail> queryByAuthorId(Integer authorId) {
        return authorDetailMapper.queryByAuthorId(authorId);
    }

    public AuthorDetail queryByAuthorIdAndStoryId(Integer authorId, Integer storyId) {
        List<AuthorDetail> list = authorDetailMapper.queryByAuthorIdAndStoryId(authorId, storyId);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        
        return null;
    }
    
    public void deleteByAuthorIdAndStoryId(Integer authorId, Integer storyId){
        authorDetailMapper.deleteByAuthorIdAndStoryId(authorId, storyId);
    }
}
