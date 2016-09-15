package com.summer.whm;

import java.util.Locale;

public final class Constants {
    private Constants() {
    }

    public static final String IS_STR_TRUE = "1";

    public static final String IS_STR_FALSE = "0";
    
    public static final Integer IS_INT_TRUE = 1;

    public static final Integer IS_INT_FALSE = 0;
    
    /**
     * 程序默认字符集
     */
    public static final String ENCODING_UTF_8 = "UTF_8";
    /**
     * 定义统一Locale.CHINA,程序中所有和Locale相关操作均默认使用此Locale
     */
    public static final Locale LOCALE_CHINA = Locale.CHINA;

    public static final String COOKIE_CONTEXT_ID = "c_id";
    public static final String COOKIE_USER_NAME = "un";

    /**
     * csrf表单提交token名称
     */
    public static final String CSRF_TOKEN = "CSRFToken";
    /**
     * csrf的cookie名称
     */
    public static final String COOKIE_CSRF_TOKEN = "x-csrf-token";

    public static final Integer SITE_ID_STORY = 4;

    public static final Integer SITE_ID_ASK = 2;

    public static final Integer SITE_ID_BBS = 1;

    public static final Integer SITE_ID_BLOG = 3;

    public static final Integer SITE_ID_STORY_AUTHOR = 100;

    public static final String[] ABC_ARRAY = new String[] { "Hot", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "其他" };

}
