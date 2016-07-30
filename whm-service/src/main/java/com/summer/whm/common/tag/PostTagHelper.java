package com.summer.whm.common.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.summer.whm.entiry.post.Post;
import com.summer.whm.entiry.sys.Tag;

/**
 * Post标签工具类
 * 
 * @author zhou
 */
public class PostTagHelper{

  private PostTagHelper(){
  }

  /**
   * 
   * @param post
   * @param tagsByComma
   *          以逗号分隔的字符串
   * @param creator
   * @return
   */
  public static List<Tag> from(Post post, String tagsByComma, String creator){
    return !StringUtils.isBlank(tagsByComma) ? from(post, Arrays.asList(tagsByComma.split(",")), creator) : Collections
        .<Tag> emptyList();
  }

  public static List<Tag> from(Post post, List<String> tags, String creator){
    List<Tag> list = new ArrayList<Tag>();
    if(!CollectionUtils.isEmpty(tags)){
      for(String tag : tags){
        Tag t = new Tag();
        t.setName(tag.trim());
        t.setCreateTime(post.getLastUpdate());
        t.setCreator(creator);
        list.add(t);
      }
    }

    return list;
  }

}
