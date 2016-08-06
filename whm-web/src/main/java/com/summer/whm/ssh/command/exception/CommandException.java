package com.summer.whm.ssh.command.exception;

public class CommandException extends Exception {

    private static final long serialVersionUID = 1L;

    public CommandException() {
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
