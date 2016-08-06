package com.suning.sample.web.test.string;

import org.apache.commons.lang.StringEscapeUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.summer.whm.web.controller.chats.model.MessageModel;

public class TestString {

    public static String escapeHtml(String str) {
        if (str != null) {
            return StringEscapeUtils.escapeHtml(str);
        }

        return null;
    }

    public static String escapeJava(String str){
        if(str != null){
           return StringEscapeUtils.escapeJava(str);
        }
        
        return null;
    }

    public static String escapeJavaScript(String str) {
        if (str != null) {
            return StringEscapeUtils.escapeJavaScript(str);
        }

        return null;
    }

    public static String escapeSql(String str) {
        if (str != null) {
            return StringEscapeUtils.escapeSql(str);
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
        MessageModel messageModel = null;
        String jsonStr = "{chatkey:\"f1990e67f2dc2af40abdeb008af5de3b\",acceptName:\"chenjin\",type:\"User\",username:\"admin\",userChatkey:\"bcd1783317c5b90cb5e4674fe8499b75\",content:\"\"你好\"\"}";
        System.out.println(jsonStr);
        jsonStr = escapeSql(jsonStr);
        System.out.println(jsonStr);
        messageModel = JSON.parseObject(jsonStr, MessageModel.class, Feature.IgnoreNotMatch);
        System.out.println(messageModel);
    }

}
