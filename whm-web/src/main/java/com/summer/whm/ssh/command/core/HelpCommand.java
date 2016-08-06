package com.summer.whm.ssh.command.core;

import java.util.HashMap;
import java.util.Map;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;

public class HelpCommand extends AbstractCommand {

    public static final Map<String, String> map = new HashMap<String, String>();

    static {
        map.put("cd", "进入目录命令,,Examples:cd /opt.");
        map.put("cp", "复制命令,Examples:cp src.txt desc.txt.");
        map.put("del", "删除命令,Examples:del xxx.txt.");
        map.put("grep", "查找命令，可于more,tail联合使用,Examples:tail xxx.txt|grep java.");
        map.put("ls", "显示当前目录文件列表.");
        map.put("ll", "显示当前目录文件列表.");
        map.put("mkdir", "创建文件夹,Examples:mkdir xxx.");
        map.put("more", "查看文件,,Examples:more xxx.txt.");
        map.put("tail", "监听文件,Examples:tail xxx.txt.");
        map.put("pwd", "显示当前目录.");
        map.put("help", "帮助命令.");
        map.put("?", "帮助命令.");
        map.put("@", "以@开头的输入参数，非命令接口.");
    }

    public HelpCommand() {
        super();
    }

    public HelpCommand(String string, CommandContext context) throws CommandException {
        super(string, context);
    }

    public String doTask(String str) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(this.format(entry.getKey() + "#" + entry.getValue()));
        }

        return sb.toString();
    }
}
