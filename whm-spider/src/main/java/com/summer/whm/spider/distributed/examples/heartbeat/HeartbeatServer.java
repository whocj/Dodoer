package com.summer.whm.spider.distributed.examples.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartbeatServer {

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param args
     * @throws InterruptedException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        private static final int IDEL_TIME_OUT = 10;
                        private static final int READ_IDEL_TIME_OUT = 25;
                        private static final int WRITE_IDEL_TIME_OUT = 15;

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("idleStateHandler",
                                    new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, IDEL_TIME_OUT));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
                                        IdleStateEvent event = (IdleStateEvent) evt;
                                        if (event.state() == IdleState.READER_IDLE)
                                            System.out.println("read idle");
                                        else if (event.state() == IdleState.WRITER_IDLE)
                                            System.out.println("write idle");
                                        else if (event.state() == IdleState.ALL_IDLE)
                                            System.out.println("all idle");
                                            ctx.channel().write("@Ping");
                                    }
                                }
                            });
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(8088).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
