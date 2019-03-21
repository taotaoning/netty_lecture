package com.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestHttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
       // 自带的处理HTTP 请求 编解码 处理器  相当于 HttpDecoder和 HttpEncoder 两个结合
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
       // 自定义处理器
        pipeline.addLast("testServerHandler",new TestHttpServerHandler());
    }
}
