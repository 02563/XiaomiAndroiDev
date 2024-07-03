package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Rect mRect;
    private Paint mPaint;
    public CustomEditText(Context context){
        super(context);
    }
    public CustomEditText(Context context, AttributeSet attrs){
        super(context,attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }
    public CustomEditText(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }
    //public CustomEditText(Context context,AttributeSet attrs){
    //    super(context,attrs,defStyleAttr,defStyleRes);
    //}

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        int count = getLineCount();//获取行数
        Rect r = mRect;
        Paint paint = mPaint;

        for(int i =0;i<count;i++){
            int baseline = getLineBounds(i,r);
            canvas.drawLine(r.left,baseline+1,r.right,baseline+1,paint);
        }

        // 绘制示例文字
        //String text = "这是一个示例这是一个示例这是一个示例这是一个示例这是一个示例\n这是一个示例这是一个示例这是一个示例这是一个示例这是一个示例";
        //paint.setColor(Color.BLACK);
        //paint.setTextSize(40);
        //canvas.drawText(text, 10, getHeight() / 2, paint);

        super.onDraw(canvas);
    }
}
