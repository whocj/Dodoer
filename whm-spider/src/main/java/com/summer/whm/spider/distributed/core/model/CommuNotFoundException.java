package com.summer.whm.spider.distributed.core.model;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */

public class CommuNotFoundException extends Exception {

    private static final long serialVersionUID = 5069740033501600556L;

    public CommuNotFoundException() {
        super();
    }

    public CommuNotFoundException(String message) {
        super(message);
    }

    public CommuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommuNotFoundException(Throwable cause) {
        super(cause);
    }
}
