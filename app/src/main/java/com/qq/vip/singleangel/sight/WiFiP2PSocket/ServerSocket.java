package com.qq.vip.singleangel.sight.WiFiP2PSocket;

import android.os.Environment;

import com.qq.vip.singleangel.sight.ClassDefined.WiFiP2P.SocketContext;
import com.qq.vip.singleangel.sight.Tools.FileTool.MyFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by singl on 2017/11/14.
 */

public class ServerSocket implements Runnable{
    public static final int SERVER_PORT = 8888;
    @Override
    public void run() {
        try {
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(SERVER_PORT);
            while (true){
                Socket client = serverSocket.accept();
                try {
                    Socket socket = client;
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    SocketContext socketContext = (SocketContext) objectInputStream.readObject();
                    handleContext(socketContext);
                    socket.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void handleContext(SocketContext socketContext){
        int type = socketContext.getSocketType();
        InputStream inputStream = socketContext.getInputStream();
        switch (type){
            case SocketContext.SOCKET_TYPE_IP_ADDRESS:
                String ipAdd = inputStream.toString();
                break;
            case SocketContext.SOCKET_TYPE_PHOTO:
                try{
                    final File f = new File(Environment.getExternalStorageDirectory() + "/"
                            + "com.qq.viq.singleangel" + "/receive-" + System.currentTimeMillis()
                            + ".jpg");

                    File dirs = new File(f.getParent());
                    if (!dirs.exists())
                        dirs.mkdirs();
                    f.createNewFile();
                    MyFile.copyFile(inputStream, new FileOutputStream(f));
                }catch (IOException e){
                    e.printStackTrace();
                }


                break;
            case SocketContext.SOCKET_TYPE_VIDEO:
                try{
                    final File f = new File(Environment.getExternalStorageDirectory() + "/"
                            + "com.qq.viq.singleangel" + "/receive-" + System.currentTimeMillis()
                            + ".mp4");

                    File dirs = new File(f.getParent());
                    if (!dirs.exists())
                        dirs.mkdirs();
                    f.createNewFile();
                    MyFile.copyFile(inputStream, new FileOutputStream(f));
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
        }
        //deal with socketContext
    }
}
