package com.example.animation;

import android.view.animation.Interpolator;

public class MyInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        //return (float) (Math.sin(input * Math.PI * 2) * Math.exp(-input * 3));
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
