package com.summer.whm.ssh.command.sys;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.utils.StringUtils;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class SysFactory {
    public static final Map<String, Class<? extends AbstractCommand>> map = new HashMap<String, Class<? extends AbstractCommand>>();

    static {
        map.put("@os", OSCommand.class);
        map.put("window", WindowCommand.class);
        map.put("shell", ShellCommand.class);
    }

    public static AbstractCommand build(String input, CommandContext context) throws NoSuchMethodException,
            SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if (!StringUtils.isBlank(input)) {
            AbstractCommand command = null;
            command = createCommand(input, context);
            return command;
        }

        return null;
    }

    public static AbstractCommand createCommand(String cmd, CommandContext context) throws NoSuchMethodException,
            SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        AbstractCommand command = null;
        Class<AbstractCommand> commandClass = (Class<AbstractCommand>) map.get(cmd);
        if (commandClass != null) {
            Constructor<AbstractCommand> constructor = commandClass.getDeclaredConstructor(CommandContext.class);
            command = constructor.newInstance(context);
        } else {
            
            if(FileUtils.isWindow()){
                commandClass = (Class<AbstractCommand>) map.get("window");
            }else{
                commandClass = (Class<AbstractCommand>) map.get("shell");
            }
            // shell命令
            Constructor<AbstractCommand> constructor = commandClass.getDeclaredConstructor(String.class,
                    CommandContext.class);
            
            command = constructor.newInstance(cmd.substring(1), context);
        }
        return command;
    }

    public static void main(String[] args) {
        String cmd = "@os";
        
        System.out.println(cmd.substring(1));
    }
}
