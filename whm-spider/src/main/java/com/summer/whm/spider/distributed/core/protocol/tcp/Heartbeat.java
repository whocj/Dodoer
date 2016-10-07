package com.summer.whm.spider.distributed.core.protocol.tcp;

public class Heartbeat {
    public static final int TYPE_REQUEST = 1;

    public static final int TYPE_RESPONSE = 2;

    public static Heartbeat REQUEST = new Heartbeat(TYPE_REQUEST);

    public static Heartbeat RESPONSE = new Heartbeat(TYPE_RESPONSE);

    public Heartbeat(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

}
