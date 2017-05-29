package com.suning.sample.string;

public class TestMainURL {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String url = "http://www.luoqiu.com/top/allvisit_";
        for (int i = 4; i < 900; i++) {
            System.out.println("\"" + (url + i) + ".html\",");
        }
    }

}
