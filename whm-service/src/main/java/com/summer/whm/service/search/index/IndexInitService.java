package com.summer.whm.service.search.index;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.mapper.search.SearchPostMapper;
import com.summer.whm.service.search.SearchService;

@Service
public class IndexInitService {

    private static final Logger logger = LoggerFactory.getLogger(IndexInitService.class);
    
    @Autowired
    private SearchPostMapper searchPostMapper;
    
    @Autowired
    private SearchService searchService;
    
    private static boolean running = true;
    
    synchronized public void startup() {
        logger.info("索引初始化开始...");
        try {
            while (running) {
                List<SearchPost> searchPostList = searchPostMapper.queryByIsIndexTop100(0);
                if (searchPostList != null && searchPostList.size() > 0) {
                    for (SearchPost post : searchPostList) {
                        try{
                            if(SearchPost.POST_MODEL_INDEX.equals(post.getModel())){
                                searchService.insert(post);
                            }else if(SearchPost.POST_MODEL_DELETE.equals(post.getModel())){
                                searchService.remove(post);
                            }else if(SearchPost.POST_MODEL_UPDATE.equals(post.getModel())){
                                searchService.update(post, true);
                            }
                            
                            searchPostMapper.updateIsIndex(post.getId());
                        }catch(Exception e){
                            logger.error("SearchPost索引创建失败" + post, e);
                        }
                    }

                    if(searchPostList.size() != 100){
                        running = false;
                    }
                } else {
                    running = false;
                }
            }
        } catch (Exception e) {
            running = true;
            logger.error("索引初始化失败...", e);
        }
        logger.info("索引初始化结束...");
    }

}
