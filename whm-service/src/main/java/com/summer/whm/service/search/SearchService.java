package com.summer.whm.service.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.index.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.MapContainer;
import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.JsoupUtils;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.service.search.core.LuceneUtils;
import com.summer.whm.service.search.core.QueryBuilder;
import com.summer.whm.service.search.core.SearchEnginer;
import com.summer.whm.service.search.model.DocType;
import com.summer.whm.service.search.model.PostType;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    /**
     * 只有添加文章才插入Lucene索引
     * 
     * @param post
     */
    public void insert(SearchPost post) {
        logger.debug("add post index -->" + post.getTitle());
        SearchEnginer.postEnginer().insert(convert(post));
    }

    /**
     * 只有更新文章才更新Lucene索引
     * 
     * @param post
     * @param affect
     */
    public void update(SearchPost post, boolean affect) {
        if (affect) {
            SearchEnginer.postEnginer().update(new Term("id", post.getDocuemntId()), convert(post));
        }
    }

    public void remove(SearchPost post) {
        SearchEnginer.postEnginer().delete(new Term("id", post.getDocuemntId()));
    }

    public PageModel<MapContainer> search(String word, int pageIndex) {
        PageModel<MapContainer> result = new PageModel<MapContainer>(pageIndex, 15);
        QueryBuilder builder = new QueryBuilder(SearchEnginer.postEnginer().getAnalyzer());
        builder.addShould(DocType.DOC_FIELD_TITLE, word).addMust(DocType.DOC_FIELD_CONTENT, word);
        builder.addLighter(DocType.DOC_FIELD_TITLE, DocType.DOC_FIELD_CONTENT);
        SearchEnginer.postEnginer().searchHighlight(builder, result);

        return result;
    }

    public PageModel<MapContainer> searchByCategory(String word, String category, int pageIndex) {
        PageModel<MapContainer> result = new PageModel<MapContainer>(pageIndex, 15);
        QueryBuilder builder = new QueryBuilder(SearchEnginer.postEnginer().getAnalyzer());
        builder.addShould(DocType.DOC_FIELD_TITLE, word).addShould(DocType.DOC_FIELD_CONTENT, word).addMust(DocType.DOC_FIELD_TAGS, category);
        builder.addLighter(DocType.DOC_FIELD_TITLE, DocType.DOC_FIELD_CONTENT);
        SearchEnginer.postEnginer().searchHighlight(builder, result);

        return result;
    }

    public PageModel<MapContainer> searchByParam(String word, Map<String, String> param, int pageIndex) {
        PageModel<MapContainer> result = new PageModel<MapContainer>(pageIndex, 15);
        QueryBuilder builder = new QueryBuilder(SearchEnginer.postEnginer().getAnalyzer());
        builder.addShould(DocType.DOC_FIELD_TITLE, word).addShould(DocType.DOC_FIELD_CONTENT, word);
        if(param != null && param.size() > 0){
            for(Map.Entry<String, String> entry : param.entrySet()){
                builder.addMust(entry.getKey(), entry.getValue());
            }
        }
        builder.addLighter(DocType.DOC_FIELD_TITLE, DocType.DOC_FIELD_CONTENT);
        SearchEnginer.postEnginer().searchHighlight(builder, result);

        return result;
    }

    public List<MapContainer> searchByParamAndExcludeIds(String word, Map<String, String> param, Set<String> idSet, int count) {
        PageModel<MapContainer> result = new PageModel<MapContainer>(1, count + 15);
        QueryBuilder builder = new QueryBuilder(SearchEnginer.postEnginer().getAnalyzer());
        builder.addShould(DocType.DOC_FIELD_TITLE, word).addShould(DocType.DOC_FIELD_CONTENT, word);
        if(param != null && param.size() > 0){
            for(Map.Entry<String, String> entry : param.entrySet()){
                builder.addMust(entry.getKey(), entry.getValue());
            }
        }
//        builder.addLighter(DocType.DOC_FIELD_TITLE, DocType.DOC_FIELD_CONTENT);
        SearchEnginer.postEnginer().searchHighlight(builder, result);
        List<MapContainer> list = new ArrayList<MapContainer>();
        if(result.getTotalCount() > 0){
            
            if(idSet != null && idSet.size() > 0){
                for(MapContainer container : result.getContent()){
                    String id = container.get(DocType.DOC_FIELD_ID);
                    if(!idSet.contains(id)){
                        list.add(container);
                        if(list.size() == count){
                            break;
                        }
                    }
                }
            }else{
                for(MapContainer container : result.getContent()){
                    list.add(container);
                    if(list.size() == count){
                        break;
                    }
                }
            }
        }

        return list;
    }
    
    private Document convert(SearchPost post) {
        Document doc = new Document();
        doc.add(new Field(DocType.DOC_FIELD_ID, post.getDocuemntId(), LuceneUtils.directType()));
        doc.add(new Field(DocType.DOC_FIELD_OBJID, post.getDocId(), LuceneUtils.searchType(false)));

        doc.add(new Field(DocType.DOC_FIELD_TYPE, post.getType(), LuceneUtils.searchType(false)));
        doc.add(new Field(DocType.DOC_FIELD_TITLE, post.getTitle(), LuceneUtils.searchType()));
        /* 用jsoup剔除html标签 */
        doc.add(new Field(DocType.DOC_FIELD_CONTENT, JsoupUtils.plainText(post.getContent()), LuceneUtils.searchType()));

        doc.add(new Field(DocType.DOC_FIELD_AUTHOR, post.getAuthor(), LuceneUtils.searchType(false)));
        doc.add(new Field(DocType.DOC_FIELD_TAGS, post.getTags(), LuceneUtils.searchType()));

        doc.add(new Field(DocType.DOC_FIELD_CREATETIME, post.getCreateTimeStr(), LuceneUtils.storeType()));
        doc.add(new LongField(DocType.DOC_FIELD_TIMEMILLIS, System.currentTimeMillis(), LuceneUtils.storeLongType()));
        return doc;
    }

    public static void main(String[] args){
        SearchService searchService = new SearchService();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(DocType.DOC_FIELD_AUTHOR, "admin");
        paramMap.put(DocType.DOC_FIELD_TYPE, PostType.POST_TYPE_BLOG);
        Set<String> idSet = new HashSet<String>();
        idSet.add(PostType.POST_TYPE_BLOG + "@" + 223);

        List<MapContainer> containerList = searchService.searchByParamAndExcludeIds("大拿分享wordpress", paramMap, idSet, 6);
        System.out.println(containerList);
    }
}
