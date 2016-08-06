package com.summer.whm.ssh.command;

import com.summer.whm.ssh.command.format.Format;
import com.summer.whm.ssh.command.format.HtmlFormat;
import com.summer.whm.ssh.command.format.JavaFormat;

public class CommandContext {

    // 当前目录
    private String currentPath = "/";

    private Format format = new JavaFormat();
    
    public static CommandContext getDefault() {
        CommandContext context = new CommandContext();
        context.currentPath = "/";
        return context;
    }

    public static CommandContext getDefaultHtmlContext() {
        CommandContext context = new CommandContext();
        context.currentPath = "/";
        context.setFormat(new HtmlFormat());
        return context;
    }
    
    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }
    
}
