package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

public class ValueAnimActivity extends AppCompatActivity {
    public static interface AnimatorUpdateListener {
        void onAnimationUpdate(@NonNull ValueAnimator animator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView propertyAnimTv = findViewById(R.id.textView);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                propertyAnimTv.setScaleX(currentValue);
                        //propertyAnimTv.
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.start();

    }
}