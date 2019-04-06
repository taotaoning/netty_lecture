package com.nt.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Create by TaoTaoNing
 * 2019/4/6
 **/
public class NioTest13 {
    public static void main(String[] args) throws Exception {
        String inputFile = "niotest13_input.txt";
        String outputFile = "niotest13_output.txt";

        RandomAccessFile inRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        FileChannel inRandomAccessFileChannel = inRandomAccessFile.getChannel();
        FileChannel outRandomAccessFileChannel = outRandomAccessFile.getChannel();

        long length = new File(inputFile).length();

        MappedByteBuffer map = inRandomAccessFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);

      //  Charset charset = Charset.forName("utf-8");
        Charset charset = Charset.forName("iso-8859-1");

        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(map);

        ByteBuffer byteBuffer = encoder.encode(charBuffer);

        outRandomAccessFileChannel.write(byteBuffer);

        outRandomAccessFile.close();
        inRandomAccessFile.close();


    }
}
