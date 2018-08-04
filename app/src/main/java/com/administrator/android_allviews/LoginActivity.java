package com.administrator.android_allviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.administrator.widget.SideslipButtonLayout;
import com.administrator.widget.ZoomRotateAnimation;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    SideslipButtonLayout buttonLayout;
    ZoomRotateAnimation rotateAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.tv_login_code);
        textView.setOnClickListener(this);

        rotateAnimation = findViewById(R.id.tv_login_login);
        rotateAnimation.setOnClickListener(this);

        buttonLayout = findViewById(R.id.ll_login_ll);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.tv_login_code:
                buttonLayout.mClick();

                break;

            case R.id.tv_login_login:
                rotateAnimation.startAnimation();

                break;



        }

    }
}
