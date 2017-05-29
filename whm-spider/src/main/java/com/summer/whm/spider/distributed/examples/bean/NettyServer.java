package com.summer.whm.spider.distributed.examples.bean;

import com.summer.whm.spider.distributed.examples.bean.server.ChildChannelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	
	public void bing(int port) throws Exception{
		
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			
			ServerBootstrap b = new ServerBootstrap();
			
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.SO_BACKLOG, 1024);
			b.childHandler(new ChildChannelHandler());
			
			//绑定端口
			ChannelFuture f = b.bind(port).sync();
			
			System.out.println("Netty Server："+ port+" 启动完毕，等待连接... ...");
			
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
			
		} finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
}
