package com.summer.whm.spider.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.summer.whm.spider.model.seed.StorySeed;

public class StorySeedService {

    private static String SEED_PATH_TEMP = "/opt/temp/seed/";

    public static String saveFile(List<StorySeed> storySeedList) {
        if (storySeedList != null && storySeedList.size() > 0) {
            File file = new File(SEED_PATH_TEMP + System.currentTimeMillis() + ".txt");
            try {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                for (StorySeed storySeed : storySeedList) {
                    pw.println(storySeed.asTxt("###"));
                }
                pw.flush();
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        
            return file.getAbsolutePath();
        }
        
        return null;
    }
}
