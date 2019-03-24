package com.nt.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by TaoTaoNing
 * 2019/3/24
 **/
public class NIOTest2 {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("G:\\code\\idea_code\\netty_lecture\\src\\main\\java\\nio.txt");
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
