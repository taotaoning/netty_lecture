package com.nt.nio;

import java.nio.ByteBuffer;

/**
 * Create by TaoTaoNing
 * 2019/3/25
 **/
public class NIOTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(45);
        byteBuffer.putLong(45666665L);
        byteBuffer.putChar('你');
        byteBuffer.putShort((short)88);
        byteBuffer.putFloat((float)12.21212121);
        byteBuffer.putDouble(45.54654654654444444);
        byteBuffer.putInt(99);

        byteBuffer.flip();

        //按顺序取
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getFloat());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getInt());
    }
}
