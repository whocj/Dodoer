package com.summer.whm.ssh.command.utils.file;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FileSchedulerTask implements Runnable{
    private boolean firstRun = true;  
    
    private String directory = "";  
    // 初始文件信息  
    private Map<String,Long> currentFiles = new HashMap<String,Long>();  
    // 当前文件信息  
    private Set<String> newFiles = new HashSet<String>();  
      
    /** 
     * 构造函数 
     */  
    public FileSchedulerTask(){  
          
    }  
    public FileSchedulerTask(String directory){  
        this.directory = directory;  
    }  
    /** 
     * 在 run() 中执行具体任务 
     */  
    public void run() {  
        File file = new File(directory);  
        if(firstRun){  
            firstRun = false;  
            // 初次运行  
            loadFileInfo(file);  
            System.out.println("----- init success -----");  
        } else{  
            // 检查文件更新状态[add,update]  
            checkFileUpdate(file);  
            // 检查被移除的文件[remove]  
            checkRemovedFiles();  
            // 清空临时文件集合  
            newFiles.clear();  
        }  
    }  
    /** 
     * 初始化文件信息 
     * @param file 
     */  
    private void loadFileInfo(File file){  
        if(file.isFile()){  
            currentFiles.put(file.getAbsolutePath(), file.lastModified());  
            return;  
        }  
        File[] files = file.listFiles();  
        for(int i=0;i<files.length;i++){  
            loadFileInfo(files[i]);  
        }  
    }  
    /** 
     * 检查文件更新状态 
     * @param file 
     */  
    private void checkFileUpdate(File file){  
        if(file.isFile()){  
            // 将当前文件加入到 newFiles 集合中  
            newFiles.add(file.getAbsolutePath());  
            //   
            Long lastModified = currentFiles.get(file.getAbsolutePath());  
            if(lastModified == null){  
                // 新加入文件  
                currentFiles.put(file.getAbsolutePath(), file.lastModified());  
                System.out.println("添加文件:" + file.getAbsolutePath());  
                return;  
            }  
            if(lastModified.doubleValue() != file.lastModified()){  
                // 更新文件  
                currentFiles.put(file.getAbsolutePath(), file.lastModified());  
                System.out.println("更新文件:" + file.getAbsolutePath());  
                return;  
            }  
            return;  
        } else if(file.isDirectory()){  
            File[] files = file.listFiles();  
            if(files == null || files.length == 0){  
                // 没有子文件或子目录时返回  
                return;  
            }  
            for(int i=0;i<files.length;i++){  
                checkFileUpdate(files[i]);  
            }  
        }  
    }  
    /** 
     * 检查被移除的文件 
     */  
    private void checkRemovedFiles(){  
        // 增加或更新时,newFiles.size() == currentFiles.size()  
        // 删除时,    newFiles.size()  < currentFiles.size()  
        // 不可能出现      newFiles.size()  > currentFiles.size()  
        if(newFiles.size() == currentFiles.size()){  
            // 增加或更新时没有被移除的文件,直接返回  
            return;  
        }  
        Iterator<String> it = currentFiles.keySet().iterator();  
        while(it.hasNext()){  
            String filename = it.next();  
            if(!newFiles.contains(filename)){  
                // 此处不能使用 currentFiles.remove(filename);从 map 中移除元素,  
                // 否则会引发同步问题.  
                // 正确的做法是使用 it.remove();来安全地移除元素.  
                it.remove();  
                System.out.println("删除文件:" + filename);  
            }  
        }  
    }  
    /** 
     * 起始目录 
     * @return 
     */  
    public String getDirectory() {  
        return directory;  
    }  
    public void setDirectory(String directory) {  
        this.directory = directory;  
    }  
}
