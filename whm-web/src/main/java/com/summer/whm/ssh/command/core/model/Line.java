package com.summer.whm.ssh.command.core.model;

public class Line {
    private long line;

    private String str;

    public long getLine() {
        return line;
    }

    public void setLine(long line) {
        this.line = line;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Line(long line, String str) {
        super();
        this.line = line;
        this.str = str;
    }

    public Line() {
        super();
    }

    @Override
    public String toString() {
        return line + ", " + str;
    }
}
