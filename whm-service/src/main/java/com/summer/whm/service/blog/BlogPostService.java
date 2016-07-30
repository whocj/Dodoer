package com.summer.whm.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.common.utils.HtmlUtils;
import com.summer.whm.common.utils.TDKModel;
import com.summer.whm.common.utils.TDKTools;
import com.summer.whm.entiry.blog.BlogComment;
import com.summer.whm.entiry.blog.BlogPost;
import com.summer.whm.entiry.search.SearchPost;
import com.summer.whm.mapper.BaseMapper;
import com.summer.whm.mapper.blog.BlogCommentMapper;
import com.summer.whm.mapper.blog.BlogPostMapper;
import com.summer.whm.mapper.search.SearchPostMapper;
import com.summer.whm.service.BaseService;
import com.summer.whm.service.search.model.PostType;

@Service
public class BlogPostService extends BaseService {

    @Autowired
    private BlogPostMapper blogPostMapper;

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private SearchPostMapper searchPostMapper;

    @Autowired
    private HtmlUtils htmlUtils;
    
    @Override
    protected BaseMapper getMapper() {
        return this.blogPostMapper;
    }

//    @Cacheable(value = "webCache", key = "#id + 'BlogPostService.queryById'")
    public BlogPost queryById(Integer id) {
        BlogPost blogPost = blogPostMapper.loadById(id + "");
        if (blogPost != null) {
            List<BlogComment> blogCommentList = blogCommentMapper.queryBlogCommentByPostId(id);
            blogPost.setBlogCommentList(blogCommentList);
            blogPostMapper.updateAddView(id);
        }

        return blogPost;
    }

//    @Cacheable(value = "webCache", key = "#creator + '@' + #id + 'BlogPostService.queryNextByCreatorAndId'")
    public BlogPost queryNextByCreatorAndId(String creator, Integer id){
        return blogPostMapper.queryNextByCreatorAndId(creator, id);
    }
    
//    @Cacheable(value = "webCache", key = "#creator + '@' + #id + 'BlogPostService.queryPrevByCreatorAndId'")
    public BlogPost queryPrevByCreatorAndId(String creator, Integer id){    
        return blogPostMapper.queryPrevByCreatorAndId(creator, id);
    }
    
    @Cacheable(value = "webCache", key = "#num + 'BlogPostService.queryLatestTopNum'")
    public List<BlogPost> queryLatestTopNum(Integer num) {
        return blogPostMapper.queryLatestTopNum(num);
    }
    
    @Cacheable(value = "webCache", key = "#page.pageIndex + '@' + #page.pageSize + 'BlogPostService.queryHotBlogPost'")
    public PageModel<BlogPost> queryHotBlogPost(PageModel<BlogPost> page) {
        List<BlogPost> blogPost = blogPostMapper.queryHotBlogPost(page);
        page.setContent(blogPost);
        return page;
    }

    public PageModel<BlogPost> queryByCreatorId(PageModel<BlogPost> page) {
        List<BlogPost> blogPost = blogPostMapper.queryByCreator(page);
        page.setContent(blogPost);
        return page;
    }

    public void save(BlogPost blogPost) {
        
        blogPost.setTitle(htmlUtils.replace(blogPost.getTitle()));
        blogPost.setTagName(htmlUtils.replace(blogPost.getTagName()));
        blogPost.setContent(htmlUtils.replace(blogPost.getContent()));
        
        if(blogPost.isNew()){
            
            TDKModel model = TDKTools.getTDK(new TDKModel(blogPost.getTitle(), blogPost.getContent(), blogPost.getTagName()));
            blogPost.setKeywords(model.getKeywords());
            blogPost.setDescription(model.getDescription());
            
            blogPostMapper.insert(blogPost);
            SearchPost searchPost = new SearchPost(blogPost.getId() + "", PostType.POST_TYPE_BLOG, blogPost.getTitle(),
                    blogPost.getContentText(), blogPost.getCreator(), blogPost.getTagName());
            searchPost.setIsIndex(0);
            searchPostMapper.insert(searchPost);
        }else{
            blogPostMapper.update(blogPost);
        }
    }
    
//    @CacheEvict(value = "webCache", key = "#id + 'BlogPostService.queryById'")
    public void delete(Integer id) {
        blogPostMapper.deleteById(id + "");
        List<SearchPost> list = searchPostMapper.queryByDocIdAndType(id + "", PostType.POST_TYPE_BLOG);
        if(list != null && list.size() > 0){
            for(SearchPost post : list){
                if(post.getIsIndex() == 0){
                    searchPostMapper.deleteById(post.getId() + "");
                }
            }
        }
        
        searchPostMapper.insert(new SearchPost(id + "", PostType.POST_TYPE_BLOG, SearchPost.POST_MODEL_DELETE));
    }
}
