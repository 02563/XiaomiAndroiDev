package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnJump = findViewById(R.id.btn_jump);
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        TextView textView = findViewById(R.id.textView);

        //Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animator_rotate_x);
        //animation.setAnimationListener(new MyAnimationListener());

// 创建一个AnimationSet对象，用于组合动画
        AnimationSet animationSet = new AnimationSet(true);

// 创建缩放动画，以当前View的中心点为缩放中心，从1.0缩放到1.5倍
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000); // 设置动画持续时间
        scaleAnimation.setRepeatCount(3); // 设置动画重复次数
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Animation", "scale animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Animation", "scale animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Animation", "scale animation repeat");
            }
        });

        animationSet.addAnimation(scaleAnimation); // 将缩放动画添加到AnimationSet中

// 创建旋转动画，以当前View的中心点为旋转中心，逆时针旋转720度
        RotateAnimation rotateAnimation = new RotateAnimation(0, -720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000); // 设置动画持续时间
        rotateAnimation.setRepeatCount(3); // 设置动画重复次数
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Animation", "rotate animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Animation", "rotate animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Animation", "rotate animation repeat");
            }
        });
        animationSet.addAnimation(rotateAnimation); // 将旋转动画添加到AnimationSet中

// 创建透明度动画，从不透明度到0.8
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.8f);
        alphaAnimation.setDuration(2000); // 设置动画持续时间
        alphaAnimation.setRepeatCount(3); // 设置动画重复次数
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Animation", "alpha animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Animation", "alpha animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Animation", "alpha animation repeat");
            }
        });
        animationSet.addAnimation(alphaAnimation); // 将透明度动画添加到AnimationSet中

        // 设置AnimationListener监听器


        // 启动动画
        //view.startAnimation(animationSet);
        textView.startAnimation(animationSet);

    }

}