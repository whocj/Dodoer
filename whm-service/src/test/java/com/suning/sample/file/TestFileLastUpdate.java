package com.suning.sample.file;

import java.io.File;

public class TestFileLastUpdate {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        File file = new File("D:/opt/dodoer/temp/story/110.txt");
        System.out.println(file);
        System.out.println(file.exists());
        System.out.println(file.lastModified());
        System.out.println(System.currentTimeMillis() - file.lastModified());
    }

}
