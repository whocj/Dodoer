package com.summer.whm.mapper.post;

import java.util.List;

import com.summer.whm.common.model.PageModel;
import com.summer.whm.entiry.post.Post;
import com.summer.whm.mapper.BaseMapper;

public interface PostMapper extends BaseMapper {
    List<Post> queryByTopicId(PageModel<Post> model);
}
