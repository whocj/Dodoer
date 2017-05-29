package com.summer.whm.spider.distributed.examples.heartbeat;

import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class StateCheckChannelHandler extends ChannelDuplexHandler {
    private static Logger log = LoggerFactory.getLogger(StateCheckChannelHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if ("ping".equals(msg)) {
            return;
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object _evt) throws Exception {
        if (_evt instanceof IdleStateEvent) {
            IdleStateEvent evt = (IdleStateEvent) _evt;
            if (evt.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush("ping");
            } else if (evt.state() == IdleState.READER_IDLE) {
                log.error("channel:{} is time out.", ctx.channel());
                ctx.fireExceptionCaught(new SocketTimeoutException("force to close channel("
                        + ctx.channel().remoteAddress() + "), reason: time out."));
                ctx.channel().close();
            }
        }
        super.userEventTriggered(ctx, _evt);
    }

}
