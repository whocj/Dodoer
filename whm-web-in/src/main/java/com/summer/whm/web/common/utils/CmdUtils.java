package com.summer.whm.web.common.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CmdUtils {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        String path = "D:/Server/apache-tomcat-7.0.26/bin/startup.bat";
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("cmd.exe /k start " + path);

            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = null;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
