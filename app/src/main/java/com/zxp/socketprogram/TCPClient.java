package com.zxp.socketprogram;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xiaoxin on 2017/6/12.
 */

public class TCPClient {
    public static void main(String[] args){
        try {
            Socket socket=new Socket("192.168.1.103",4567);
            InputStream inputStream=new FileInputStream("f://file/words.txt");
            OutputStream outputStream=socket.getOutputStream();
            byte buffer[]=new byte[4*1024];
            int temp=0;
            while((temp=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,temp);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
