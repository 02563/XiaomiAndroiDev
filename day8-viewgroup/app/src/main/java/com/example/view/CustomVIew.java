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
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class CustomVIew extends View {
    private Paint mPaint;

    public CustomVIew(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);

        //写一段文字
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(40);
        canvas.drawText("开始写字，写一堆文字",100,100,mPaint);
        //draw a straight line
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStrokeWidth(10);
        canvas.drawLine(100,150,300,350,mPaint);
        //画一个空心圆
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,250,80,mPaint);

        //画一个空心矩形
        canvas.drawRect(100,400,400,500,mPaint);

        //剪裁画布，设置背景色，画圆
        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);//设置实心
        canvas.clipRect(new Rect(100,550,300,750));//裁剪出一个矩形区域
        canvas.drawColor(Color.LTGRAY);
        canvas.drawCircle(150,600,100,mPaint);
        canvas.restore();//恢复画布状态

        //画点，画连续点
        canvas.drawPoint(400,200,mPaint);//西一个点
        canvas.drawPoints(new float[]{400,220,420,220,440,220},mPaint);//画多个点
        //画图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.android_icon);//实例化一个bitmap
        canvas.drawBitmap(bitmap,500, 150,mPaint);//画图片
// 使用Path 绘制
        Path path = new Path();
        path.moveTo(400,300);// 不会进行绘制，只用手移动移动画笔，相当于起点。分
        path.lineTo(1000,350);//lineTo 用于进行直线绘制。
        path.moveTo( 400, 310);// 不会进行绘制，只用于移动移动画笔，相当于起点。
        path.quadTo( 650,  400, 400,600);//quadTo 用于绘制圆滑曲线，即贝塞尔曲线。mPath.quadTo(x1，y1，x2
        path.moveTo( 400, 610);//不会进行绘制，只用于移动移动画笔，相当于起点。
        path.cubicTo( 400,  706,500,550, 600,  900);//cubicTo 同样是用来实现贝塞尔曲线的。mPath.cub:
        path.moveTo(600,1000);// 不会进行绘制,,只用于移动移动画笔，相当于起点
        RectF mRectF = new RectF( 400 ,900 ,600 , 1100);
        path.arcTo(mRectF, 0,  270);//arcTo 用于绘制弧线(实际是截取圆或椭圆的》部分
        canvas.drawPath(path, mPaint);//画出曲线

        //圆弧
        RectF rectF1 =new RectF(100,900,200,1000);
        canvas.drawArc(rectF1,0,120,false,mPaint);
        //rectF1为弧线所使用的矩形区域大小；开始角度；扫过的角度；是否使用中心
        //按照坐标绘制文字
        mPaint.setTextSize(32);
        mPaint.setStrokeWidth(1);
        canvas.drawPosText("Hello world",new float[]{20,20,20,20,30,30,40,40,50,50,60,60,70,70,80,60,90,50,100,40,110,30,120,20},mPaint);

        //沿着路径绘制文字
        Path path2 = new Path();
        path2.moveTo(400,300);//移动画笔
        path2.lineTo(1000,200);
        canvas.drawTextOnPath("1591008610010",path2,50,-30,mPaint);//按照既定路径绘制文本内容
    }
}
