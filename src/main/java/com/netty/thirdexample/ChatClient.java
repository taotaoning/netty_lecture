package com.netty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ChatClient {

    private static final int BOSSCOUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        EventLoopGroup loopGroup = new NioEventLoopGroup(BOSSCOUNT);

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new ChatClientinitializer());
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true){
                if ("886".equals(br.readLine())){
                    break;
                }
                channel.writeAndFlush(br.readLine() + "\r\n");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }


    }

}
