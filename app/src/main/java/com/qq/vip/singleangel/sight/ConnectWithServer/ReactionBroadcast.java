package com.qq.vip.singleangel.sight.ConnectWithServer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qq.vip.singleangel.sight.ClassDefined.Server.Action;
import com.qq.vip.singleangel.sight.WiFiP2P.WiFiP2PService;

/**
 * Created by singl on 2017/11/14.
 */

public class ReactionBroadcast extends BroadcastReceiver {

    public ReactionBroadcast(){}
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action){
            case Action.START_DISCOVER:
                Intent sdIntent = new Intent(context, WiFiP2PService.class);
                sdIntent.setAction(WiFiP2PService.DISCOVERY);
                context.startService(sdIntent);
                break;
            case Action.START_CONNECT:
                Intent scIntent = new Intent(context, WiFiP2PService.class);
                scIntent.setAction(WiFiP2PService.CONNECT);
                context.startService(scIntent);
                break;
            case Action.STOP_DISCOVER:
                break;
            case Action.STOP_CONNECT:
                break;
            default:
                break;
        }
    }
}
