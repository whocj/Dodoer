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
    }

}
