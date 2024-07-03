package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = findViewById(R.id.textView2);

// 创建属性动画对象
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(textView, "rotationX", 0f, 360f);
        rotateAnimator.setDuration(1000);

        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(textView, "translationX", 0f, 120f);
        translateAnimator.setDuration(1000);

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(textView, "alpha", 1f, 0.5f);
        alphaAnimator.setDuration(500);

// 创建AnimatorSet对象
        AnimatorSet animatorSet = new AnimatorSet();

// 设置插值器
        alphaAnimator.setInterpolator(new MyInterpolator());

// 设置估值器
        alphaAnimator.setEvaluator(new MyEvaluator());
        //translateAnimator.setEvaluator(new MyEvaluator());

// 设置动画播放顺序和协调
        animatorSet.play(rotateAnimator).with(translateAnimator);
        animatorSet.play(alphaAnimator).after(rotateAnimator).after(translateAnimator);

// 启动动画
        animatorSet.start();
    }
}