package com.suning.sample.string;

import com.summer.whm.spider.utils.Hex;

public class TestHex {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String url = "http://s8.static.jjwxc.net/novelimage.php?novelid=431273";
        System.out.println(Hex.str2HexStr(url).replace(" ", ""));
    }

}
