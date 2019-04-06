package com.netty.zeroCopy_example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Create by TaoTaoNing
 * 2019/4/6
 **/
public class OldClient {
    public static void main(String[] args)throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8898));

        String filePath =  "G:\\exe安裝文件夾\\thrift-0.12.0_2.exe";
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int read = 0;
        long total = 0;
        long start = System.currentTimeMillis();
        while ((read = fileInputStream.read(buffer)) !=  -1){
            dataOutputStream.write(buffer,0,read);
            total += read;
        }
        System.out.println("共耗时： " + (System.currentTimeMillis() - start) + " :总字节 ："+ total);
        fileInputStream.close();
        dataOutputStream.close();
        socket.close();



    }
}
