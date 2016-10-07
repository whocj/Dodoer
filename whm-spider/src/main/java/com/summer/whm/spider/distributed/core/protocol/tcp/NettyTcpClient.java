package com.summer.whm.spider.distributed.core.protocol.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.summer.whm.spider.distributed.DistributedConfigs;
import com.summer.whm.spider.distributed.core.DistributedException;
import com.summer.whm.spider.distributed.utils.NamedThreadFactory;

public class NettyTcpClient {

    private static Logger logger = LoggerFactory.getLogger(NettyTcpClient.class);

    private final static int IDLE_TIMEOUT = 20;

    private static final int CHANNEL_SIZE = 1;

    private static EventLoopGroup eventLoopGroup = new NioEventLoopGroup(0, new NamedThreadFactory("client.worker",
            true));

    private NettyChannel[] channels = new NettyChannel[CHANNEL_SIZE];

    public NettyTcpClient() {
        for (int i = 0; i < CHANNEL_SIZE; i++) {
            NettyChannel channel = new NettyChannel();
            channels[i] = channel;
        }
        for (NettyChannel channel : channels) {
            try {
                channel.checkChannel();
            } catch (Throwable ex) {
                logger.warn("Exception:", ex);
            }
        }
        logger.info(this.toString() + " has been created.");
    }

    public void dumpStatus() {
        // not support yet.
    }

    public static void shutdown() {
        eventLoopGroup.shutdownGracefully();
    }

    public void destroy() {
        for (NettyChannel channel : channels) {
            try {
                channel.destroy();
            } catch (Throwable ex) {
                logger.error("Destroy channel fail.", ex);
            }
        }
        logger.info("NettyTcpClient " + this + " has been destroyed.");
    }

    private class NettyChannel {

        private volatile Channel channel;

        private Lock lock = new ReentrantLock();

        private Condition connectedCondition = lock.newCondition();

        private ScheduledFuture<?> heartbeatScheduledFuture;

        private transient ChannelInitializer<SocketChannel> channelInitializer;

        private volatile boolean destroyed = false;

        private NettyChannel() {
            channelInitializer = new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    MessageHandler messageHandler = new MessageHandler();
                    ch.pipeline().addLast(new MessageEncoder(), new MessageDecoder(),
                    // if not receive heartbeat response in 20s,close channel.
                            new IdleStateHandler(IDLE_TIMEOUT, 0, 0), messageHandler);
                }
            };
            try {
                connect();
                heartbeatScheduledFuture = eventLoopGroup.scheduleWithFixedDelay(new Runnable() {
                    @Override
                    public void run() {
                        sendHeartbeat();
                    }
                }, 5, 2, TimeUnit.SECONDS);
            } catch (Exception ex) {
                logger.error(NettyChannel.this.toString() + " init channel occur exception.", ex);
            }
        }

        private void sendHeartbeat() {
            try {
                if (channel != null) {
                    channel.writeAndFlush(Heartbeat.REQUEST).addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture channelFuture) throws Exception {
                            if (channelFuture.isSuccess()) {
                                logger.debug(NettyChannel.this.toString() + ":heartbeat send successfully.");
                            } else {
                                logger.debug(NettyChannel.this.toString() + ":heartbeat send failure.",
                                        channelFuture.cause());
                            }
                        }
                    });
                }
            } catch (Throwable ex) {
                logger.debug("Exception:", ex);
            }
        }

        protected void destroy() {
            destroyed = true;
            if (heartbeatScheduledFuture != null) {
                heartbeatScheduledFuture.cancel(true);
            }
            if (channel != null) {
                channel.close();
                channel = null;
            }
        }

        @Override
        public String toString() {
            return hashCode() + "@" + NettyTcpClient.this.toString();
        }

        private void connect() {
            if (destroyed) {
                return;
            }
            logger.debug(this.toString() + " start connect.");
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.remoteAddress(DistributedConfigs.SERVER_IP, DistributedConfigs.SERVER_PORT);
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, false);
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, DistributedConfigs.CONNECT_TIMEOUT);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
            bootstrap.handler(channelInitializer);
            bootstrap.connect();
            logger.debug(this.toString() + " end connect.");
        }

        public void checkChannel() throws DistributedException {
            if (channel == null) {
                logger.warn("Current channel is null,will wait " + DistributedConfigs.CONNECT_TIMEOUT + "ms for ready.");
                lock.lock();
                try {
                    connectedCondition.await(DistributedConfigs.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    lock.unlock();
                }
            }
            if (channel == null) {
                throw new DistributedException(this.toString() + " connection is not ready", true);
            }
        }

        public class MessageHandler extends SimpleChannelInboundHandler<Object> {

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                channel = ctx.channel();
                logger.info(NettyChannel.this.toString() + " channel " + channel + " has connected.");
                lock.lock();
                try {
                    connectedCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }

            @Override
            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                channel = null;
                logger.debug(NettyChannel.this.toString() + " disconnected... will try to reconnect in 5 sec...");
                final EventLoop loop = ctx.channel().eventLoop();
                loop.schedule(new Runnable() {
                    public void run() {
                        connect();
                    }
                }, 5, TimeUnit.SECONDS);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                logger.error(NettyChannel.this.toString() + " unexpected exception from downstream.", cause);
                ctx.close();
            }

            @Override
            protected void channelRead0(ChannelHandlerContext ctx, Object message) throws Exception {
                if (logger.isDebugEnabled()) {
                    logger.debug(NettyChannel.this.toString() + " channel " + ctx.channel() + " receive:" + message);
                }
                if (message == null) {
                    // maybe has handled in decode phase.
                    return;
                }
                if (message instanceof Heartbeat) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(NettyChannel.this.toString() + " heartbeat response Received.");
                    }
                } else {
                    logger.warn(NettyChannel.this.toString() + " invalid message,will be ignore:" + message);
                }
            }

            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                // 监听idle事件
                if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
                    IdleStateEvent event = (IdleStateEvent) evt;
                    // 根据read idle判断channel是否失效
                    if (event.state() == IdleState.READER_IDLE) {
                        logger.info("Channel " + ctx.channel() + " has timeout because idle,will be closed.");
                        // 如果检测到闲，则关闭channel
                        ctx.close();
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException{
        new NettyTcpClient();
        Thread.sleep(1000000);
    }
}