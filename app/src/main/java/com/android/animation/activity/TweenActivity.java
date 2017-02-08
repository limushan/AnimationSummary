package com.android.animation.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.android.animation.R;

/**
 * @author 李彬彬
 *         2017/2/7
 *         Tweened animations
 *         1. 4种类型：
 *         a.alpha :透明度
 *         b.scale :缩放
 *         c.rotate :旋转
 *         d.translate :位移
 *         <p>
 *         2. 添加动画的两个方法：
 *         a.代码方式：调试方便，可重复性差，可维护行差
 *         b.XML方式: 可重复性，可维护性高，调试不变
 *         <p>
 *         3.使用代码方式添加动画：
 *         <p>
 *         3.1 使用的类：Animation的4个子类
 *         a.透明度：AlphaAnimation
 *         b.缩放：ScaleAnimation
 *         c.旋转：RotateAnimation
 *         d.位移：TranslateAnimation
 *         <p>
 *         3.2 单一动画添加：
 *         a.new 一个Animation子类对象
 *         b.设置动画的属性
 *         c.调用view的startAnimation方法开启动画
 *         <p>
 *         3.3 混合动画添加
 *         a.使用AnimationSet类的对象
 *         b.new需要的添加的Animation对象
 *         c.调用AnimationSet的addAnimation方法添加每个动画
 *         d.调用view的startAnimation方法开启动画
 *         <p>
 *         3.4 Animation常用的通用方法：
 *         a.setDuration 设置动画执行时间
 *         b.setFillAfter 设置动画结束后，保持结束后的状态
 *         c.setRepeatCount 设置动画重复次数
 *         d.setStartOffset 设置动画开始结束之前的等待时间
 *         e.setRepeatMode 设置动画重复模式（两种：往返和重新开始）
 *         <p>
 *         4. 使用XML方式添加动画：
 *         a.res资源文件下新建anim文件夹
 *         b.在anim文件夹文件下Animation 资源文件（set标签下添加rotate，alpha，scale，translate便签添加不同的动画或者直接创建rotate，alpha，scale，translate标签的动画）
 *         c.使用AnimationUtils.loadAnimation方法读取解析资源文件，生成Animation对象
 *         d.调用view的startAnimation(Animation)方法开启动画
 *         <p>
 *         5. 注意事项：repeatCount属性只能在具体的动画中设置（rotate，alpha，scale，translate），不能在set下设置，否则无效
 *         <p>
 *         6.Interpolator的使用
 *         AccelerateDecelerateInterpolator: 在动画开始和结束的地方速率比较慢，再中间的时候加速
 *         AccelerateInterpolator : 速率逐渐加速
 *         CycleInterpolator :速率沿正弦曲线改变
 *         DecelerateInterpolator : 速率逐渐减速
 *         LinerInterpolator : 匀速
 *         <p>
 *         6.1 在set中添加
 *         shareInterpolator设置是否共享速率修改器，true共享（代码中构造AnimationSet对象时传参，xml中使用android:shareInterpolator="true"属性）
 *         interpolator设置使用的Interpolator的使用（代码中调用setInterpolator方法传入Interpolator对象，xml中使用 android:interpolator="xxx"属性）
 *         6.2 在单个动画中添加
 *         为单个动画设置interpolator
 *         set下设置shareInterpolator为false
 *
 *         7.AnimationListener
 *         动画监听可以监听动画的开始。动画的结束，以及动画的重复
 */

public class TweenActivity extends AppCompatActivity {

    private View animationView;

    public static void start(Context context) {
        Intent intent = new Intent(context, TweenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        animationView = findViewById(R.id.animation_iv);
    }

    /**
     * 透明度动画，对应Animation ：AlphaAnimation
     * 构造器参数：（起始透明度，终止透明度） 取值范围【0,1】完全透明到完全不透明
     */
    public void alphaByCode(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        animationView.startAnimation(alphaAnimation);
    }

    public void alphaByXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animationView.startAnimation(animation);
    }

    /**
     * 缩放动画：对应的Animation ：ScaleAnimation
     * 构造器参数（动画开始X坐标的伸缩尺寸，动画结束X坐标的伸缩尺寸，动画开始Y坐标的伸缩尺寸，动画结束Y坐标的伸缩尺寸，
     * , 动画在X轴相对于物件位置类型,动画相对于物件的X坐标的开始位置,动画在Y轴相对于物件位置类型,动画相对于物件的Y坐标的开始位置）
     *
     * @param view
     */
    public void scaleByCode(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 3, 1, 3,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        animationView.startAnimation(scaleAnimation);
    }

    public void scaleByXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        animationView.startAnimation(animation);
    }

    /**
     * 旋转动画 ：对应的Animation ：RotateAnimation
     * 构造器参数（动画起始角度,动画的终止角度，动画在X轴位置类型，动画在X轴的开始位置，动画在Y轴位置类型，动画在Y轴的开始位置）
     */
    public void rotateByCode(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(180, 360 + 360,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        rotateAnimation.setDuration(3000);
        animationView.startAnimation(rotateAnimation);
    }

    public void rotateByXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        animationView.startAnimation(animation);
    }

    /**
     * 位移动画 ：对应的Animation ：TranslateAnimation
     * 构造器参数（动画在X轴开始位置类型，动画在X轴的开始位置，动画在Y轴开始位置类型，动画在Y轴的开始位置
     * 动画在X轴结束位置类型，动画在X轴的结束位置，动画在Y轴结束位置类型，动画在Y轴的结束位置）
     */
    public void translateByCode(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0
                , Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 4);
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setRepeatCount(3);
        translateAnimation.setStartOffset(1000);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        animationView.startAnimation(translateAnimation);
    }

    public void translateByXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        animationView.startAnimation(animation);
    }

    /**
     * 混合动画：AnimationSet类
     * 构造器参数（是否共享Animation的Interpolator——true共享）
     *
     * @param view
     */
    public void hybridByCode(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360 + 180,
                Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setDuration(1000);
        animationSet.setRepeatCount(3);
        animationView.startAnimation(animationSet);
    }

    public void hybridByXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hybird);
        animationView.startAnimation(animation);
    }
}
