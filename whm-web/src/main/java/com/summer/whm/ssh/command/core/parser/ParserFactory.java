package com.summer.whm.ssh.command.core.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.core.CDCommand;
import com.summer.whm.ssh.command.core.CPCommand;
import com.summer.whm.ssh.command.core.DelCommand;
import com.summer.whm.ssh.command.core.GrepCommand;
import com.summer.whm.ssh.command.core.HelpCommand;
import com.summer.whm.ssh.command.core.LSCommand;
import com.summer.whm.ssh.command.core.MkdirCommand;
import com.summer.whm.ssh.command.core.MoreCommand;
import com.summer.whm.ssh.command.core.PWDCommand;
import com.summer.whm.ssh.command.core.TailCommand;
import com.summer.whm.ssh.command.utils.StringUtils;

public class ParserFactory {

    public static final Map<String, Class<? extends AbstractCommand>> map = new HashMap<String, Class<? extends AbstractCommand>>();

    static {
        map.put("cd", CDCommand.class);
        map.put("cp", CPCommand.class);
        map.put("del", DelCommand.class);
        map.put("grep", GrepCommand.class);
        map.put("ls", LSCommand.class);
        map.put("ll", LSCommand.class);
        map.put("mkdir", MkdirCommand.class);
        map.put("more", MoreCommand.class);
        map.put("tail", TailCommand.class);
        map.put("pwd", PWDCommand.class);
        map.put("help", HelpCommand.class);
        map.put("?", HelpCommand.class);
    }

    public static AbstractCommand build(String input, CommandContext context) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            if (!StringUtils.isBlank(input)) {
                AbstractCommand command = null;
                AbstractCommand parentCommand = null;
                String[] strs = input.split("\\|");
                boolean isFrist = true;
                for (String str : strs) {
                    if (isFrist) {
                        isFrist = false;
                        command = createCommand(str, context);
                        parentCommand = command;
                    } else {
                        AbstractCommand nextCommand = null;
                        nextCommand = createCommand(str, context);
                        parentCommand.setNextCommand(nextCommand);
                        parentCommand = nextCommand;
                    }
                }
                return command;
            }

        return null;
    }

    public static AbstractCommand createCommand(String str, CommandContext context) throws NoSuchMethodException,
            SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        if (str != null) {
            AbstractCommand command = null;

            String[] cms = str.split(" ");
            if (cms.length > 0) {
                Class<AbstractCommand> commandClass = (Class<AbstractCommand>) map.get(cms[0].trim());
                if (commandClass != null) {
                    Constructor<AbstractCommand> constructor = commandClass.getDeclaredConstructor(String.class,
                            CommandContext.class);
                    if (cms.length == 2) {
                        command = constructor.newInstance(cms[1].trim(), context);
                    } else {
                        command = constructor.newInstance("", context);
                    }
                }
                return command;
            }
        }
        return null;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//        ParserFactory parserFactory = new ParserFactory();
//        System.out.println(ParserFactory.build("ls D:/paoding_test_index", CommandContext.getDefault()).doTask(null));
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        System.out.println(os);
    }

}
