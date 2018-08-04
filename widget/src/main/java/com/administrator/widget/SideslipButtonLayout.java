package com.administrator.widget;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

//更换验证码登录或者密码登录
public class SideslipButtonLayout extends LinearLayout {

    private Context mContext;

    private RelativeLayout mLoginProving;
    private RelativeLayout mLoginPassword;

    int width;
    int height;
    int i = 0;


    public SideslipButtonLayout(Context context) {
        this(context,null);
    }

    public SideslipButtonLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SideslipButtonLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        setOrientation(HORIZONTAL);

        mContext = context;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);


        for (int i1 = 0; i1 < getChildCount(); i1++) {
            (getChildAt(i1)).measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mLoginProving = findViewById(R.id.id_previous);
        mLoginPassword = findViewById(R.id.id_next);

    }


    public void mClick(){
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(mContext, R.animator.go_animation);
            if (i == 0){

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        mLoginProving.setTranslationX(-(width/1000.0f*value));
                        mLoginProving.setAlpha(1/1000.0f*(1000-value));
                        mLoginPassword.setAlpha(1/1000.0f*(value));
                        mLoginPassword.setTranslationX(-(width/1000.0f*value));
                    }
                });
                i = 1;

            } else {
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        mLoginProving.setTranslationX((width/1000.0f*(value-1000)));
                        mLoginPassword.setTranslationX((width/1000.0f*(value-1000)));
                        mLoginPassword.setAlpha(1/1000.0f*(1000-value));//Alpha渐变
                        mLoginProving.setAlpha(1/1000.0f*(value));
                    }
                });
                i = 0;

            }animator.start();
    }
}
