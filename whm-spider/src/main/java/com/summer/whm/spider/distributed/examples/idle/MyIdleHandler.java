package com.summer.whm.spider.distributed.examples.idle;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyIdleHandler extends ChannelHandlerAdapter {
    
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
 
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println("--- Reader Idle ---");
 
                ctx.writeAndFlush("读取等待：客户端你在吗... ...\r\n");
 
                // ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                System.out.println("--- Write Idle ---");
 
                ctx.writeAndFlush("写入等待：客户端你在吗... ...\r\n");
                // ctx.close();
            } else if (e.state() == IdleState.ALL_IDLE) {
                System.out.println("--- All_IDLE ---");
 
                ctx.writeAndFlush("全部时间：客户端你在吗... ...\r\n");
 
            }
 
        }
    }
}