package com.summer.whm.ssh.command.sys;

import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class OSCommand extends SysCommand {

    private static final String NAME = "os";

    public OSCommand(CommandContext context) {
        super();
        this.context = context;
    }
    
    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String doTask(String str) {
        String os = FileUtils.getOSName();
        return this.format(os);
    }
}
