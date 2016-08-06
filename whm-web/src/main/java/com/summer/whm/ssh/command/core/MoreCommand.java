package com.summer.whm.ssh.command.core;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.core.model.Line;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class MoreCommand extends AbstractCommand {
    
    //最大显示500行数据
    private static final int SHOW_MAX_SIZE = 500;
    
    public MoreCommand(){
        super();
    }
    
    public MoreCommand(String string, CommandContext context) throws CommandException{
        super(string, context);
    }
    
    public String doTask(String str) {
        if (file == null && !StringUtils.isBlank(str)) {
            if (str.startsWith("/")) {
                this.file = new File(str);
            } else {
                this.file = new File(FileUtils.getPath(this.context.getCurrentPath(), str));
            }
            if (!file.exists()) {
                return str + "目录不存在。";
            } else if (file.isDirectory()) {
                return str + "是目录。";
            }
        }
        StringBuffer sb = new StringBuffer();
        String temp = null;
        try {
            int totalLine = getTotalLines(); 
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
                    if (countLine == SHOW_MAX_SIZE) {
                        break;
                    }
                }
            }
            
            if(tempList.size() > 0){
                temp = null;
                int len = tempList.size();
                int tempCurrentLine = totalLine - len;
                for(int i = len - 1; i >= 0; i--){
                    tempCurrentLine++;
                    temp = tempList.get(i);
                    temp = next(temp);
                    if (temp != null) {
                        temp = format(new Line(tempCurrentLine, temp));
                        sb.append(temp);
                    }
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
}
