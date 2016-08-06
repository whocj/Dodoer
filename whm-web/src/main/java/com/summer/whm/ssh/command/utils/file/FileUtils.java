package com.summer.whm.ssh.command.utils.file;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.summer.whm.ssh.command.utils.StringUtils;

public class FileUtils {
    //最大显示500行数据
    private static final int SHOW_MAX_SIZE = 500;
    
    public static String getOSName() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        return os;
    }

    public static boolean isWindow() {
        String os = getOSName();
        if (os != null) {
            if (os.toLowerCase().indexOf("win") != -1) {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    public static String getPath(String path, String file) {
        String tempPath = "";

        if (StringUtils.isBlank(file)) {
            file = "";
        }

        // 如果是win系统，且有：，则进入根目录
        if (isWindow()) {
            if (file.indexOf(":") != -1) {
                path = "";
            }
        } else {
            if (file.startsWith("/")) {
                path = "";
            }

            if (file.startsWith("\\")) {
                path = "";
            }
        }

        if (!StringUtils.isBlank(path)) {
            tempPath = path;
        } else {
            tempPath = "/";
        }

        if ("..".equals(file)) {
            File f = new File(tempPath);
            if (f.getParent() == null) {
                return tempPath;
            } else {
                return f.getParent();
            }
        }

        if (tempPath.endsWith("/") && file.startsWith("/")) {
            tempPath = tempPath + file.substring(1);
        } else if (tempPath.endsWith("/") && !file.startsWith("/")) {
            tempPath = tempPath + file;
        } else if (!tempPath.endsWith("/") && file.startsWith("/")) {
            tempPath = tempPath + file;
        } else if (!tempPath.endsWith("/") && !file.startsWith("/")) {
            tempPath = tempPath + "/" + file;
        }

        return tempPath;
    }
    
    //没找到更好的方法
    public static int getTotalLines(File file) {
        FileReader in = null;
        int totalLines = 0;
        try {
            in = new FileReader(file);
            LineNumberReader reader = new LineNumberReader(in);
            String strLine = reader.readLine();
            
            while (strLine != null) {
                totalLines++;
                strLine = reader.readLine();
            }
            reader.close();
            in.close();
            System.out.println(file.getName() + "共" + totalLines + "行数据。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalLines;
    }
    
    public static List<String> readFile(File file, int topN){
        try {
            //使用随机读取
            RandomAccessFile fileRead = null;
            //使用读模式
            fileRead = new RandomAccessFile(file, "r");
            //读取文件长度
            long length = fileRead.length();
            //初始化游标
            long pos = length - 1;
            int countLine = 0;
            byte b = '\0';

            int tempCount = topN > SHOW_MAX_SIZE ? SHOW_MAX_SIZE : topN;
            
            String line = null;
            List<String> tempList = new ArrayList<String>();
            while (pos > 0) {
                pos--;
                //开始读取
                fileRead.seek(pos);
                try{
                    b = fileRead.readByte();
                }catch(EOFException e){
                    System.out.println("文件读取结束.");
                    break;
                }
                
                //如果读取到\n代表是读取到一行
                if (b == '\n') {
                    //使用readLine获取当前行
                    line = fileRead.readLine();
                    if(line == null){
                        break;
                    }
                    line =  new String(line.getBytes("ISO-8859-1"), "utf-8");
                    countLine++;
                    //保存结果
                    tempList.add(line);
                    
                    //行数统计，如果到达了numRead指定的行数，就跳出循环
                    if (countLine == tempCount) {
                        break;
                    }
                }
            }

            return tempList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
