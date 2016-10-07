package com.summer.whm.spider.distributed.core.protocol.tcp;

import com.summer.whm.common.utils.DateUtils;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartbeatHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                // logger.info("[发送心跳探测消息READER_IDLE][" + message.toString().trim() + "]");
                ctx.writeAndFlush(DateUtils.getNowTimeForYYYYMMDDHHMMSS_SSS());
            } else if (e.state() == IdleState.WRITER_IDLE) {
                // logger.info("[发送心跳探测消息WRITER_IDLE][" + message.toString().trim() + "]");
                ctx.writeAndFlush(DateUtils.getNowTimeForYYYYMMDDHHMMSS_SSS());
            }
        }
    }
}
