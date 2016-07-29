package com.android.animationtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by lbb on 2016/7/14.
 */
public class ValueAnimationActivity extends Activity {
    private ImageView mTestIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animation);
        mTestIv = (ImageView) findViewById(R.id.test_iv);
        setValueAnimation();
        testValueAnimator();
    }

    private void testValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100, 10, 1000);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("current value :", "" + animation.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 属性动画是通过不断改变View的属性值进行操作实现的
     * 初始和结束值是由ValueAnimator 负责计算的，内部循环计算值与值的动画过渡
     */
    public void setValueAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTestIv, "alpha", 1f, 0f, 1f);
        animator.setDuration(1000);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mTestIv, "translationX", -100f, 0, 100f);
        animator1.setDuration(1000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mTestIv, "rotation", 0, 360, 0);
        animator2.setDuration(2000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mTestIv, "translationY", -500f, 0, 500f);
        animator3.setDuration(5000);
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mTestIv, "scaleY", 1, 10, 5, 10, 1);
        animator4.setDuration(10000);
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(mTestIv, "scaleX", 1, 10, 5, 10, 1);
        animator5.setDuration(10000);
        set.play(animator).with(animator1).after(animator2).before(animator4).before(animator5);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(ValueAnimationActivity.this, "开始了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(ValueAnimationActivity.this, "结束了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }
        });

    }
}
