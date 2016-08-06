package com.summer.whm.ssh.command.examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.summer.whm.ssh.command.core.GrepCommand;
import com.summer.whm.ssh.command.exception.CommandException;

public class TestGrep {

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

        Pattern pattern = Pattern.compile("\\S+[work]+\\S+");
        Matcher matcher = pattern.matcher("workspace12");
        System.out.println(matcher.matches());
        // Pattern p = Pattern.compile("a*b");
        // Matcher m = p.matcher("aaaaab");
        // boolean b = m.matches();
        // System.out.println(b);
        System.out.println("work*1".indexOf("*"));
        GrepCommand grep = new GrepCommand("work");
        
        System.out.println(grep.doTask("workspace12"));
        
    }

}
