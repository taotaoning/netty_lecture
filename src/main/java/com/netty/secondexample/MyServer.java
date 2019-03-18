package com.netty.secondexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyServer {

    private static int BOSSCOUNT = Runtime.getRuntime().availableProcessors()*2;
    private static int WORKERCOUNT = 8 ;

    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(BOSSCOUNT);
        EventLoopGroup workerGroup = new NioEventLoopGroup(WORKERCOUNT);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new MyServerinitializer());

            ChannelFuture future = serverBootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();
        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }

}
