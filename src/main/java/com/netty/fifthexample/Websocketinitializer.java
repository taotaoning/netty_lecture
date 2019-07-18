package com.netty.fifthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Create by TaoTaoNing
 * 2019/3/21
 * 测试commit 撤回
>>>>>>> test_branch
 **/
public class Websocketinitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpCodec",new HttpServerCodec());
        //以块状写入
        pipeline.addLast("chunkeWrite",new ChunkedWriteHandler());
        //将httprequest和httpcontent 以长度聚合到fullhttprequest （fullhttpresponse 类似）
        pipeline.addLast(new HttpObjectAggregator(8192));

        //websocket handler  websocket 请求url ： ws：//ip:port/content_path   下面参数 /ws 值得是 content_path
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new TextWebsocketHandler());
    }
}
