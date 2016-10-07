package com.summer.whm.spider.distributed.core.model;

/**
 * @author <a href="mailto:ljw79618@gmail.com">L.J.W</a>
 */
public class ServiceNotFoundException extends Exception {

    private static final long serialVersionUID = -1774859972574000489L;

    public ServiceNotFoundException() {
        super();
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceNotFoundException(Throwable cause) {
        super(cause);
    }
}
