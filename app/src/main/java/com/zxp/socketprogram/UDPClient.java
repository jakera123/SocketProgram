package com.zxp.socketprogram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by xiaoxin on 2017/6/12.
 */

public class UDPClient {
    public static void main(String[] args){

        try {
            DatagramSocket socket=new DatagramSocket(4567);
            InetAddress serverAddress=InetAddress.getByName("192.168.1.103");
            String str="hello";
            byte data[]=str.getBytes();
            DatagramPacket packet=new DatagramPacket(data,data.length,serverAddress,4567);
            socket.send(packet);
        } catch (SocketException e) {
            e.printStackTrace();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
