package com.example.animation;

import android.view.animation.Animation;

public class MyAnimationListener implements Animation.AnimationListener {
    @Override
    public void onAnimationStart(Animation animation) {
        // 动画开始时的操作
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // 动画结束时的操作
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // 动画重复时的操作
        System.out.println("Animation repeat");
    }
}
