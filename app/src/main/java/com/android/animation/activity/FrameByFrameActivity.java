package com.android.animation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.animation.R;

/**
 * @author 李彬彬
 *         2017/2/7
 *         Frame by frame animations
 *         逐帧动画：这一类动画可以创建一个Drawable列表，在一定的时间内逐个播放图片
 *         两种方式添加逐帧动画：
 *         1.xml的方式 直接在drawable新建animation_list格式的文件，item中设置播放图片和每张图片的播放时间
 *         2.代码的方式添加，调用AnimationDrawable的addFrame(drawable,duration)方法设置播放图片和每张图片的播放时间
 */

public class FrameByFrameActivity extends Activity {

    AnimationDrawable animationDrawable = new AnimationDrawable();

    public static void start(Context context) {
        Intent intent = new Intent(context, FrameByFrameActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_by_frame);

        for (int i = 0; i < 24; i++) {
            int id = getResources().getIdentifier("biyao_" + i, "drawable", getPackageName());
            Drawable d = getDrawable(id);
            if (d != null)
                animationDrawable.addFrame(d, 100);
        }
        animationDrawable.setOneShot(false);
        ((ImageView) findViewById(R.id.animation_by_code)).setImageDrawable(animationDrawable);
    }

    public void startAnimation(View view) {
        AnimationDrawable drawable = (AnimationDrawable) findViewById(R.id.animation_by_xml).getBackground();
        drawable.start();


        animationDrawable.start();
    }

    public void stopAnimation(View view) {
        AnimationDrawable drawable = (AnimationDrawable) findViewById(R.id.animation_by_xml).getBackground();
        drawable.stop();

        animationDrawable.stop();
    }


}
