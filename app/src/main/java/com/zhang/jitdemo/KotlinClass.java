package com.zhang.jitdemo;

import android.content.Context;
import com.wushiyi.util.NonNullMap;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by zhangyuncai on 2019/6/26.
 */
public class KotlinClass {
    private void test()
    {
        NonNullMap map=new NonNullMap();
        map.put(null,null);
    }

    public static String getLocalIpAddress(Context context) {
        String localIp = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        localIp = inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return localIp;
    }
}
