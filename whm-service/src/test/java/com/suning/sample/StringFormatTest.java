package com.suning.sample;

public class StringFormatTest {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String url = "/ask/question/list/%d.html";
        System.out.println(String.format(url, 1));
    }

}
