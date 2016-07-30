package com.summer.whm.service.search.utils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StringComparator implements Comparator<String> {
    Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

    public int compare(String o1, String o2) {
        if (cmp.compare(o1, o2) > 0) {
            return 1;
        } else if (cmp.compare(o1, o2) < 0) {
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("海尔");
        list.add("三门");
        list.add("冰箱");

        // 正序
        Collections.sort(list, new StringComparator());
        System.out.println("中文名称正序排列：");
        for (String pp : list) {
            System.out.println(pp);
        }
        System.out.println("---------------神奇的分割线--------------------");
        // 倒序
        Collections.reverse(list);
        System.out.println("中文名称倒序排列：");
        for (String pp : list) {
            System.out.println(pp);
        }
        System.out.println("---------------神奇的分割线--------------------");
        Collections.sort(list, Collections.reverseOrder(new StringComparator()));
        System.out.println("中文名称倒序排列：");
        for (String pp : list) {
            System.out.println(pp);
        }
        System.out.println(list.toString());
    }
}
