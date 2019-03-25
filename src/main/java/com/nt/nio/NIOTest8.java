package com.nt.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by TaoTaoNing
 * 2019/3/25
 * 直接内存上的Buffer
 **/
public class NIOTest8 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output_direct.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        /**
         * DirectByteBuffer: 直接使用的时jvm内存之外的内存，即系统内存
         * 零拷贝： HeapByteBuffer 使用的时数组存储数据，在执行io操作时，会将数据拷贝到系统内存执行，因为数据的
         * 操作是由底层系统执行的，（为什么系统不直接操作jvm上heap内存呢 ：因为jvm时长发生gc操作，gc之后
         * jvm会进行内存压缩（即释放垃圾内存后，剩余的内存集中，留下足够大的内存给其他对象），因而系统可能会找不到
         * 存放在jvm上数据的内存地址，所以需要将数据拷贝到系统内存处理，而DirectByteBuffer是直接通过java底层
         * 代码（c或者c++编写的native方法）将数据放到系统内存处理，而DirectByteBuffer的顶层类中的Long address
         * 属性是指向系统内存地址的引用。
         */
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        while (true){
            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);
            if (-1 == read){
                break;
            }
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }

        inputStream.close();
        outputStream.close();
    }
}
