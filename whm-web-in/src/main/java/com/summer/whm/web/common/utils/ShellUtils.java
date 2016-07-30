package com.summer.whm.web.common.utils;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class ShellUtils {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) {
        
    }

    /**
     * 运行shell
     * 
     * @param shStr 需要执行的shell
     * @return
     * @throws IOException
     */
    public static List<String> execShell(String shStr) throws Exception {
        List<String> strList = new ArrayList<String>();

        Process process;
        process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", shStr }, null, null);
        InputStreamReader ir = new InputStreamReader(process.getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        process.waitFor();
        while ((line = input.readLine()) != null) {
            strList.add(line);
        }

        return strList;
    }

}
