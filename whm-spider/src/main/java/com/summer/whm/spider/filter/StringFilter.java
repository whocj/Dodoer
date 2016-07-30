package com.summer.whm.spider.filter;

import org.apache.commons.lang3.StringUtils;

public class StringFilter {
    
    private String[] filterWord = null;
    
    public StringFilter(String filterWord){
        String[] strs = filterWord.split("!!!");
        this.filterWord = strs;
    }
    
    public StringFilter(String[] filterWord){
        this.filterWord = filterWord;
    }
    
    public String filter(String src){
        if(StringUtils.isEmpty(src)){
            return "";
        }
        
        for(String str : filterWord){
            src =  src.replaceAll(str, "");
        }
        
        return src;
    }
}
