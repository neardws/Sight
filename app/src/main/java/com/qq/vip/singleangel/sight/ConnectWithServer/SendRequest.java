package com.qq.vip.singleangel.sight.ConnectWithServer;

import com.qq.vip.singleangel.sight.ClassDefined.Server.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;

/**
 * Created by singl on 2017/11/15.
 */

public class SendRequest implements Runnable {
    private Request request;

    public SendRequest(Request request){
        this.request = request;
    }

    @Override
    public void run() {
        Socket socket = new Socket();
        try {
            socket.bind(null);
            /**
             * * 连接时需要IP address. port . 5000是重连时间，应该是5S
             */
            socket.connect(new InetSocketAddress("120.78.167.211",33366),5000);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();
            objectOutputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
