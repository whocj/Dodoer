package com.summer.whm.service.analyzer.tf;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.service.analyzer.library.utils.IOUtil;

public class SimpleLibrary {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleLibrary.class);

    private Set<String> libSet = null;
    
    private SimpleLibrary() {
        libSet = new HashSet<String>();
    }

    public boolean has(String term){
        if(StringUtils.isNotEmpty(term)){
            return libSet.contains(term);
        }
        
        return false;
    }
    
    public static SimpleLibrary makeLib(InputStream... inputStream) throws Exception {
        if (inputStream != null) {
            SimpleLibrary library = new SimpleLibrary();
            for (InputStream is : inputStream) {
                if (is != null) {
                    makeLibrary(IOUtil.getReader(is, "UTF-8"), library.libSet);
                }
            }
            return library;
        }

        return null;
    }

    /**
     * 词典树的构造方法
     * 
     * @param br
     * @param forest
     * @return
     * @throws Exception
     */
    private static void makeLibrary(BufferedReader br, Set<String> libSet) throws Exception {
        if (br == null) {
            return;
        }
        try {
            String temp = null;
            while ((temp = br.readLine()) != null) {
                libSet.add(temp.trim());
            }
        } catch (Exception e) {
            LOG.error("词典初始化失败。", e);
        } finally {
            br.close();
        }
    }
}
