package com.summer.whm.service.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.MapContainer;
import com.summer.whm.entiry.ask.Question;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.post.Topic;
import com.summer.whm.service.search.model.DocType;
import com.summer.whm.service.search.model.PostType;

@Service
public class SimilarSearchService {

    @Autowired
    private SearchService searchService;

    public List<MapContainer> searchQuestion(Question question) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(DocType.DOC_FIELD_TYPE, PostType.POST_TYPE_BLOG);
        Set<String> idSet = new HashSet<String>();
        idSet.add(PostType.POST_TYPE_QUESTION + "@" + question.getId());

        List<MapContainer> containerList = searchService.searchByParamAndExcludeIds(question.getQuestionTitle(),
                paramMap, idSet, 6);
        return containerList;
    }

    public List<MapContainer> searchTopic(Topic topic) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(DocType.DOC_FIELD_TYPE, PostType.POST_TYPE_BLOG);
        Set<String> idSet = new HashSet<String>();
        idSet.add(PostType.POST_TYPE_TOPIC + "@" + topic.getId());

        List<MapContainer> containerList = searchService.searchByParamAndExcludeIds(topic.getTitle(), paramMap, idSet,
                6);
        return containerList;
    }

    public List<MapContainer> searchBlogByMe(BlogPost blog) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(DocType.DOC_FIELD_AUTHOR, blog.getCreator());
        paramMap.put(DocType.DOC_FIELD_TYPE, PostType.POST_TYPE_BLOG);
        Set<String> idSet = new HashSet<String>();
        idSet.add(PostType.POST_TYPE_BLOG + "@" + blog.getId());

        List<MapContainer> containerList = searchService
                .searchByParamAndExcludeIds(blog.getTitle(), paramMap, idSet, 6);
        return containerList;
    }

    public List<MapContainer> search(String key, Set<String> idSet) {
        Map<String, String> paramMap = new HashMap<String, String>();
        List<MapContainer> containerList = searchService
                .searchByParamAndExcludeIds(key, paramMap, idSet, 6);
        return containerList;
    }

}
