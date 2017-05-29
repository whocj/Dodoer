package com.suning.sample.string;

public class TestSubstring {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String a = "《斗罗大陆3龙王传说》第四章 入学";
        System.out.println(a.substring(a.lastIndexOf("》") + 1));

        a = "http://www.aiyousheng.com/17612/19471218.html";
        String id = a.substring(a.lastIndexOf("/") + 1, a.lastIndexOf("."));
        System.out.println(id);

        a = "http://www.aiyousheng.com/book/17612.html";
        id = a.substring(a.lastIndexOf("/") + 1, a.lastIndexOf("."));
        System.out.println(id);

        a = "http://www.kanunu8.com/wuxia/201102/1606.html";

        id = a.substring(0, a.lastIndexOf("/") + 1);
        System.out.println(id);
        
        a = "作者：萧鼎   类别：玄幻仙侠   状态：连载中";
        id = a.substring(3, a.indexOf("类别："));
        System.out.println(id.trim());
        id = a.substring(a.lastIndexOf("状态："));
        System.out.println(id.replace("状态：", "").trim());
        
        a = "《三国演义》作者:罗贯中";
        id = a.substring(1, a.indexOf("》"));
        System.out.println(id.trim());
        
        id = a.substring(a.indexOf("作者:") + 3);
        System.out.println(id.trim());
        
        a = "http://www.wenxuemm.com/book/38/38629/index.html";
        id = a.substring(0, a.lastIndexOf("/") + 1);
        System.out.println(id.trim());
        
        a = "海边的卡夫卡在线阅读，村上春树《海边的卡夫卡》";
        if(a.indexOf("《") != -1 && a.indexOf("》") != -1){
            System.out.println(a.substring(a.indexOf("《") + 1, a.indexOf("》")));
        }
        
        a = "来源：　作者：匪我思存 发布时间：2011-04-16";
        if(a.indexOf("作者：") != -1 && a.indexOf("发布时间") != -1){
            System.out.println(a.substring(a.indexOf("作者：") + 3, a.indexOf("发布时间")));
        }
        
        a = "《亿万总裁小小妻》";
        if(a.indexOf("《") != -1 && a.indexOf("》") != -1){
            System.out.println(a.substring(a.indexOf("《") + 1, a.indexOf("》")));
        }
        a = "《斜阳若影》与《净水红莲》时隔一千年，人物性格时代背景都差别很大，所以不用将两文相互联系。至于为什么邹敬阳和黄翎羽死亡时间仅差一年，但穿越到的时空却间隔了一千年的时间，答案是这样的，所谓时空是独立的，那边世界的时间当然不用按着这边世界的时间来算为您提供百折而后弯的小黄（净水红莲）无弹窗广告免费全文阅读，也可以txt全集下载到本地阅读。";
        System.out.println(a.substring(a.indexOf("为您提供")).indexOf("无弹窗广告免费全文阅读") != -1);
     
        a = "http://www.klxsw.com/files/article/html/36/36848/";
        
        int index = a.indexOf("/html/");
        if(index != -1){
            index = index + 6;
        }
        System.out.println(a.substring(index));
        
    }

}
