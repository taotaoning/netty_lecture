package com.nt.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by TaoTaoNing
 * 2019/3/24
 **/
public class NIOTest3 {
    public static void main(String[] args) throws Exception{
        FileOutputStream outputStream = new FileOutputStream("nio_test.txt");
        FileChannel channel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] data = "hello java nio ".getBytes();
         for (int i = 0;i<data.length;i++){
            byteBuffer.put(data[i]);
        }
        byteBuffer.flip();

        channel.write(byteBuffer);
        outputStream.close();
    }
}
