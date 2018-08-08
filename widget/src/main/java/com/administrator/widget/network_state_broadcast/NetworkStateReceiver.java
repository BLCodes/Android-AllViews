package com.administrator.widget.network_state_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class NetworkStateReceiver extends BroadcastReceiver {
    private static NetworkCallBack networkCallBack;

    public NetworkStateReceiver() {
    }

    public NetworkStateReceiver(NetworkCallBack networkCallBack) {
        this.networkCallBack = networkCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        networkCallBack.networkChangeCallback(NetWorkeUtil.getNetworkInfo(context));
    }
}
