package com.summer.whm.spider.distributed.core.protocol.tcp;

import io.netty.util.ResourceLeakDetector;

public class TcpConstants {
    protected static final int MSG_LENGTH_SIZEINBYTE = 4;

    protected static final byte MSG_CODE_INVOCATION_HESSIAN = 1;

    protected static final byte MSG_CODE_RESULT_HESSIAN = 2;

    protected static final byte MSG_CODE_HEARTBEAT = 3;

    protected static final byte MSG_CODE_INVOCATION_KRYO = 4;

    protected static final byte MSG_CODE_RESULT_KRYO = 5;

    protected static final byte MSG_CODE_INVOCATION_JSON = 6;

    protected static final byte MSG_CODE_RESULT_JSON = 7;

    protected static final String SERIALIZATION = "RSF_SERIALIZATION";

    public static final int MAX_MSG_SIZE = Integer.getInteger("rsf.tcp.maxMsgSize", 10 * 1024 * 1024);

    static {
        ResourceLeakDetector.setEnabled(Boolean.getBoolean("io.netty.resourceLeakDetection"));
    }
}
