package com.qq.vip.singleangel.sight.UI;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qq.vip.singleangel.sight.R;

import java.util.List;

import static com.qq.vip.singleangel.sight.WiFiP2P.MyPeerListListener.getDeviceStatus;

/**
 * Created by singl on 2017/11/14.
 */

public class WiFiPeerListAdapter extends ArrayAdapter<WifiP2pDevice> {
    private List<WifiP2pDevice> items;

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public WiFiPeerListAdapter(Context context, int textViewResourceId,
                               List<WifiP2pDevice> objects) {
        super(context, textViewResourceId, objects);
        items = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row_device, null);
        }


        WifiP2pDevice device = items.get(position);
        if (device != null) {
            TextView top = (TextView) v.findViewById(R.id.device_name);
            TextView bottom = (TextView) v.findViewById(R.id.device_details);
            if (top != null) {
                top.setText(device.deviceName);
            }
            if (bottom != null) {
                bottom.setText(getDeviceStatus(device.status));
                if (device.status == WifiP2pDevice.CONNECTED) {

                }else if (device.status == WifiP2pManager.BUSY){

                }else if (device.status == WifiP2pManager.ERROR){

                }
            }
        }


        return v;

    }

}
