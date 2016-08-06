package com.summer.whm.ssh.command.core;

import java.io.File;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class CDCommand extends AbstractCommand {

    public CDCommand() {
        super();
    }

    public CDCommand(String string, CommandContext context) throws CommandException {
        super(string, context);
    }

    public String doTask(String str) {

        if (file == null) {
            if (!StringUtils.isBlank(str)) {
                if (str.startsWith("/")) {
                    this.file = new File(str);
                } else {
                    this.file = new File(FileUtils.getPath(this.context.getCurrentPath(), str));
                }
                if (!file.exists()) {
                    return str + "目录不存在。";
                } else if (!file.isDirectory()) {
                    return str + "不是目录。";
                }

                this.context.setCurrentPath(file.getPath());
            }
        }else{
            this.context.setCurrentPath(file.getPath());
        }
        
        return this.format(context.getCurrentPath());
    }

}
