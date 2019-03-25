package com.nt.nio;

import java.io.BufferedReader;
import java.nio.ByteBuffer;

/**
 * Create by TaoTaoNing
 * 2019/3/25
 **/
public class NIOTest6 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i=0;i<byteBuffer.capacity();i++){
            byteBuffer.put((byte)i);

        }
        byteBuffer.position(2);
        byteBuffer.limit(6);

        ByteBuffer silece = byteBuffer.slice(); // silece 与byteBuffer对2-6 位置的修改，会相互影响

        while (silece.hasRemaining() ){
            System.out.println(silece.get());
        }
    }
}
