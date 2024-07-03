package com.example.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PropertyAnimRotateXmlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView propertyAnimTv = findViewById(R.id.textView);

        //ObjectAnimator类实现
        /*
        ObjectAnimator animator =
                (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.animator_rotate_x);
        animator.setTarget(propertyAnimTv);
        animator.start();
        */


        //ObjectAnimator objectAnimator =
        //        ObjectAnimator.ofFloat(propertyAnimTv, View.ROTATION_X,0f,360f);




        //ObjectAnimator组合动画
        /*
        //PropertyValuesHolder rotationXHolder =
                //PropertyValuesHolder.ofFloat(View.ROTATION_X,0f,360f);
        //PropertyValuesHolder translateXHolder =
                //PropertyValuesHolder.ofFloat(View.TRANSLATION_X,0f,100f);
        //ObjectAnimator objectAnimator =
                //ObjectAnimator.ofPropertyValuesHolder(propertyAnimTv,rotationXHolder,translateXHolder);
        */


        //AnimatorSet实现（未完成）
        AnimatorSet animatorSet = new AnimatorSet();
        //animatorSet.playTogether(rotationXHolder,translateXHolder);
        

        //animate()方法
        /*
        propertyAnimTv.animate()
                .rotationX(360)
                .translationX(100)
                .setDuration(1000)
                .setStartDelay(1000)
                .start();
         */

        //Evaluator平滑过度背景颜色
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(propertyAnimTv,
                "backgroundColor", Color.parseColor("#009688"),Color.RED);
        objectAnimator.setEvaluator(new ArgbEvaluator());

        //设置持续时间
        objectAnimator.setDuration(10000);
        //启动动画
        objectAnimator.start();
    }
}
