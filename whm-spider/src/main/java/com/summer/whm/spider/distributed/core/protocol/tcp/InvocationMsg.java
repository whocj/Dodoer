package com.summer.whm.spider.distributed.core.protocol.tcp;

import java.io.Serializable;

import com.summer.whm.spider.distributed.core.Invocation;


public class InvocationMsg implements Serializable {

    private static final long serialVersionUID = -7159738294720431545L;

    public Invocation invocation;

    public int index;

    public InvocationMsg(Invocation invocation, int index) {
        this.invocation = invocation;
        this.index = index;
    }
}
