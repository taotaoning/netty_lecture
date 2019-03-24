package com.nt.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Create by TaoTaoNing
 * 2019/3/24
 **/
public class NIOTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i= 0; i<buffer.capacity();i++){
            int nextInt = new SecureRandom().nextInt(20);
            System.out.println(nextInt);
            buffer.put(nextInt);
        }
        System.out.println("============================");
        buffer.flip();//读写转换 这里时netty的buf类与nio中的buf类的主要区别：netty中带readindex writeindex 不需要读写转换
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
