package com.netty.zeroCopy_example;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Create by TaoTaoNing
 * 2019/4/6
 **/
public class NewClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);

        String filePath = "G:\\exe安裝文件夾\\thrift-0.12.0_2.exe";
        File file = new File(filePath);

        FileChannel fileChannel = new FileInputStream(file).getChannel();
        System.out.println(fileChannel.size());
        long start = System.currentTimeMillis();
        long count = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("共耗时 ： " + (System.currentTimeMillis() - start) + " - 总大小： " + count + "File 大小 = " + file.length() );

        fileChannel.close();
        socketChannel.close();
    }
}
