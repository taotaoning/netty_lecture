package com.nt.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Create by TaoTaoNing
 * 2019/3/26
 **/
public class NIOTest10 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("niotest10.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        // 获得文件锁 参数说明 锁定文件内容的开始位置，长度， 是否是共享锁
        // 若为共享锁 则同时只能有一个流读写该部分内容
        FileLock lock = fileChannel.lock(3, 6, true);
        System.out.println("valid = " + lock.isValid());
        System.out.println("lock type = " + lock.isShared());

        // 释放锁
        lock.release();
        randomAccessFile.close();

    }
}
