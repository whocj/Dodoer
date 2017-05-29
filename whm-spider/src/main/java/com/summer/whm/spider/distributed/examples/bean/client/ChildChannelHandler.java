package com.summer.whm.spider.distributed.examples.bean.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel sc) throws Exception {

		System.out.println("报告");
		System.out.println("信息：客户端与服务端建立连接");
		System.out.println("报告完毕");
		
		//序列化、解码
		sc.pipeline().addLast(
				new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this
						.getClass().getClassLoader())));

		//序列化、编码
		sc.pipeline().addLast(new ObjectEncoder());

		//信息处理
		sc.pipeline().addLast(new SubReqClientHandler());

	}

}
