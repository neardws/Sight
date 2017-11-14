package com.qq.vip.singleangel.sight.WiFiP2PSocket;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.qq.vip.singleangel.sight.ClassDefined.WiFiP2P.SocketContext;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by singl on 2017/11/14.
 */

public class ClientSocket implements Runnable {
    private String serverIp;
    private Context context;
    private SocketContext socketContext;
    public static final int SERVER_PORT = 8888;

    public ClientSocket(String serverIp, Context context, SocketContext socketContext){
        this.serverIp = serverIp;
        this.context = context;
        this.socketContext = socketContext;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket.bind(null);
            socket.connect(new InetSocketAddress(serverIp, SERVER_PORT),5000);
            try {
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
                objOutputStream.writeObject(socketContext);
                objOutputStream.flush();
                outputStream.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketContext newContext(int type, String fileUri){
        SocketContext socketContext = new SocketContext(0,null);
        ContentResolver cr = context.getContentResolver();
        InputStream is = null;
        switch (type){
            case SocketContext.SOCKET_TYPE_IP_ADDRESS:
                socketContext.setSocketType(SocketContext.SOCKET_TYPE_IP_ADDRESS);
                try {
                    is = new ByteArrayInputStream(fileUri.getBytes("utf-8"));
                    socketContext.setInputStream(is);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case SocketContext.SOCKET_TYPE_PHOTO:
                socketContext.setSocketType(SocketContext.SOCKET_TYPE_PHOTO);
                try {
                    is = cr.openInputStream(Uri.parse(fileUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                socketContext.setInputStream(is);
                break;
            case SocketContext.SOCKET_TYPE_VIDEO:
                socketContext.setSocketType(SocketContext.SOCKET_TYPE_VIDEO);
                try {
                    is = cr.openInputStream(Uri.parse(fileUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                socketContext.setInputStream(is);
                break;
        }
        return socketContext;

    }
}
