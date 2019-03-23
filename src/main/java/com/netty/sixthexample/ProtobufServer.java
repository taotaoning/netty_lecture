package com.netty.sixthexample;

import com.netty.secondexample.MyServerinitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.logging.Handler;

/**
 * Create by TaoTaoNing
 * 2019/3/23
 **/
public class ProtobufServer {
    private static int BOSSCOUNT = Runtime.getRuntime().availableProcessors()*2;
    private static int WORKERCOUNT = 8 ;

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(BOSSCOUNT);
        EventLoopGroup workerGroup = new NioEventLoopGroup(WORKERCOUNT);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new ProtobufInitializer());

            ChannelFuture future = serverBootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }
}
