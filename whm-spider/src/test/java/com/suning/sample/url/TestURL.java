package com.suning.sample.url;

import com.summer.whm.spider.utils.URLUtil;

public class TestURL {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String url = URLUtil.getHost("http://www.shuyuewu.com/kan_75582/13706698.html");
        System.out.println(url);
    }

}
