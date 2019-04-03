package com.nt.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Create by TaoTaoNing
 * 2019/4/3
 **/
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {

                SelectionKey selectionKey = selectionKeyIterator.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel accept = serverSocketChannel1.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    clientMap.put(UUID.randomUUID().toString(), accept);
                } else if (selectionKey.isReadable()) {

                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                    int read = channel.read(byteBuffer);
                    if (0 <= read) {
                        byteBuffer.flip();
                        Set<Map.Entry<String, SocketChannel>> entries = clientMap.entrySet();
                        String message = String.valueOf(Charset.forName("utf-8").decode(byteBuffer).array());
                        String sendKey = null;
                        for (Map.Entry<String, SocketChannel> entry : entries) {
                            if (channel == entry.getValue()) {
                                sendKey = entry.getKey();
                                break;
                            }
                            byteBuffer.clear();
                        }
                        for (Map.Entry<String, SocketChannel> entry : entries) {
                            SocketChannel value = entry.getValue();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                            writeBuffer.put((sendKey + ":" + message).getBytes());
                            writeBuffer.flip();
                            System.out.println(sendKey + ":" + message);

                                value.write(writeBuffer);

                        }
                    }
                }
                selectionKeyIterator.remove();

            }


        }
    }
}
