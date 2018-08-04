package com.administrator.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;


public class ZoomRotateAnimation extends TextView {
    private static final String TAG = "LoginTextView";
    private Context mContext;

    private int width;
    private int height;
    private int change;
    private Paint paint;
    private float left;
    private float right;
    private float startAngle1;

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    private int color;


    public ZoomRotateAnimation(Context context) {
        this (context,null);
    }

    public ZoomRotateAnimation(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZoomRotateAnimation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZoomRotateAnimation);
        paint = new Paint();
        paint.setAntiAlias(true);
        color = typedArray.getColor(R.styleable.ZoomRotateAnimation_login_color,0);
        paint.setColor(color);
        typedArray.recycle();//
        mContext = context;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        change = width - height >0 ? width - height : 0;
        //初始化矩形高宽
        left = 0;
        right = width;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        RectF rectF = new RectF();
        rectF.left = left;
        rectF.top = 0;
        rectF.right = right;
        rectF.bottom = height;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRoundRect(rectF,height/2,height/2,paint);


        if (startArc) {///
            RectF mRectF = new RectF();
            mRectF.left = left+10;
            mRectF.top = 10;
            mRectF.right = right-10;
            mRectF.bottom = height-10;
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.WHITE);
            canvas.drawArc(mRectF,startAngle1,(startAngle1+270)%360,false,paint);
        }


        super.onDraw(canvas);
    }

    private boolean startArc;

    public void startAnimation(){
        final ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(mContext, R.animator.go_animation);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                if (value > 500) {
                    setText("");
                }

                left = (width/2 - height/2)/1000.0f * value;
                right = width - (width-(width+height)/2)/1000.0f*value;
                invalidate();
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener(){


            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {


                startTwoAnimator();
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();

    }

    public void startTwoAnimator(){
        ValueAnimator arcValueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(mContext, R.animator.go_animation);
        arcValueAnimator.setDuration(1000);
        arcValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        arcValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        arcValueAnimator.setInterpolator(new LinearInterpolator());
        arcValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int startAngle= (int) animation.getAnimatedValue();
                startAngle1 = startAngle * 0.36f;
                startArc = true;
                invalidate();
            }
        });
        arcValueAnimator.start();

        arcValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


    public interface onLoginListener{
         void listener();
    }

    private onLoginListener listener;

    public void setonLoginLisrener(onLoginListener listener){
        this.listener = listener;
    }

}