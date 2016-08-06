package com.suning.sample.string;

public class TestReplace {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String a = "一秒记住【?书?阅☆屋?www.shuyuewu.com】，為您提供精彩小说阅读。";
        String a2 = "手机用户请浏览m.shuyuewu.com阅读，更优质的阅读体验。";
        
        System.out.println(a.replaceAll("【\\?书\\?阅☆屋\\?www.shuyuewu.com】", "【多多儿#www.dodoer.com】"));
        System.out.println(a2.replaceAll("m.shuyuewu.com", "m.dodoer.com"));
        
        
    }

}
