package com.nt.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Create by TaoTaoNing
 * 2019/3/27
 * Selector 详解：
 * 每处理一个selectKey 都要将其从set中删除
 **/
public class NioTest12 {
    public static void main(String[] args) throws Exception {
        int[] prots = new int[5];
        prots[0] = 5000;
        prots[1] = 5001;
        prots[2] = 5002;
        prots[3] = 5003;
        prots[4] = 5004;

        // 打开一个selector
        Selector selector = Selector.open();
        // 用selector 监听 五个端口
        for (int i = 0; i < prots.length; i++) {
            // 打开serversocketchannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 设置为非阻塞模式  必须要设置
            serverSocketChannel.configureBlocking(false);

            // 从serversocketchannel 中获得服务器端的serversocket
            ServerSocket socket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(prots[i]);

            // 绑定端口号
            socket.bind(address);
            // 将serversocketchannel注册到selector上
            SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("端口号绑定：" + prots[i] + ",socket " + socket);
        }

        while (true) {
            int number = selector.select();
            System.out.println("number = " + number);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    // 此处获得的serversocketchannel 对应上面的 将serversocketchannel 注册到selector
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                   // 服务器与客户端连接的socketchannel对象  因为上面的ServerSocketChannel 仅仅是建立连接
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    // 每次处理完一个selectkey 都要将它从set中移除
                    iterator.remove();
                    System.out.println("获得客户端连接：" + channel);
                } else if (next.isReadable()) {
                    // 对用 accept.register(selector, SelectionKey.OP_READ); read事件的注册
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    while (true) {
                        byteBuffer.clear();
                        int read = channel.read(byteBuffer);
                        byteBuffer.flip();
                        channel.write(byteBuffer);
                        byteBuffer.clear();
                        System.out.println("3333333读到 " + read + "个字节from " + channel);
                       if (read <= 0){
                           break;
                       }
                        System.out.println("读到 " + read + "个字节from " + channel);
                    }
                    iterator.remove();
                }
            }
        }

    }
}
