package com.summer.whm.ssh.command.core;

import java.io.File;
import java.io.IOException;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class MkdirCommand extends AbstractCommand {
    
    public MkdirCommand(){
        super();
    }
    
    public MkdirCommand(String string, CommandContext context) throws CommandException{
        super(string, context);
    }
    
    public String doTask(String str) {

        if (file == null && !StringUtils.isBlank(str)) {
            if (str.startsWith("/")) {
                this.file = new File(str);
            } else {
                this.file = new File(FileUtils.getPath(this.context.getCurrentPath(), str));
            }
            if (file.exists()) {
                return str + "文件己存在。";
            }
        }
        
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return file.getAbsolutePath();
    }
}
