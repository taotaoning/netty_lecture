package com.netty.firstexample;

import com.netty.firstexample.TestServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    private static int BOSS = Runtime.getRuntime().availableProcessors() * 2;

    private static int WORKER = 8 ;

    public static void main(String[] args){

//        创建 boss 和 worker 线程池（一般设置两个线程池）， boss中一个线程用于监听绑定的端口号，其他线程用于连接客户端，
//        worker 线程 用于处理boss转发的客户端连接的后续处理流程。
        EventLoopGroup boss = new NioEventLoopGroup(BOSS);
        EventLoopGroup worker = new NioEventLoopGroup(WORKER);

        try {
            //启动服务端  netty封装的 nio启动
            ServerBootstrap server = new ServerBootstrap();

            // 绑定线程池，
            server.group( boss,worker)
                    .channel(NioServerSocketChannel.class)// 指定连接方式为nio 如果为 阻塞式 ：OioServerSocketChannel
                    .childHandler(new TestServerInitializer());// 绑定 子处理器

            ChannelFuture f = server.bind(8899).sync();//监听端口  异步方式
            f.channel().closeFuture().sync(); // 关闭连接 异步方式
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }


    }


}
