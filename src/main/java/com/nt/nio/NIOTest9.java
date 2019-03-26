package com.nt.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Create by TaoTaoNing
 * 2019/3/26
 * MappedByteBuffer 可以直接在内存修改文件，实时同步到修改磁盘文件 (由操作系统实现)
 **/
public class NIOTest9 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("niotest9.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        // 参数说明 读写模式， 起始位置 ， 读写长度 byte为单位
        MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0,(byte)'a');
        map.put(3,(byte)'d');
        randomAccessFile.close();

    }
}
