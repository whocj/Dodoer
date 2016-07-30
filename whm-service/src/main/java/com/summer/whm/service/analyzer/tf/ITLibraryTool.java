package com.summer.whm.service.analyzer.tf;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wltea.analyzer.dic.Dictionary;

import com.summer.whm.service.analyzer.dict.DictConfig;

public class ITLibraryTool {

    private static final Logger logger = LoggerFactory.getLogger(ITLibraryTool.class);

    private static ITLibraryTool libraryTool = new ITLibraryTool();

    private SimpleLibrary library = null;

    private ITLibraryTool() {

        InputStream isen = Dictionary.class.getResourceAsStream("/" + DictConfig.DICT_ROOT + DictConfig.DICT_IT_EN);
        InputStream iscn = Dictionary.class.getResourceAsStream("/" + DictConfig.DICT_ROOT + DictConfig.DICT_IT_CN);
        try {
            library = SimpleLibrary.makeLib(isen, iscn);
        } catch (Exception e) {
            logger.error("词典初始化失败", e);
        }
    }

    public static ITLibraryTool getInstance() {
        return libraryTool;
    }

    public String getTerm(String term) {
        if (library.has(term)) {
            return term;
        }
        
        return null;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String content = "java5";
        String str = ITLibraryTool.getInstance().getTerm(content);
        System.out.println(str);
    }
}
