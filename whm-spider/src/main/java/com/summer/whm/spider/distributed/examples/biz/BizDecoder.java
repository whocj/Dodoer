package com.summer.whm.spider.distributed.examples.biz;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class BizDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final byte[] JSON_PROTOCOL = { 01, 00, 00, 00, 00 };
    private final byte[] STRING_PROTOCOL = { 00, 01, 00, 00, 00 };
    private final byte[] SERIALIZABLE_PROTOCOL = { 00, 00, 01, 00, 00 };
    private final byte[] FILE_PROTOCOL = { 00, 00, 00, 01, 00 };
    private final byte[] PING_PROTOCOL = { 00, 00, 00, 00, 01 };

    @Override
    protected void decode(ChannelHandlerContext cxt, ByteBuf msg, List<Object> out) throws Exception {
        // 取出自定义协议的header部分
        ByteBuf msg_header = msg.copy(0, 5);
        msg = msg.skipBytes(4);
        // 剔除最后一个字符串 03
        msg = msg.copy(0, msg.readableBytes() - 1);
        if (msg_header.array() == JSON_PROTOCOL) {
            // JsonObject jsonObj = Json.parse(msg);
            // out.add(jsonObj);
        } else if (msg_header.array() == STRING_PROTOCOL) {
            // ...
        } else if (msg_header.array() == SERIALIZABLE_PROTOCOL) {
            // ...
        } else if (msg_header.array() == FILE_PROTOCOL) {
            // ...
        } else if (msg_header.array() == PING_PROTOCOL) {

        }
    }

}