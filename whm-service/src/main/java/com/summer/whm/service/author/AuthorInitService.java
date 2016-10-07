package com.summer.whm.service.author;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summer.whm.common.utils.PinUtil;
import com.summer.whm.entiry.author.Author;
import com.summer.whm.entiry.author.AuthorDetail;
import com.summer.whm.entiry.story.StoryInfo;
import com.summer.whm.service.stroy.StoryInfoService;

@Service
public class AuthorInitService {

    @Autowired
    private AuthorDetailService authorDetailService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private StoryInfoService storyInfoService;

    public void initAuthor(int id) {
        List<String> authorNameList = storyInfoService.queryAllAuthorByGTId(id);
        List<Author> authorList = null;
        List<StoryInfo> storyInfoList = null;
        List<AuthorDetail> authorDetailList = null;
        if (authorNameList != null && authorNameList.size() > 0) {
            for (String str : authorNameList) {
                authorList = authorService.queryByName(str);
                if (authorList == null || authorList.size() == 0) {

                    Integer authorId = saveAuthor(str);
                    if (authorId != null) {
                        storyInfoList = storyInfoService.queryByAuthorName(str);
                        for (StoryInfo story : storyInfoList) {
                            AuthorDetail authorDetail = authorDetailService.queryByAuthorIdAndStoryId(authorId,
                                    story.getId());
                            if (authorDetail == null) {
                                saveAuthorDetail(authorId, story);
                            }
                        }
                    }
                } else if (authorList != null && authorList.size() == 1) {
                    Author author = authorList.get(0);
                    storyInfoList = storyInfoService.queryByAuthorName(str);
                    authorDetailList = authorDetailService.queryByAuthorId(author.getId());
                    if (storyInfoList != null && storyInfoList.size() > 0) {
                        if (authorDetailList == null || authorDetailList.size() == 0) {
                            for (StoryInfo story : storyInfoList) {
                                saveAuthorDetail(author.getId(), story);
                            }
                        } else {
                            if (authorDetailList.size() > 0) {
                                if (storyInfoList.size() > authorDetailList.size()) {
                                    for (StoryInfo story : storyInfoList) {
                                        AuthorDetail authorDetail = authorDetailService.queryByAuthorIdAndStoryId(
                                                author.getId(), story.getId());
                                        if (authorDetail == null) {
                                            saveAuthorDetail(author.getId(), story);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void saveAuthorDetail(Integer authorId, StoryInfo story) {
        AuthorDetail authorDetail = new AuthorDetail();
        authorDetail.setAuthorId(authorId);
        authorDetail.setStoryId(story.getId());
        authorDetail.setTitle(story.getTitle());
        authorDetail.setOutline(story.getOutline());
        
        authorDetail.setCreator("Admin");
        authorDetail.setCreateTime(new Date());

        authorDetailService.insert(authorDetail);
    }

    public Integer saveAuthor(String str) {
        Author author = new Author();

        author.setNameen(PinUtil.getPyByCn(str));
        author.setName(str);
        author.setCategoryId(34);
        author.setProfession("作家");
        author.setNamezh(str);
        author.setCountry("中国");
        author.setStatus("0");
        author.setHomeplace("中国");
        author.setCreator("Admin");
        author.setCreateTime(new Date());
        Integer id = authorService.save(author);

        return id;
    }

}
