package com.summer.whm.spider.service;

import java.util.HashMap;
import java.util.Map;

public class SpiderSiteCategoryService {

    private static Map<String, Integer> CATETORY_MAP = null;

    private static Map<Integer, Map<String, Integer>> TEMPLATE_SITE_MAP = new HashMap<Integer, Map<String, Integer>>();

    static{
        CATETORY_MAP = new HashMap<String, Integer>();
        CATETORY_MAP.put("玄幻小说", 30);
        CATETORY_MAP.put("修真小说", 29);
        CATETORY_MAP.put("武侠小说", 29);
        CATETORY_MAP.put("历史小说", 27);
        CATETORY_MAP.put("都市小说", 28);
        CATETORY_MAP.put("网游小说", 26);
        CATETORY_MAP.put("科幻小说", 25);
        CATETORY_MAP.put("恐怖小说", 25);
        CATETORY_MAP.put("穿越小说", 32);
        CATETORY_MAP.put("言情小说", 32);
        CATETORY_MAP.put("校园小说", 31);

        TEMPLATE_SITE_MAP.put(3, CATETORY_MAP);
    }
    
    public static Map<String, Integer> getCategoryMapByTemplateId(Integer templateId) {
        return TEMPLATE_SITE_MAP.get(templateId);
    }
}
