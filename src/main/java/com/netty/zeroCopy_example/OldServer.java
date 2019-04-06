package com.netty.zeroCopy_example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create by TaoTaoNing
 * 2019/4/6
 * 是一个简单的例子，客户端发送一个文件到服务端，然后查看客户端所消耗的时间
 **/
public class OldServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8898);

        while (true) {
            Socket accept = serverSocket.accept();
            DataInputStream dataOutputStream = new DataInputStream(accept.getInputStream());
            byte[] byteArray = new byte[4096];
            int read = 0;
            long total = 0;
            while (-1 != (read = dataOutputStream.read(byteArray))) {
                total += read;
            }
            System.out.println(total);
            dataOutputStream.close();
        }


    }
}
