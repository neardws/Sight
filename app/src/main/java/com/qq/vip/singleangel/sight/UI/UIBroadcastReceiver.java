package com.qq.vip.singleangel.sight.UI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qq.vip.singleangel.sight.Activity.MainActivity;
import com.qq.vip.singleangel.sight.ClassDefined.Server.HeartBeatsInfo;
import com.qq.vip.singleangel.sight.ClassDefined.WiFiP2P.Device;

import java.util.Set;

/**
 * Created by singl on 2017/11/15.
 */

public class UIBroadcastReceiver extends BroadcastReceiver {
    public static final String UPDATE_DEVICE = "Update_device";
    public static final String SET_IP_MAC = "Set_ip_mac";

    private MainActivity mainActivity;

    public UIBroadcastReceiver(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case UPDATE_DEVICE:
                Device device = (Device) intent.getParcelableExtra(UPDATE_DEVICE);
                mainActivity.setThisDevice(device);
                break;
            case SET_IP_MAC:
                HeartBeatsInfo info = (HeartBeatsInfo) intent.getParcelableExtra(SET_IP_MAC);
                mainActivity.setIPandMac(info.getIpAdd(), info.getMacAdd());
                break;
            default:
                break;
        }
    }
}
