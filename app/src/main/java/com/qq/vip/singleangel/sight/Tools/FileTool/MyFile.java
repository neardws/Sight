package com.qq.vip.singleangel.sight.Tools.FileTool;

import android.os.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by singl on 2017/11/14.
 */

public class MyFile {

    private static final String TAG = "MyFile";

    private static final String FILE_URL = Environment.getExternalStorageDirectory() + "/"
            + "com.qq.vip.singleangel.wifi_direct_demo"  + "/wificlientip" + ".txt";


    public static boolean copyFile(InputStream inputStream, OutputStream out) {
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                out.write(buf, 0, len);

            }
            out.close();
            inputStream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }



}