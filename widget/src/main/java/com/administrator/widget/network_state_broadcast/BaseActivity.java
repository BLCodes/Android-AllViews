package com.administrator.widget.network_state_broadcast;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity {
    
    private NetworkStateReceiver receiver;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initTipView();
        setNetReceiver();

    }
    public void setNetReceiver(){
        receiver = new NetworkStateReceiver(new NetworkCallBack() {
            @Override
                public void networkChangeCallback(int type) {
                    switch (type) {
                        case NetWorkeUtil.WIFY_CONNECTED:
                            textView.setText("无线网已经连接上");
                            break;
                        case NetWorkeUtil.MOBILE_CONNECTED:
                            textView.setText("移动数据已经连接上");
                            break;
                        case NetWorkeUtil.NET_CONNECTED:
                            textView.setText("网络已经连接上！！");
                            break;
                        case NetWorkeUtil.NO_NET:
                            textView.setText("当前无网路");
                            break;
                    }
            }
        });
        IntentFilter filter = new IntentFilter();//动态
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }

    public abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        if (textView != null && textView.getParent() != null) {
            mWindowManager.removeView(textView);
        }
        super.onDestroy();
    }

    WindowManager mWindowManager;

    private void initTipView() {
        textView = new TextView(this);
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);//窗口服务
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        //使用非CENTER时，可以通过设置XY的值来改变View的位置
        mLayoutParams.gravity = Gravity.TOP;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
        mWindowManager.addView(textView,mLayoutParams);
    }
}
