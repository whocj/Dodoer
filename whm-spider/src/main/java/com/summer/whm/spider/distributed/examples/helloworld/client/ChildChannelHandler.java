package com.summer.whm.spider.distributed.examples.helloworld.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
 
        System.out.println("报告");
        System.out.println("信息：连接服务器成功");
        System.out.println("报告完毕");
 
        // 半包处理【基于换行符】
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 字符串编码
        ch.pipeline().addLast(new StringDecoder());
        // 字符串解码
        ch.pipeline().addLast(new StringEncoder());
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new EchoClientHandler());
 
    }
 
}