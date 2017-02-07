package com.android.animation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lbb on 2016/7/29.
 */
public class MyView extends ImageView {
    private Paint paint;
    private Scale scale;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    public void setScale(Scale scale) {
        this.scale = scale;
        setScaleX(scale.scaleX);
        setScaleY(scale.scaleY);
    }

    public Scale getScale() {
        return scale;
    }


}
