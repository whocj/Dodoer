package com.suning.sample.string;

public class TestStartWith {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String a =  "《霸道总裁夜夜欢》";
        System.out.println(a.startsWith("《"));
        System.out.println(a.indexOf("《") != -1);
        System.out.println( a.replace("《", ""));
    }

}
