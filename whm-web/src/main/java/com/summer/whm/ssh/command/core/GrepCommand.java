package com.summer.whm.ssh.command.core;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;

public class GrepCommand extends AbstractCommand {

    public GrepCommand(){
        super();
    }
    
    public GrepCommand(String string, CommandContext context) throws CommandException{
        this(string);
    }
    
    private String pattern = null;

    public GrepCommand(String str) throws CommandException {
        super();
//        String temp = str;
//        if (!StringUtils.isBlank(str)) {
//            temp = "[\\W|\\w]*?[" + temp + "]\\S*?";
//            System.out.println(temp);
//            this.pattern = Pattern.compile(temp);
//        } else {
//            throw new CommandException("Pattern is Null。");
//        }
        if(!StringUtils.isBlank(str)){
            this.pattern = str.trim().toLowerCase();
        }
    }

    public String doTask(String str) {
        if (!StringUtils.isBlank(str)) {
//            try{
//                Matcher matcher = pattern.matcher(str);
//                if (matcher.matches()) {
//                    str = this.next(str);
//                    return str;
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//                return null;
//            }
            if(str.indexOf(pattern) != -1){
                str = str.replaceAll(pattern, "<b>" + pattern + "</b>");
                return str;
            }
        }
        return null;
    }
}
