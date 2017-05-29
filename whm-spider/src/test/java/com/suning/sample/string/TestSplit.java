package com.suning.sample.string;

import java.util.Arrays;

public class TestSplit {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String a = "http://www.bxwx8.org/b/115/115957/index.html";
        System.out.println(Arrays.toString(a.split("/")));
        
        a = "您当前的位置：首页 - > 都市言情 - > 我家王爷总坑我";
        System.out.println(a.split(">")[1].replace("-", "").trim());
        
        a = "/html/86/86867/";
        System.out.println(a.split("/")[3]);
        
        a = "/files/chinese/201103/1974/45875.html";
        System.out.println(a.split("/").length);
        
        a = "http://www.klxsw.com/files/article/html/36/36848/";
        System.out.println(a.split("/").length);
        System.out.println(a.split("/").length + "," +  a.split("/")[7]);
        
        a = "小说更新时间:2016-12-15爱有声小说网 » 校园小说 » BOSS来袭：娇妻躺下，别闹！最新章节无弹窗";
        a = a.replace(" ", "");
       String[] strs =  a.split("»");
        System.out.println(strs[1]);
    }

}
