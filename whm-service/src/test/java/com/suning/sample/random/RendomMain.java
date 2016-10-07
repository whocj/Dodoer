package com.suning.sample.random;

import org.apache.commons.lang.math.RandomUtils;

public class RendomMain {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.nextInt(100));
        }
    }

}
