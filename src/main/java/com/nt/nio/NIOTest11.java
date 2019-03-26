package com.nt.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Create by TaoTaoNing
 * 2019/3/26
 * 关于buffer的Scattering （分散 成多个）与Gathering （收集 聚合 把多个聚合成一个）
 **/
public class NIOTest11 {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(inetSocketAddress);
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        while (true){

            int byteRead = 0;

            while (byteRead < messageLength){
                long read = socketChannel.read(byteBuffers);
                byteRead += read;

                System.out.println("byteRead = " + byteRead);
                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "position = " + buffer.position() + ", limit = " + buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buffer ->{
                buffer.flip();
            });

            long byteWrite = 0;

            while (byteWrite < messageLength){
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
            Arrays.asList(byteBuffers).forEach(buffer ->{
                buffer.clear();
            });

            System.out.println("byteRead = " + byteRead +",byteWrite = " + byteWrite + ",messageLength = " + messageLength);
        }
    }
}
