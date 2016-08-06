package com.summer.whm.ssh.command.core;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.summer.whm.ssh.command.AbstractCommand;
import com.summer.whm.ssh.command.CommandContext;
import com.summer.whm.ssh.command.exception.CommandException;
import com.summer.whm.ssh.command.utils.StringUtils;
import com.summer.whm.ssh.command.utils.file.FileUtils;

public class LSCommand extends AbstractCommand {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final  DecimalFormat df = new DecimalFormat(".###");

    public LSCommand() {
        super();
    }

    public LSCommand(String string, CommandContext context) throws CommandException {
        super(string, context);
    }

    public String doTask(String str) {
        if (file == null && !StringUtils.isBlank(str)) {
            if (str.startsWith("/")) {
                this.file = new File(str);
            } else {
                this.file = new File(FileUtils.getPath(this.context.getCurrentPath(), str));
            }
            if (!file.exists()) {
                return str + "目录不存在。";
            } else if (!file.isDirectory()) {
                return str + "不是目录。";
            }
        }
        File[] files = file.listFiles();
        StringBuffer sb = new StringBuffer();

        sb.append("<table style=\"border:0;width:100%\">");
        String temp = null;
        for (File f : files) {
            temp = next(f.getName());
            if (temp != null) {
                sb.append(this.format(f));
            }
        }
        sb.append("</table>");

        return sb.toString();
    }

    public String format(File f) {
        StringBuffer sb = new StringBuffer();

        sb.append("<tr>");
        sb.append("<td width=\"60%\">").append(f.getName()).append("</td>");
        if (f.isDirectory()) {
            sb.append("<td width=\"5%\">Dir</td>");
        } else {
            sb.append("<td width=\"5%\">File</td>");
        }
        long len = f.length();
        if(len > 0l){
            sb.append("<td width=\"10%\">").append(df.format(f.length() / 1024d / 1024d)).append("MB</td>");
        }else{
            sb.append("<td width=\"10%\">&nbsp;</td>");
        }
        
        sb.append("<td width=\"25%\">").append(sdf.format(new Date(f.lastModified()))).append("</td>");
        sb.append("</tr>");
        return sb.toString();
    }
}
