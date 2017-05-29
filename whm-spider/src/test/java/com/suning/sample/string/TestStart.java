package com.suning.sample.string;

public class TestStart {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        System.out.println("/read/24/".startsWith("/read/"));
        System.out.println("/read/24/".equals(""));
        String a = "**作者：书海沧生所";
        System.out.println(a.startsWith("**作者："));
    }

}
