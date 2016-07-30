package com.summer.whm.service.analyzer.library.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.dic.Dictionary;

import com.summer.whm.service.analyzer.dict.DictConfig;
import com.summer.whm.service.analyzer.library.Forest;
import com.summer.whm.service.analyzer.library.GetWord;
import com.summer.whm.service.analyzer.library.Library;

public class LibraryTool {

    private static final Logger logger = LoggerFactory.getLogger(LibraryTool.class);

    private static LibraryTool libraryTool = new LibraryTool();

    private Forest forest = null;

    private LibraryTool() {

        InputStream isen = Dictionary.class.getResourceAsStream("/" + DictConfig.DICT_ROOT + DictConfig.DICT_IT_EN);
        InputStream iscn = Dictionary.class.getResourceAsStream("/" + DictConfig.DICT_ROOT + DictConfig.DICT_IT_CN);
        try {
            forest = Library.makeForest(isen, iscn);
        } catch (Exception e) {
            logger.error("词典初始化失败", e);
        }
    }

    public static LibraryTool getInstance() {
        return libraryTool;
    }

    public List<String> getTerm(String content) {
        if (forest != null && content != null) {
            List<String> strList = new ArrayList<String>();
            GetWord udg = forest.getWord(content);
            String temp = null;
            while ((temp = udg.getFrontWords()) != null) {
                if(temp.length() > 2){
                    strList.add(temp);
                }
            }
            return strList;
        }
        return null;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String content = "java";
        List<String> udg = LibraryTool.getInstance().getTerm(content);
        System.out.println(udg);
    }
}
