package com.administrator.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author zhang
 *
 */

public class NinthPalaceViewGroup extends ViewGroup {

    private static final String TAG = "NinthPalaceViewGroup";

    private int itemWidth;
    private int itemGap;//todo 自定义属性

    private ArrayList<Integer> data = new ArrayList<>();


    public NinthPalaceViewGroup(Context context) {
        this(context, null);
    }

    public NinthPalaceViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NinthPalaceViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        removeAllViews();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NinthPalaceViewGroup);
        itemGap = typedArray.getDimensionPixelOffset(R.styleable.NinthPalaceViewGroup_itemgap,0);
        typedArray.recycle();


        for (int i = 0; i < 12; i++) {
            data.add(R.drawable.ic_launcher_background);
        }
        addChild(context, attrs, defStyleAttr);
    }

    private void addChild(Context context, AttributeSet attrs, int defStyleAttr) {
        if (data.size() == 1) {
            ImageView view = new ImageView(context);
            view.setBackgroundResource(data.get(0));
            // TODO: 2018/6/27  计算原始图片的大小，如果宽大于父亲的宽就把它等比缩小到父亲的宽那么大，同理
            LayoutParams lp = new LayoutParams(44, 44);
            addView(view, lp);




        } else {
            for (int i = 0; i < data.size(); i++) {
                if (i < 9) {
                    ImageView view = new ImageView(context);
                    view.setBackgroundResource(data.get(i));
                    addView(view);
                }
            }
        }
        //
        if(getChildCount() > 8){
            TextView tv = new TextView(context);
            tv.setBackgroundColor(context.getResources().getColor(R.color.color_overlay));
            tv.setText("+"+(data.size()-9));
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(48);
            tv.setGravity(Gravity.CENTER);
            addView(tv);
        }

    }


    /**
     * @param widthMeasureSpec  父亲指定孩子的测量宽的结果，widthMeasureSpec代表了宽的类型以及具体的宽度,前4位代表类型，比如Exactly_MOde：20dp，11dp。。。
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //1、获取测量的宽高
        int width = 0;

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY || //指明具体尺寸
                MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) { //match_parent
            width = MeasureSpec.getSize(widthMeasureSpec);
        }


        //2、计算item的宽
        if (getChildCount() > 1) {
            itemWidth = (width - itemGap * 2) / 3;
        }
        //3、测绘设置孩子的宽高
        int widthSpec = 0;
        int heightSpec = 0;
        if (getChildCount() == 1) {
            widthSpec = MeasureSpec.makeMeasureSpec(44, MeasureSpec.EXACTLY);
            heightSpec = MeasureSpec.makeMeasureSpec(44, MeasureSpec.EXACTLY);
            Log.i(TAG, "onMeasure: ");
        } else {
            widthSpec = MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY);
            heightSpec = MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY);
        }
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(widthSpec, heightSpec);
        }
        //4、计算布局的高
        if (getChildCount() == 1) {
            setMeasuredDimension(width, 44);
        } else {
            int cont = 0;
            cont = getChildCount()>9?9:getChildCount();
            setMeasuredDimension(width, ((cont - 1) / 3 + 1) * (itemWidth) + (cont - 1) / 3 * itemGap);
        }
    }

    @Override
    protected void onLayout(boolean b, int l, int t, int r, int bot) {
        //1、只有一个孩子
        if (getChildCount() == 1) {
            Log.i(TAG, "onLayout: " + getChildAt(0).getWidth());
            getChildAt(0).layout(0, 0, 44, 44);

        } else if (getChildCount() == 4) {//2、4个孩子
            getChildAt(0).layout(0, 0, itemWidth, itemWidth);
            getChildAt(1).layout(itemGap + itemWidth, 0, itemWidth * 2 + itemGap, itemWidth);
            getChildAt(2).layout(0, itemGap + itemWidth, itemWidth, itemWidth * 2 + itemGap);
            getChildAt(3).layout(itemGap + itemWidth, itemGap + itemWidth, itemWidth * 2 + itemGap, itemWidth * 2 + itemGap);

        } else {//

            for (int i = 0; i < getChildCount(); i++) {
                if(i<9){
                    getChildAt(i).layout((i % 3) * (itemGap + itemWidth)
                            , (i / 3) * (itemGap + itemWidth)
                            , (i % 3) * (itemGap + itemWidth) + itemWidth
                            , (i / 3) * (itemGap + itemWidth) + itemWidth);
                }else {
                    getChildAt(i).layout((8 % 3) * (itemGap + itemWidth)
                            , (8 / 3) * (itemGap + itemWidth)
                            , (8 % 3) * (itemGap + itemWidth) + itemWidth
                            , (8 / 3) * (itemGap + itemWidth) + itemWidth);
                }


            }
        }
    }
}
