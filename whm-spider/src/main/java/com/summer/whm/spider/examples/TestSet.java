package com.summer.whm.spider.examples;

import java.util.ArrayList;
import java.util.List;

public class TestSet {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>(5);
        for (int i = 0; i < 10; i++) {
            list.add("i" + i);
        }
        System.out.println(list.contains("i10"));

    }
}
