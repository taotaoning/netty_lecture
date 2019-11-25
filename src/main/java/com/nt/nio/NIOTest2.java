package com.nt.nio;

import java.io.FileInputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by TaoTaoNing
 * 2019/3/24
 **/

public class NIOTest2 {
    public static void main(String[] args) throws Exception{
//        String resource = NIOTest2.class.getClassLoader().getResource("").getPath();
//        System.out.println(resource);
        FileInputStream inputStream = new FileInputStream( "G:\\code\\source\\netty---videos\\netty_lecture\\src" +
                "\\main\\java\\nio.txt");
        FileChannel channel = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.remaining() != -1){
            byte x = byteBuffer.get();
            System.out.println((char) x);
        }
        inputStream.close();
    }
}
