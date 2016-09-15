package com.summer.whm.service.author;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.Constants;
import com.summer.whm.entiry.author.Author;
import com.summer.whm.entiry.author.AuthorDetail;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.author.AuthorDetailMapper;
import com.summer.whm.mapper.author.AuthorMapper;
import com.summer.whm.service.BaseService;

/**
 * 
 * 作者词条<br>
 * 
 * @author 11041810
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class AuthorService extends BaseService {

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorDetailMapper authorDetailMapper;

    @Override
    protected BaseMapper getMapper() {
        return authorMapper;
    }

    public Integer save(Author author) {
        if (author.isNew()) {
            authorMapper.insert(author);
        } else {
            authorMapper.update(author);
        }

        return author.getId();
    }

    public List<Author> queryByName(String name) {
        List<Author> authorList = authorMapper.queryByName(name);
        if (authorList != null && authorList.size() > 0) {
            Author author = authorList.get(0);
            List<AuthorDetail> detailList = authorDetailMapper.queryByAuthorId(author.getId());
            author.setDetailList(detailList);
        }

        return authorList;
    }

    public Author queryById(Integer id) {
        Author author = authorMapper.loadById(id + "");
        if (author != null) {
            List<AuthorDetail> detailList = authorDetailMapper.queryByAuthorId(author.getId());
            author.setDetailList(detailList);
        }

        return author;
    }

    public Map<String, List<Author>> queryAllByGroupNamePrefix(Integer categoryId) {
        List<Author> authorList = authorMapper.queryAll(categoryId);
        Map<String, List<Author>> map = new LinkedHashMap<String, List<Author>>();

        String[] ABC_ARRAY = Constants.ABC_ARRAY;
        for (String str : ABC_ARRAY) {
            map.put(str, new ArrayList<Author>());
        }

        if (authorList != null) {
            String prefix = null;
            for (Author author : authorList) {
                if (Constants.IS_INT_TRUE == author.getHot()) {
                    map.get("Hot").add(author);
                } else {
                    if (author.getNameen() != null && author.getNameen().length() > 0) {
                        prefix = author.getNameen().substring(0, 1);
                        if (map.get(prefix.toUpperCase()) != null) {
                            map.get(prefix.toUpperCase()).add(author);
                        } else {
                            map.get("其他").add(author);
                        }
                    } else {
                        map.get("其他").add(author);
                    }
                }
            }
        }

        return map;
    }

    public List<Author> queryTopHot(Integer categoryId, Integer topN) {
        return authorMapper.queryTopHot(categoryId, topN);
    }

    public List<Author> queryAll(Integer categoryId) {
        return authorMapper.queryAll(categoryId);
    }

    public void addLike(Integer id) {
        authorMapper.addLike(id);
    }

    public void addRead(Integer id) {
        authorMapper.addRead(id);
    }

    public void addReply(Integer id) {
        authorMapper.addReply(id);
    }
}
