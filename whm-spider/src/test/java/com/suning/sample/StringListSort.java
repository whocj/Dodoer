package com.suning.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringListSort {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(
                "D:/workspace/Lucene/lucene/whm-service/src/main/resources/Dict/ik/9-it-en.dic"))));
        String str = null;
        List<String> strList = new ArrayList<String>();
        Set<String> strSet = new HashSet<String>();
        do {
            str = br.readLine();
            if (str != null) {
                str = str.trim();
               if(str.length() > 2){
                   strSet.add(str);
               }
            }

        } while (str != null);
        strList.addAll(strSet);

        Collections.sort(strList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                int t = o1.compareTo(o2);
                return t;
            }

        });
        for (String s : strList) {
            System.out.println(s);
        }

    }
}
