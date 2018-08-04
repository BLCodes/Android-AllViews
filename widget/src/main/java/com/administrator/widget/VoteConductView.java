package com.administrator.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class VoteConductView extends View {
    private static final String TAG = "VoteConductView";

    private float linePaddingX = 15;

    ArrayList<Integer> iList = new ArrayList<>();
    ArrayList<String> sList = new ArrayList<>();

    float width;
    int height;

    int mHeight;//圆柱条的底部到TextView下面的高度

    private float mWidth;

    public VoteConductView(Context context) {
        this(context,null);
    }

    public VoteConductView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VoteConductView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        mHeight = height - 40;
        if (sList.size() == 0 || iList.size() == 0) {//防止报空
            return;
        }
        mWidth = ((width - 30)/ ((sList.size()*2)+1));//30代表线的两边是15的间距；再把剩下的宽度分成投票总数和间距总数；就算出每个投票矩形和矩形之间的间距宽

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.app_run));
        mPaint.setStrokeWidth(2);
        canvas.drawLine(15,mHeight,width-15,mHeight,mPaint);
        mPaint.setColor(getResources().getColor(R.color.app_color));

        //文本高度
        float textHeight = mPaint.descent()-mPaint.ascent();
        Rect rect = new Rect();

        for (int i = 0; i < sList.size(); i++) {
            if (i==0){ //假定
                canvas.drawRect((2*i+1) * mWidth+linePaddingX
                        ,(mHeight-textHeight)/5 + textHeight
                        ,(2*i+2) * mWidth+linePaddingX
                        ,mHeight,mPaint);
            }else {
                canvas.drawRect((2*i+1) * mWidth + linePaddingX
                        ,mHeight - (mHeight - textHeight - (mHeight - textHeight)/5) * iList.get(i)/iList.get(0)
                        ,(2*i+2) * mWidth+linePaddingX
                        ,mHeight,mPaint);
            }

            mPaint.getTextBounds(sList.get(i), 0, sList.get(i).length(), rect);
            int textWidth = rect.width();//文本的宽度

            canvas.drawText(""+iList.get(i),(2*i+1) * mWidth+15
                    ,mHeight - (mHeight - textHeight - (mHeight - textHeight)/5) * iList.get(i)/iList.get(0)-textHeight/2,mPaint);
            canvas.drawText(""+sList.get(i),(2*i+1) * mWidth+15 + mWidth/2-textWidth/2,height-20,mPaint);
        }
    }

    public void setiList(ArrayList<Integer> iList, ArrayList<String> sList) {
        if (sList.size() == 0 || iList.size() == 0) {//防止报空
            return;
        }
        this.iList = iList;
        this.sList = sList;

        mWidth = ((width - 30)/ (sList.size()*(2+1)));//30代表线的两边是15的间距；再把剩下的宽度分成投票总数和间距总数；就算出每个投票矩形和矩形之间的间距宽

        invalidate();
    }
}
