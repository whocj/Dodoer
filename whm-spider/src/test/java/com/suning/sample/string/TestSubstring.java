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
    }

}
