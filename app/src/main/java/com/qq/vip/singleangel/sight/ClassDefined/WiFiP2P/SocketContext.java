package com.qq.vip.singleangel.sight.ClassDefined.WiFiP2P;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by singl on 2017/11/14.
 */
public class SocketContext implements Serializable {
    public static final int SOCKET_TYPE_IP_ADDRESS = 1;
    public static final int SOCKET_TYPE_PHOTO = 2;
    public static final int SOCKET_TYPE_VIDEO =3;

    private int SocketType;
    private InputStream inputStream;

    public SocketContext(int Sockettype, InputStream inputStream){
        this.SocketType = Sockettype;
        this.inputStream = inputStream;
    }

    public int getSocketType(){
        return this.SocketType;
    }

    public InputStream getInputStream(){
        return this.inputStream;
    }

    public void setSocketType(int socketType){
        this.SocketType = socketType;
    }

    public void setInputStream(InputStream inputStream){
        this.inputStream = inputStream;
    }

}