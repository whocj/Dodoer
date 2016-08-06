package com.summer.whm.ssh.command.format;

import com.summer.whm.ssh.command.utils.StringUtils;

public class JavaFormat implements Format {

    private final static String ENTER = "\n";

    @Override
    public String format(String str) {
        if (StringUtils.isBlank(str)) {
            return ENTER;
        }
        return str + ENTER;
    }
}
