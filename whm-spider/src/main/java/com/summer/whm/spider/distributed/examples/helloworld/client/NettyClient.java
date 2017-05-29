package com.summer.whm.spider.distributed.examples.helloworld.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	public void connect(String inetHost, int inetPort) throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();

		try {

			Bootstrap b = new Bootstrap();
			b.group(group); // group 组
			b.channel(NioSocketChannel.class); // channel 通道
			b.option(ChannelOption.TCP_NODELAY, true); // option 选项
			b.handler(new ChildChannelHandler()); // handler 处理

			// 发起异步链接
			ChannelFuture f = b.connect(inetHost, inetPort);

			// 等待客户端链路关闭
			f.channel().closeFuture().sync();

		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception{
	    NettyClient nettyClient = new NettyClient();
	    nettyClient.connect("127.0.0.1", 53606);
	}
}
