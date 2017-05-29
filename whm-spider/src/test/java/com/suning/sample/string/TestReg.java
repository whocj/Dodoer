package com.suning.sample.string;

import java.util.regex.Pattern;

public class TestReg {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String reg = "^\\d+$";
        System.out.println(Pattern.compile(reg).matcher("d123").find());
    }

}
