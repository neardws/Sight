package com.qq.vip.singleangel.sight.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.qq.vip.singleangel.sight.ClassDefined.Server.Action;
import com.qq.vip.singleangel.sight.ClassDefined.Server.Request;
import com.qq.vip.singleangel.sight.ClassDefined.WiFiP2P.Device;
import com.qq.vip.singleangel.sight.ConnectWithServer.HeartBeatsService;
import com.qq.vip.singleangel.sight.ConnectWithServer.SendRequest;
import com.qq.vip.singleangel.sight.R;
import com.qq.vip.singleangel.sight.UI.UIBroadcastReceiver;
import com.qq.vip.singleangel.sight.WiFiP2P.WiFiP2PService;

public class MainActivity extends AppCompatActivity {
    private Button btnRequest;
    private Button btnIP;
    private TextView tv_ipAdd;
    private TextView tv_macAdd;
    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_is_disc;
    private TextView tv_is_con;
    private TextView tv_is_go;
    private ListView peersView;

    private BroadcastReceiver receiver = null;
    private IntentFilter intentFilter = new IntentFilter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = (Button) findViewById(R.id.btn_request);
        btnIP = (Button) findViewById(R.id.btn_ip);
        tv_ipAdd = (TextView) findViewById(R.id.tv_ip_add);
        tv_macAdd = (TextView) findViewById(R.id.tv_mac_add);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_is_disc = (TextView) findViewById(R.id.tv_is_discover);
        tv_is_con = (TextView) findViewById(R.id.tv_is_connect);
        tv_is_go = (TextView) findViewById(R.id.tv_is_go);
        peersView = (ListView) findViewById(R.id.list);

        //开启服务
        Intent heartBeatsIntent = new Intent(this, HeartBeatsService.class);
        startService(heartBeatsIntent);
        Intent wifiP2PIntent = new Intent(this, WiFiP2PService.class);
        startService(wifiP2PIntent);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request(Request.INITIATIVE);
                new Thread(new SendRequest(request)).start();
            }
        });

        intentFilter.addAction(UIBroadcastReceiver.SET_IP_MAC);
        intentFilter.addAction(UIBroadcastReceiver.UPDATE_DEVICE);

    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * UI更新
     */
    public void setIPandMac(String ip, String mac){
        tv_ipAdd.setText("IP地址："+ ip);
        tv_macAdd.setText("MAC地址"+ mac);
    }
    public void setThisDevice(Device device){
        tv_id.setText("设备ID：" + device.getDeviceID());
        tv_name.setText("设备名称：" + device.getDeviceName());
        tv_is_disc.setText("发现是否完成：" + String.valueOf(device.isDiscover()));
        tv_is_con.setText("连接是否完成：" + String.valueOf(device.isConnected()));
        tv_is_go.setText("是否为GroupOwner: " + String.valueOf(device.isGroupOwner()));
    }
}
