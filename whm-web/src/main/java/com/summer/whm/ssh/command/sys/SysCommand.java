package com.summer.whm.ssh.command.sys;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;

public abstract class SysCommand extends AbstractCommand {

    public SysCommand() {
        super();
    }

    public SysCommand(CommandContext context) {
        super();
        this.context = context;
    }

    public abstract String name();
}
