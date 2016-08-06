package com.summer.whm.ssh.command.core;

import java.io.File;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class DelCommand extends AbstractCommand {

    public DelCommand(){
        super();
    }
    
    public DelCommand(String string, CommandContext context) throws CommandException{
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
                return str + "文件不存在。";
            } else if (!file.canWrite()) {
                return str + "文件不能删除。";
            }
        }

        file.deleteOnExit();
        return file.getAbsolutePath() + " 删除成功。";
    }

}
