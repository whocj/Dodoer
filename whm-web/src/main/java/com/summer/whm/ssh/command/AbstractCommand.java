package com.summer.whm.ssh.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public abstract class AbstractCommand implements Command {
    protected Command nextCommand = null;

    protected CommandContext context;

    protected File file;

    public AbstractCommand() {
        context = new CommandContext();
    }

    public AbstractCommand(CommandContext context) {
        super();
        this.context = context;
    }

    public AbstractCommand(String str, CommandContext context) throws CommandException {
        this.context = context;

        String path = "";

        if (this.context != null) {
            path = this.context.getCurrentPath();
        }

        this.file = new File(FileUtils.getPath(path, str));
        if (file == null || !this.file.exists()) {
            throw new CommandException("文件不存在。");
        }
    }

    public String next(String str) {
        if (nextCommand != null) {
            return this.nextCommand.doTask(str);
        }

        return str;
    }

    public String format(Object obj) {
        if (obj != null) {
            if (context != null && context.getFormat() != null) {
                return context.getFormat().format(obj.toString());
            }
            return obj.toString();
        } else {
            return null;
        }
    }

    public String format(String str) {
        if (context != null && context.getFormat() != null) {
            return context.getFormat().format(str);
        }

        return str;
    }

    protected int getTotalLines() {
        FileReader in = null;
        int totalLines = 0;
        try {
            in = new FileReader(this.file);
            LineNumberReader reader = new LineNumberReader(in);
            String strLine = reader.readLine();
            
            while (strLine != null) {
                totalLines++;
                strLine = reader.readLine();
            }
            reader.close();
            in.close();
            System.out.println(this.file.getName() + "共" + totalLines + "行数据。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalLines;
    }
    
    public Command getNextCommand() {
        return nextCommand;
    }

    public void setNextCommand(Command nextCommand) {
        this.nextCommand = nextCommand;
    }

    public String doTask(String str) {
        return null;
    }
}
