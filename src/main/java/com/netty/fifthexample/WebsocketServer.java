package com.netty.fifthexample;

import com.netty.fourthexample.MyServerinitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Create by TaoTaoNing
 * 2019/3/21
 **/
public class WebsocketServer {

    private static final int BOSSCOUNT = Runtime.getRuntime().availableProcessors();
    private static final int WORKERCOUNT = 4;

    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(BOSSCOUNT);
        EventLoopGroup workerGroup = new NioEventLoopGroup(WORKERCOUNT);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new Websocketinitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
