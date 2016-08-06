package com.summer.whm.ssh.command.examples;

import com.summer.whm.ssh.command.core.GrepCommand;
import com.summer.whm.ssh.command.core.LSCommand;
import com.summer.whm.ssh.command.exception.CommandException;

public class TestLS {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws CommandException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws CommandException {
        LSCommand ls = new LSCommand();
        GrepCommand grep = new GrepCommand("w");
        ls.setNextCommand(grep);
        System.out.println(ls.doTask("D:/"));
    }

}
