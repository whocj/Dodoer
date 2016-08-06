package com.summer.whm.ssh.command.core;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;

public class CPCommand extends AbstractCommand{
    public CPCommand(){
        super();
    }
    
    public CPCommand(String string, CommandContext context) throws CommandException{
        super(string, context);
    }
}
