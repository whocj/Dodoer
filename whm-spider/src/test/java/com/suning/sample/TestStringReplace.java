package com.suning.sample;

public class TestStringReplace {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String str = "《仙门弃少》第二十八章 劫持";
        System.out.println("《" + "﻿仙门弃少" + "》");
        System.out.println(str.replace("《" + "﻿仙门弃少" + "》", ""));
        System.out.println("<br/><br/> &nbsp;&nbsp;&nbsp;&nbsp;".startsWith("<br/>"));
        System.out.println("<br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;1");
        System.out.println("<br/><br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;1".replaceFirst("<br/>", ""));
    }

}
