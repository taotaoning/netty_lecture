package com.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        System.out.println(msg.getClass());
        System.out.println(ctx.channel().remoteAddress());
            Thread.sleep(5000);


        if (msg instanceof HttpRequest) {

            HttpRequest httpRequest = (HttpRequest) msg;

            String methodName = httpRequest.method().name();

            String uri = httpRequest.uri();

            URI uri1 = new URI(uri);

            // google 浏览器会发送两次请求 该url 是请求 网站图标
            if ("/favicon.ico".equals(uri1.getPath())){
                System.out.println("methodName = " + methodName +" : " + "uri = " + uri1);
                return;
            }


            // 构造返回给客户端的信息
            ByteBuf context = Unpooled.copiedBuffer("hello client!", CharsetUtil.UTF_8);

            // 设置响应给客户端的信息，  http版本， 响应状态码， 响应的信息
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    context);
            // 设置 响应的头部信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,context.readableBytes());

            // 写回给客户端， 如果只调用write方法，则只是写在缓冲区，并未发送，需要调用flush 方法
            ctx.writeAndFlush(response);
            ctx.channel().close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.print("连接异常----");
    }
}
