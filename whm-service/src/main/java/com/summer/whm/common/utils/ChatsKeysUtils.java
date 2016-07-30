package com.summer.whm.common.utils;

import java.util.HashSet;
import java.util.Set;

import com.summer.whm.common.utils.MD5;

public class ChatsKeysUtils {

    public static Set<String> CHATS_TYPE_SET = new HashSet<String>();

    public static String CHATS_TYPE_USER = "User";
    
    public static String CHATS_TYPE_FRIEND = "Friend";

    public static String CHATS_TYPE_GROUP = "Group";

    public static String CHATS_TYPE_TOPIC = "Topic";

    static {
        CHATS_TYPE_SET.add("Friend");
        CHATS_TYPE_SET.add("Topic");
        CHATS_TYPE_SET.add("Group");
        CHATS_TYPE_SET.add("User");
    }

    public static String createKey(Object obj, String type) {
        if(obj != null && CHATS_TYPE_SET.contains(type)){
            return MD5.encode(type + "@" + obj);
        }

        return null;
    }
    
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        System.out.println(createKey("3", CHATS_TYPE_TOPIC));
    }

}
