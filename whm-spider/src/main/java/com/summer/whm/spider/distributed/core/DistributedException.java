package com.summer.whm.spider.distributed.core;

public class DistributedException extends RuntimeException {

    private static final long serialVersionUID = 2617523950309939510L;

    private boolean retryable = false;

    public DistributedException() {
        super();
        this.retryable = false;
    }

    public DistributedException(boolean retryable) {
        super();
        this.retryable = retryable;
    }

    public DistributedException(String message) {
        super(message);
        this.retryable = false;
    }

    public DistributedException(String message, boolean retryable) {
        super(message);
        this.retryable = retryable;
    }

    public DistributedException(String message, Throwable cause) {
        super(message, cause);
        this.retryable = false;
    }

    public DistributedException(String message, Throwable cause, boolean retryable) {
        super(message, cause);
        this.retryable = retryable;
    }

    public DistributedException(Throwable cause) {
        super(cause);
        this.retryable = false;
    }

    public DistributedException(Throwable cause, boolean retryable) {
        super(cause);
        this.retryable = retryable;
    }

    public boolean retryable() {
        return this.retryable;
    }
}
