package com.android.animation;

import android.animation.TypeEvaluator;

/**
 * Created by lbb on 2016/7/29.
 */
public class ScaleEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Scale startScale = (Scale) startValue;
        Scale endScale = (Scale) endValue;
        float x = startScale.scaleX + fraction * (endScale.scaleX - startScale.scaleX);
        float y = startScale.scaleY + fraction * (endScale.scaleY - startScale.scaleY);
        return new Scale(x, y);
    }
}
