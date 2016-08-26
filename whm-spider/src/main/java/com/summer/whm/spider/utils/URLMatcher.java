package com.summer.whm.spider.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLMatcher {

    static Pattern pattern = Pattern
            .compile("(http://|ftp://|https://|www){0,1}[^\u4e00-\u9fa5\\s]*?\\.[^\u4e00-\u9fa5\\s]{2,4}[^\u4e00-\u9fa5\\s]*");
    
    public static String replaceURL(String src, String desc){
        if(src != null){
         // 空格结束
            Matcher matcher = pattern.matcher(src);
            while (matcher.find()) {
                src = src.replace(matcher.group(0), desc);
            }
        }
        
        return src;
    }
    
    public static void main(String[] args) {
        
        // 空格结束
        Matcher matcher = pattern.matcher("随碟附送下载地址http://www.zuidaima.com/sdfsdf.htm?aaaa=%ee%sss ?sdfsyyy空格结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        // 中文结束
        matcher = pattern.matcher("随碟附送下载地址http://www.zuidaima.com/sdfsdf.htm?aaaa=%ee%sss网址结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        // 没有http://开头
        matcher = pattern.matcher("随碟附送下载地址www.zuidaima.com/sdfsdf.htm?aaaa=%ee%sss网址结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        // net域名
        matcher = pattern.matcher("随碟附送下载地址www.xxx.net/sdfsdf.htm?aaaa=%ee%sss网址结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        // xxx域名
        matcher = pattern.matcher("随碟附送下载地址www.zuidaima.xxx/sdfsdf.htm?aaaa=%ee%sss网址结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        // yyyy域名匹配不到
        System.out.println("匹配不到yyyy域名");
        matcher = pattern.matcher("随碟附送下载地址www.zuidaima.yyyy/sdfsdf.html?aaaa=%ee%sss网址结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        // 没有http://www.
        matcher = pattern.matcher("随碟附送下载地址zuidaima.com/sdfsdf.html?aaaa=%ee%sss网址结束");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        matcher = pattern.matcher("但，上架后，力争每日三更……www.zhuaji.org/book/116");
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }

        System.out.println(replaceURL("但，上架后，力争每日三更……www.zhuaji.org/book/116随碟附送下载地址www.zuidaima.yyyy/sdfsdf.html?aaaa=%ee%sss网址结束随碟附送下载地址zuidaima.com/sdfsdf.html?aaaa=%ee%sss网址结束", ""));
        System.out.println(replaceURL("但http://www.zhuaji.org/book/116", ""));
    }

}
