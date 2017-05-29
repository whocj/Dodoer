package com.summer.whm.spider.script.d8qu;

public class Pic {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String url = "http://www.wenxuemm.com/book/38/38629/index.html";
        String[] strs = url.split("/");
        System.out.println(strs[4]);
        System.out.println(strs[5]);
        
        String picUrl = "http://www.wenxuemm.com/files/article/image/38/38629/38629s.jpg";
        
        System.out.println("http://www.wenxuemm.com/files/article/image/" + strs[4] + "/" + strs[5] + "/" + strs[5] + "s.jpg");
        
    }

}
