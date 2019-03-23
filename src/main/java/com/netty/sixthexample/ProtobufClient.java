package com.netty.sixthexample;

import com.netty.secondexample.MyClinetinitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Create by TaoTaoNing
 * 2019/3/23
 **/
public class ProtobufClient {
    private static int GROUPCOUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(GROUPCOUNT);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new ProtobufClientinitializer());

            ChannelFuture f = bootstrap.connect("localhost", 8899).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
