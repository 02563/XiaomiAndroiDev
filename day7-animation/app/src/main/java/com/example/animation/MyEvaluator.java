package com.example.animation;

import android.animation.TypeEvaluator;
import android.graphics.Color;

public class MyEvaluator implements TypeEvaluator<Float> {
    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        float start = startValue;
        float end = endValue;
        return start + fraction * (end - start);
    }
}

