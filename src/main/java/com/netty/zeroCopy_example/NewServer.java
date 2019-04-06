package com.netty.zeroCopy_example;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Create by TaoTaoNing
 * 2019/4/6
 * 零拷贝传输文件
 **/
public class NewServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket socket = serverSocketChannel.socket();
        socket.setReuseAddress(true);
        socket.bind(new InetSocketAddress(8899));

        ByteBuffer byteBuffer = ByteBuffer.allocate(6144);

        while (true) {

            SocketChannel accept = serverSocketChannel.accept();
            accept.configureBlocking(true);

            int count = 0;
            int total = 0;
            while (-1 != count) {
                count = accept.read(byteBuffer);

                total += count;
                byteBuffer.clear();
            }
            System.out.println(count + "------- total = " + total);

        }
    }
}
