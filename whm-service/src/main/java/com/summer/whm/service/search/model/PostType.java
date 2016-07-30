package com.summer.whm.service.search.model;

import java.util.HashMap;
import java.util.Map;

public class PostType {
    public static final String POST_TYPE_QUESTION = "Question";

    public static final String POST_TYPE_ANSWER = "Answer";

    public static final String POST_TYPE_TOPIC = "Topic";

    public static final String POST_TYPE_POST = "Post";

    public static final String POST_TYPE_BLOG = "Blog";

    public static final Map<String, String> POST_URL_PREFIX = new HashMap<String, String>();

    static {
        POST_URL_PREFIX.put(POST_TYPE_QUESTION, "/q/detail/%s.html");
        POST_URL_PREFIX.put(POST_TYPE_TOPIC, "/t/detail/%s.html");
        POST_URL_PREFIX.put(POST_TYPE_BLOG, "/b/detail/%s.html");
    }
}
