package com.summer.whm.web.controller.common;

import java.util.HashMap;
import java.util.Map;

public class ForwardManager {
    public static Map<String, String> FORWARD_MAP = new HashMap<String, String>();;

    static{
        FORWARD_MAP.put("index.ftl", "m/index.ftl");
        FORWARD_MAP.put("story/list/list_index.ftl", "m/story/list/list_index.ftl");
        FORWARD_MAP.put("story/main/story_info_index.ftl", "m/story/main/story_info_index.ftl");
        FORWARD_MAP.put("story/detail/story_detail_index.ftl", "m/story/detail/story_detail_index.ftl");
        FORWARD_MAP.put("story/bookshelf/bookshelf_index.ftl", "m/story/bookshelf/list_index.ftl");
        FORWARD_MAP.put("login.ftl", "m/login.ftl");
    }

    public static String getForward(String forward, boolean isMobile){
        if(isMobile){
           if(FORWARD_MAP.get(forward) != null){
               return FORWARD_MAP.get(forward);
           }
        }
        
        return forward;
    }
}
