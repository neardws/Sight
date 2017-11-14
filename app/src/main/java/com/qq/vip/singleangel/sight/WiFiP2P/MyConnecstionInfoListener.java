package com.qq.vip.singleangel.sight.WiFiP2P;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;

import java.net.InetAddress;

/**
 * Created by singl on 2017/11/14.
 */

public class MyConnecstionInfoListener implements WifiP2pManager.ConnectionInfoListener {
    public MyConnecstionInfoListener(){

    }
    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
        boolean isGroupOwner = (boolean) wifiP2pInfo.isGroupOwner;  //已连接，该节点是否为GO
        InetAddress groupOwnerAddress = (InetAddress) wifiP2pInfo.groupOwnerAddress;
        String groupOwnerIPAdd = (String) groupOwnerAddress.getHostAddress();  //GO的IP地址




    }
}