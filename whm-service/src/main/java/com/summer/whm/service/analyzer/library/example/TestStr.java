package com.summer.whm.service.analyzer.library.example;

import com.summer.whm.service.analyzer.library.Forest;
import com.summer.whm.service.analyzer.library.GetWord;
import com.summer.whm.service.analyzer.library.Library;

public class TestStr {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /**
         * 词典的构造.一行一个词后面是参数.可以从文件读取.可以是read流.
         */

        String fileEn = "D:/workspace/Lucene/lucene/whm-service/src/main/resources/Dict/ik/9-it-en.dic";
        String fileCn = "D:/workspace/Lucene/lucene/whm-service/src/main/resources/Dict/ik/9-it-cn.dic";
        Forest forest = Library.makeForest(fileEn, fileCn);
        /**
         * 删除一个单词
         */
        //Library.removeWord(forest, "中国");
        /**
         * 增加一个新词
         */
        //Library.insertWord(forest, "中国人");
        String content = "s";
        GetWord udg = forest.getWord(content);

        String temp = null;
        while ((temp = udg.getAllWords()) != null) {
            System.out.println(temp);
        }
    }
}
