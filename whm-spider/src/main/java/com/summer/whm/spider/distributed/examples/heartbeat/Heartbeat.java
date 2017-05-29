package com.summer.whm.spider.distributed.examples.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.spider.client.InterceptWebConnection;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class Heartbeat extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(InterceptWebConnection.class);

    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                channel.close(); // call back channelInactive(ChannelHandlerContext ctx)
                if (logger.isDebugEnabled()) {
                    logger.debug(channel.remoteAddress() + "---No data was received for a while ,read time out... ...");
                }
                // because we are attaching more importance to the read_idle time(timeout to rec)
            } else if (e.state() == IdleState.WRITER_IDLE) { // No data was sent for a while.
                channel.close();
                if (logger.isDebugEnabled()) {
                    logger.debug(channel.remoteAddress() + "---No data was sent for a while.write time out... ...");
                }
            }
        }
    }
}
