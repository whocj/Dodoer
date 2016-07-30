package com.summer.whm.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagUtils {
    
    public static List<String> getTagList(String tagName) {
        if (tagName != null && !"".equals(tagName.trim())) {
            String temp = null;
            List<String> list = new ArrayList<String>();
            temp = tagName.replaceAll("，", ",");
            String[] tags = temp.split(",");
            list = Arrays.asList(tags);
            return list;
        }

        return null;
    }
}
