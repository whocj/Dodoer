package com.summer.whm.ssh.command.core;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.core.model.Line;
import com.summer.whm.ssh.command.core.thread.ThreadOperate;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;

public class TailCommand extends AbstractCommand {

    private long lastUpdate = 0;

    // 当时行数
    private int currentLine = 0;

    // 当时行数
    private int totalLine = 0;

    private boolean isEnd = false;

    private long currentPos = 0l;
    
    private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5000);

    public TailCommand(){
        super();
    }
    
    public TailCommand(String string, CommandContext context) throws CommandException{
        super(string, context);
    }
    
    public TailCommand(String file) throws CommandException, IOException {
        super();
        if (!StringUtils.isBlank(file)) {
            this.file = new File(file);
            if (!this.file.exists()) {
                this.file = null;
                throw new CommandException(file + "文件不存在。");
            } else if (!this.file.canRead()) {
                this.file = null;
                throw new CommandException(file + "文件没有读权限。");
            }
        }
    }

    public BlockingQueue<String> tail() throws Exception {
        if (file == null || !file.canRead()) {
            throw new CommandException("文件不存在或文件没有读权限。");
        }

        new Thread(new TailThread(this)).start();
        return queue;
    }

    private boolean isUpdate() {
        if (lastUpdate == this.file.lastModified()) {
            return false;
        }
        lastUpdate = this.file.lastModified();

        return true;
    }

    public void stop() {
        isEnd = true;
    }

    class TailThread implements Runnable,ThreadOperate {

        TailCommand tailCommand = null;

        public TailThread(TailCommand tailCommand) {
            super();
            this.tailCommand = tailCommand;
        }
        
        @Override
        public void run() {
            synchronized (this) {
                totalLine = getTotalLines();
                while (!isEnd) {
                    try {
                        while (isUpdate() && !isEnd) {
                            // 使用随机读取
                            RandomAccessFile fileRead = null;
                            //使用读模式
                            fileRead = new RandomAccessFile(file, "r");
                            //读取文件长度
                            long length = fileRead.length();
                            //如果是0，代表是空文件，直接返回空结果
                            if (length > 0L) {
                                //首先展示倒数30行数据
                                if(currentPos == 0l){
                                    //初始化游标
                                    long pos = length - 1;
                                    int countLine = 0;
                                    byte b = '\0';
                                    currentPos = length + 1;
                                    currentLine = totalLine;
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
                                            if (countLine == 30) {
                                                break;
                                            }
                                        }
                                    }
                                    
                                    if(tempList.size() > 0){
                                        String temp = null;
                                        int len = tempList.size();
                                        int tempCurrentLine = totalLine - len;
                                        for(int i = len - 1; i >= 0; i--){
                                            tempCurrentLine++;
                                            temp = tempList.get(i);
                                            temp = tailCommand.next(temp);
                                            if (temp != null) {
                                                temp = tailCommand.format(new Line(tempCurrentLine, temp));
                                                tailCommand.queue.add(temp);
                                            }
                                            
                                        }
                                    }
                                }else{
                                    //有新数据迭代展示
                                    if(length > currentPos){
                                        byte b = '\0';
                                        String temp = null;
                                        while (currentPos < length) {
                                            currentPos++;
                                            //开始读取
                                            fileRead.seek(currentPos);
                                            
                                            try{
                                                b = fileRead.readByte();
                                            }catch(EOFException e){
                                                System.out.println("文件读取结束.");
                                                break;
                                            }
                                            
                                          //如果读取到\n代表是读取到一行
                                            if (b == '\n') {
                                                //使用readLine获取当前行
                                                temp = fileRead.readLine();
                                                if(temp == null){
                                                    break;
                                                }
                                                //乱码转换
                                                temp =  new String(temp.getBytes("ISO-8859-1"), "utf-8");
                                                
                                                currentLine++;
                                                //打印当前行
                                                temp = tailCommand.next(temp);
                                                if (temp != null) {
                                                    temp = tailCommand.format(new Line(currentLine, temp));
                                                    tailCommand.queue.add(temp);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //等特5秒
                        wait(5 * 1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
        
        @Override
        public void stop() {
            isEnd = true;
        }
    }
}
