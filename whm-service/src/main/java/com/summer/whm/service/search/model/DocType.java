package com.summer.whm.service.search.model;

public class DocType {
    public final static String DOC_FIELD_ID = "id";
    public final static String DOC_FIELD_OBJID = "objId";
    public final static String DOC_FIELD_TYPE = "type";
    public final static String DOC_FIELD_TITLE = "title";
    public final static String DOC_FIELD_CONTENT = "content";
    public final static String DOC_FIELD_AUTHOR = "author";
    public final static String DOC_FIELD_TAGS = "tags";
    public final static String DOC_FIELD_CREATETIME = "createTime";
    public final static String DOC_FIELD_TIMEMILLIS = "timeMillis";
    public final static String DOC_FIELD_URL = "url";
    public final static String DOC_FIELD_SITE = "site";
    
    /**
     * 索引字段
     */
    public final static String FIELD_DOCID = "docid";
    public final static String FIELD_KEYWORD = "keyword";
    public final static String FIELD_SRC_KEYWORD = "srckeyword";
    public final static String FIELD_PIN = "pin";
    public final static String FIELD_SPIN = "spin";
    public final static String FIELD_USERID = "userid";
    public final static String FIELD_TIMES = "times";
    public final static String FIELD_CORE = "core";
    
    public final static String FIELD_SORT = "sort";
    public final static String FIELD_COUNT = "count";
    
    public final static String FIELD_IKWORD = "ikword";
    public final static String FIELD_MANUAL = "manual";
    public final static String FIELD_UNIQUE_IKWORD = "unique_ikword";
    
    public final static float BOOST_FIELD_KEYWORD = 5;
    public final static float BOOST_FIELD_PIN = 3;
    public final static float BOOST_FIELD_SPIN = 2;
}
