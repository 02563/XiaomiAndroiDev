package com.example.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.location.Location;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.view.R.color;

import java.util.List;

public class TagCloud2 extends FrameLayout {
    private static final String TAG = "TagCloud";
    private float mHorizontalMargin = 10;
    private float mVerticalMargin = 10;
    private List<String> mTags = null;
    private GestureDetector mDetector;
    private float lastX, lastY;
    private int pressedTagIndex = -1;
    private int originalLeft, originalTop;

    public TagCloud2(@NonNull Context context) {
        super(context);
    }

    public TagCloud2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagCloud);
        mHorizontalMargin = a.getDimension(R.styleable.TagCloud_hMargin, 20);
        mVerticalMargin = a.getDimension(R.styleable.TagCloud_vMargin, 10);
        a.recycle();

        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View childView = getChildAt(i);
            childView.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }
        mDetector = new GestureDetector(this.getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

    }

    public void setTag(List<String> tags) {
        if (mTags != tags) {
            mTags = tags;
            int tagCount = mTags != null ? mTags.size() : 0;//获取总标签数
            int childCount = getChildCount();//获取现有的子View数
            if (tagCount > childCount) {//总标签数大于子View数，向后追加
                for (int i = childCount; i < tagCount; i++) {
                    TextView child = new TextView(getContext());
                    child.setTextSize(25);//字体大小
                    child.setMaxLines(1);//最多一行
                    child.setEllipsize(TextUtils.TruncateAt.END);//截断方式
                    child.setBackgroundResource(R.color.purple_200);// 背景色
                    addView(child, i);
                }
            } else if (tagCount < childCount) {//标签数量小于子View数量，移除
                for (int i = childCount; i > tagCount; i--) {
                    removeViewAt(i - 1);
                }
            }
        }

        for (int i = 0; i < getChildCount(); i++) {
            ((TextView) getChildAt(i)).setText(mTags.get(i));//设置Text
        }
        requestLayout(); // 触发重新布局
    }

    private static class Location {
        int left;
        int top;
        int right;
        int bottom;

        Location(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }
    //@Override
    //protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    //    //遍历全部的子view，获取位置分发layout
    //    for
    //    (int i = 0; i < getChildCount(); i++) {
    //        View child = getChildAt(i);
    //        Location location = (Location) child.getTag();
    //        child.layout(location.left, location.top, location.right, location.bottom);
//
    //    }
    //}

    //====-2===重写view方法=====+=== start
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthspecMode = MeasureSpec.getMode(heightMeasureSpec); //传入的测量模式 EXACTLY
        int widthSpecsize = MeasureSpec.getSize(widthMeasureSpec); //宽的尺寸 1440

        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec); //传入的测量模式 AT_MOST
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//高的尺寸 2392

        int childwidthSpec;
        switch (widthspecMode) {
            case MeasureSpec.AT_MOST:

            case MeasureSpec.EXACTLY:
                //计算需要传给子View的 Measurespec
                // 1440-20-20 AT_MOST
                childwidthSpec = MeasureSpec.makeMeasureSpec(widthSpecsize - (int) mHorizontalMargin * 2, MeasureSpec.AT_MOST);
                // Measu
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                childwidthSpec = widthMeasureSpec;
                break;
        }
        int childHeightSpec;
        switch (heightSpecMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                //计算需要传给子View的 MeasureSpec
                //2392-10-10 AT_MOST
                childHeightSpec = MeasureSpec.makeMeasureSpec(heightSpecSize - (int) mVerticalMargin * 2, MeasureSpec.AT_MOST);
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                childHeightSpec = heightMeasureSpec;
                break;
        }

        int height = 0; // 当前高度坐标 Y轴
        int remainWidth = 0;// 剩余宽度坐标 X轴
        int top = 0;// 顶部 Y轴
        //遍历全部子View
        for (int i = 0; i < getChildCount(); i++) {
            //将测量要求传给子View
            View child = getChildAt(i);

            child.measure(childwidthSpec, childHeightSpec);
            //left top right bottom
            int l=0, t=0, r=0, b=0;
            //第1行:height == 0
            //新起一行:remainwidth +mHorizontalMargin + child.getMeasuredWidth() > widthspecsize
            if (height == 0 || remainWidth + mHorizontalMargin + child.getMeasuredWidth() > widthSpecsize) {
                t = height + (int) mVerticalMargin;    //0+10 顶部坐标     第二行顶部坐标 10+50
                top = t;        //顶部坐标

                height += mVerticalMargin + child.getMeasuredHeight();//每一行追加 10+50
                b = height; // 底部坐标
                remainWidth = (int) mHorizontalMargin;//20     新起一行的剩余宽度
                l = remainWidth;  //left 左边坐标
                //cccccccccccccccccc
                remainWidth += child.getMeasuredWidth(); // 追加每行第一个子view 的宽度
                r = remainWidth;//right 右侧坐标
            }

            //记录下在当前子View里面，等下用
            Location location = new Location(l,t,r,b);
            child.setTag(location);
        }
        setMeasuredDimension(widthSpecsize,heightSpecSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int parentWidth = right - left;
        int parentHeight = bottom - top;

        int childLeft = 0;
        int childTop = 0;
        int childRight = 0;
        int childBottom = 0;

        int currentRow = 0;
        int currentColumn = 0;

        int horizontalSpacing = 10; // 水平间距
        int verticalSpacing = 10; // 垂直间距

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            // 计算子View的宽度和高度
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            // 如果当前行放不下该标签，则换行
            if (childLeft + childWidth > parentWidth) {
                currentRow++;
                currentColumn = 0;
                childLeft = 0;
                childTop = currentRow * (childHeight + verticalSpacing);
            }

            // 根据需要重新布局子View
            childLeft += currentColumn * (childWidth + horizontalSpacing);
            childRight = childLeft + childWidth;
            childBottom = childTop + childHeight;

            // 设置子View的位置
            childView.layout(childLeft, childTop, childRight, childBottom);

            // 更新当前列的索引
            currentColumn++;
        }
    }
}