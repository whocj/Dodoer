package com.summer.whm.ssh.command.sys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;

public class WindowCommand extends SysCommand {

    private static final String NAME = "shell";

    private String shell;

    public WindowCommand() {
        super();
    }

    public WindowCommand(String string, CommandContext context) throws CommandException {
        super();
        this.context = context;
        this.shell = string;
    }

    public List<String> exec(String cmd) {
        List<String> list = new ArrayList<String>();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("cmd /c "+ cmd);
            
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = "";
            while ((line = input.readLine()) != null) {
                list.add(line);
            }
            input.close();
            input = null;
            process.destroy();
            process = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String doTask(String str) {
        List<String> list = null;
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(shell)) {
            list = exec(shell);
            if (list != null && list.size() > 0) {
                for (String line : list) {
                    sb.append(this.format(line));
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ShellCommand command = new ShellCommand();
        List<String> list = command.exec("netstat -ano");
        System.out.println(list);
    }
}
