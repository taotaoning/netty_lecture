package com.nt.nio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by TaoTaoNing
 * 2019/4/3
 **/
public class NioClient {

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        while (true) {

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {

                SelectionKey next = iterator.next();

                if (next.isConnectable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    if (channel.isConnectionPending()) {
                        // 不加 这一行代码，会报连接未建立异常
                        channel.finishConnect();
                        ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                        writeBuffer.put("客户端已连接--".getBytes());
                        writeBuffer.flip();
                        channel.write(writeBuffer);
                        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                                10, 20, 10000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
                        executor.submit(new Runnable() {
                            @Override
                            public void run() {
                                writeBuffer.clear();
                                InputStreamReader inputStream = new InputStreamReader(System.in);
                                BufferedReader bufferedReader = new BufferedReader(inputStream);
                                try {
                                    String s = bufferedReader.readLine();
                                    writeBuffer.put(s.getBytes());
                                    writeBuffer.flip();
                                    channel.write(writeBuffer);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        channel.register(selector, SelectionKey.OP_READ);
                    }
                } else if (next.isReadable()) {

                    SocketChannel socketChannel1 = (SocketChannel) next.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(512);

                    int read = socketChannel1.read(readBuffer);
                    if (0 <= read) {
                        String string = new String(readBuffer.array(), 0, read);
                        System.out.println(string);
                    }
                }

                iterator.remove();
            }

        }


    }
}
