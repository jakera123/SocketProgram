package com.zxp.socketprogram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainActivity extends AppCompatActivity {

    private Button startButton=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.btn_startListener);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ServerThread().start();
            }
        });


    }
    class ServerThread extends Thread{
        @Override
        public void run() {
            //以TCP协议进行Socket通信
            ServerSocket serverSocket=null;
            try {
                //4567是端口号
                serverSocket=new ServerSocket(4567);
                //接受客户端请求，阻塞请求，直接连接成功，往下执行
                Socket socket=serverSocket.accept();
                InputStream inputStream=socket.getInputStream();
                byte buffer[]=new byte[1024*4];
                int temp=0;
                while((temp=inputStream.read(buffer))!=-1){
                    System.out.println(new String(buffer,0,temp));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try{
                    serverSocket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            //以UDP方式进行Socket通信
            try {
                //这个DatagramSocket采用UDP协议，并指定监听的端口号
                DatagramSocket socket=new DatagramSocket(4567);
                byte data[]=new byte[1024];
                //创建一个空的DatagramPacket对象
                DatagramPacket packet=new DatagramPacket(data,data.length);
                //接收客户端的发送的数据
                socket.receive(packet);
                String reusult=new String(packet.getData(),packet.getOffset(),packet.getLength());
                System.out.println(reusult);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
