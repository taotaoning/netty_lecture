package com.nt.nio;

import java.nio.ByteBuffer;

/**
 * Create by TaoTaoNing
 * 2019/3/25
 * 只读Buffer
 **/
public class NIOTest7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.getClass());
        for( int i = 0;i<byteBuffer.capacity();i++)
        {
            byteBuffer.put((byte) i);
        }

        ByteBuffer readonly = byteBuffer.asReadOnlyBuffer();
        System.out.println(readonly.getClass());
    }

}
