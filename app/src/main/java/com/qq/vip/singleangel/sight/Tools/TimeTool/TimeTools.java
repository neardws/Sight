package com.qq.vip.singleangel.sight.Tools.TimeTool;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by singl on 2017/11/14.
 */

public class TimeTools {
    private Timer timer;
    private NtpTime ntpTime;

    public TimeTools(){
        init();
    }

    public  void syncTime(){
        ntpTime.startSyncTime();
    }

    public void stopSyncTime(){
        ntpTime.stopSyncTime();
    }

    public void init(){

        ntpTime = new NtpTime();

    }

    private void setTimerTask() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                doActionHandler.sendMessage(message);
            }
        }, 1000, 1000/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);
    }

    /**
     * do some action
     */
    private Handler doActionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int msgId = msg.what;
            switch (msgId) {
                case 1:
                    // do some action
                    break;
                default:
                    break;
            }
        }
    };

}
