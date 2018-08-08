package com.administrator.widget.network_state_broadcast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;

import android.net.NetworkInfo;
import android.os.Build;

public class NetWorkeUtil {
    public final static int WIFY_CONNECTED = 0x110;
    public final static int MOBILE_CONNECTED = 0x111;
    public final static int NET_CONNECTED = 0x112;
    public final static int NO_NET = 0x113;

    public static int getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {//sdk版本小于21
            //获得wify的状态
            NetworkInfo wifyNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifyNetInfo.isConnected()) {//只要wify连接，就不用管移动数据
                return WIFY_CONNECTED;

            } else {
                if (mobileNetInfo.isConnected()) {//移动数据连接
                    return MOBILE_CONNECTED;
                } else {//当前无网络
                    return NO_NET;
                }
            }

        } else {//sdk>=21
            Network[] netWorkers = connectivityManager.getAllNetworks();
            for (int i = 0; i < netWorkers.length; i++) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(netWorkers[i]);
                if (networkInfo.isConnected()) {//有网
                    return NET_CONNECTED;
                }
            }
        }
        return NO_NET;
    }
}
