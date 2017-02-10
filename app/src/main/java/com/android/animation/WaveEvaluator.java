package com.android.animation;

import android.animation.TypeEvaluator;

/**
 * Created by lbb on 2016/7/29.
 */
public class WaveEvaluator implements TypeEvaluator {

    // duration =10s;
    //y=x
    //fraction=t/duration
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //int x= (int) startValue;
        float x = 500 * fraction;
        float y = (float) (150 * Math.cos(fraction * 2 * Math.PI));
        return new Translate(x, y);
    }
}
